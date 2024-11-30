package net.tslat.aoa3.content.entity.monster.overworld;

import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntitySpawnPlacements;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;


public class YetiEntity extends AoAMeleeMob<YetiEntity> {
	private static final int ATTACK_STRIKE = 0;
	private static final int ATTACK_SWING = 1;

	public YetiEntity(EntityType<? extends YetiEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_YETI_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_YETI_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_YETI_HURT.get();
	}

	@Override
	public BrainActivityGroup<YetiEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>(),
				new OneRandomBehaviour<>(
						Pair.of(new AnimatableMeleeAttack<>(7).attackInterval(entity -> 16)
								.whenStarting(entity -> ATTACK_STATE.set(entity, ATTACK_STRIKE))
								.whenStopping(entity -> BrainUtils.setSpecialCooldown(this, 16)), 3),
						Pair.of(new AnimatableMeleeAttack<>(4).attackInterval(entity -> 14)
								.whenStarting(entity -> ATTACK_STATE.set(entity, ATTACK_SWING))
								.whenStopping(entity -> BrainUtils.setSpecialCooldown(this, 14)), 1)
				).startCondition(entity -> !BrainUtils.isOnSpecialCooldown(this))
		);
	}

	@Override
	protected void onAttack(Entity target) {
		super.onAttack(target);

		if (ATTACK_STATE.is(this, ATTACK_SWING) && target instanceof LivingEntity livingTarget)
			DamageUtil.doScaledKnockback(livingTarget, this, 1.1f, 1, 1.25f, 1);
	}

	@Override
	protected int getAttackSwingDuration() {
		return ATTACK_STATE.is(this, ATTACK_STRIKE) ? 16 : 14;
	}

	@Override
	protected int getPreAttackTime() {
		return ATTACK_STATE.is(this, ATTACK_STRIKE) ? 7 : 4;
	}

	public static SpawnPlacements.SpawnPredicate<Mob> spawnRules() {
		return AoAEntitySpawnPlacements.SpawnBuilder.DEFAULT_DAY_MONSTER.noLowerThanY(45).spawnChance(1 / 2f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<YetiEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(25)
				.armour(2, 2)
				.moveSpeed(0.2875)
				.meleeStrength(5)
				.followRange(12)
				.aggroRange(12);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkController(this),
				AoAAnimations.dynamicAttackController(this, state -> ATTACK_STATE.is(this, ATTACK_STRIKE) ? DefaultAnimations.ATTACK_STRIKE : DefaultAnimations.ATTACK_SWING)
		);
	}
}
