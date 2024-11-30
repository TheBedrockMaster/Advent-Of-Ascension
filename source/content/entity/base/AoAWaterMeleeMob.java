package net.tslat.aoa3.content.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;

public abstract class AoAWaterMeleeMob<T extends AoAWaterMeleeMob<T>> extends AoAWaterMonster<T> {
    protected double attackReach;

    protected AoAWaterMeleeMob(EntityType<? extends AoAWaterMeleeMob> entityType, Level level) {
        super(entityType, level);

        this.attackReach = getBbWidth() * 1.75d + getAttackVectorPositionOffset() * 0.3f + (getEyeHeight() / 3.6d * 0.25d);
    }

    @Override
    public int calculateKillXp() {
        return !this.hasDrops ? 0 : (int)(8 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);
    }

    @Override
    public BrainActivityGroup<? extends T> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
    }

    protected float getAttackVectorPositionOffset() {
        return 0;
    }

    protected double getAttackReach() {
        return this.attackReach;
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity target) {
        final AABB hitBounds = getAttackBoundingBox();

        if (!hitBounds.intersects(target.getHitbox()))
            return false;

        double reach = Math.max(hitBounds.getXsize(), hitBounds.getZsize()) / 2f;

        return hitBounds.getCenter().distanceToSqr(target.position()) <= reach * reach;
    }

    @Override
    protected AABB getAttackBoundingBox() {
        AABB boundingBox = getBoundingBox();
        double attackPositionOffset = getAttackVectorPositionOffset() - boundingBox.getXsize();
        double reach = getAttackReach();

        if (getVehicle() != null) {
            AABB vehicleBounds = getVehicle().getBoundingBox();
            boundingBox = new AABB(
                    Math.min(boundingBox.minX, vehicleBounds.minX),
                    boundingBox.minY,
                    Math.min(boundingBox.minZ, vehicleBounds.minZ),
                    Math.max(boundingBox.maxX, vehicleBounds.maxX),
                    boundingBox.maxY,
                    Math.max(boundingBox.maxZ, vehicleBounds.maxZ)
            );
        }

        if (attackPositionOffset > 0)
            boundingBox.move(MathUtil.getBodyForward(this).scale(attackPositionOffset));

        return boundingBox.inflate(reach, 0, reach);
    }
}
