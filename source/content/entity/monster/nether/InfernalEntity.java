package net.tslat.aoa3.content.entity.monster.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntitySpawnPlacements;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;


public class InfernalEntity extends AoAMeleeMob<InfernalEntity> {
    public InfernalEntity(EntityType<? extends InfernalEntity> entityType, Level world) {
        super(entityType, world);

        setPathfindingMalus(PathType.LAVA, 4);
        setPathfindingMalus(PathType.DANGER_FIRE, 0);
        setPathfindingMalus(PathType.DAMAGE_FIRE, 0);
        setPathfindingMalus(PathType.WATER_BORDER, 8);
        setPathfindingMalus(PathType.WATER, -1);
    }

    @Override
    public BrainActivityGroup<InfernalEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && (pl.isCreative() || pl.isSpectator())) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2).whenActivating(entity -> {
                    Entity target = BrainUtils.getTargetOfEntity(entity);
                    BlockPos pos = (target == null ? entity : target).blockPosition();
                    BlockState state = level().getBlockState(pos);

                    if (state.isAir())
                        state = level().getBlockState(pos = pos.below());

                    BlockPos finalPos = pos;

                    net.tslat.effectslib.api.particle.ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, state), () -> new Vec3(finalPos.getX() + RandomUtil.randomValueUpTo(1), finalPos.getY() + 1.1f, finalPos.getZ() + RandomUtil.randomValueUpTo(1)), 3)
                            .velocity(0, 0.5f, 0)
                            .sendToAllPlayersTrackingBlock((ServerLevel)entity.level(), finalPos);

                    entity.playSound(AoASounds.ROCK_SMASH.get(), 1, 0.2f);
                    doSlam(finalPos, 0.75f);
                }));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.LAVA_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_INFERNAL_HURT.get();
    }

    @Override
    protected float getStepWeight() {
        return 2f;
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 0.6f;
    }

    @Override
    protected int getAttackSwingDuration() {
        return 22;
    }

    @Override
    protected int getPreAttackTime() {
        return 10;
    }

    private void doSlam(BlockPos fromPos, float chance) {
        TELParticlePacket packet = new TELParticlePacket();

        for (Direction dir : Direction.values()) {
            if (RandomUtil.percentChance(chance)) {
                BlockPos pos = fromPos.offset(dir.getNormal());

                if (level().getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
                    int tickTime = Math.max(1, 1 - pos.distManhattan(fromPos));

                    packet.particle(ParticleBuilder.forPosition(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + 1.1, pos.getZ() + RandomUtil.randomValueUpTo(1)));
                    level().setBlock(pos, Blocks.MAGMA_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
                    AoAScheduler.scheduleSyncronisedTask(() -> doSlam(pos, chance * 0.8f), tickTime);
                    AoAScheduler.scheduleSyncronisedTask(() -> {
                        if (level().getBlockState(pos).getBlock() == Blocks.MAGMA_BLOCK)
                            level().setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), Block.UPDATE_ALL);
                    }, tickTime + 100);
                }
            }
        }

        if (!packet.isEmpty())
            packet.sendToAllPlayersTrackingEntity((ServerLevel)level(), this);
    }

    public static SpawnPlacements.SpawnPredicate<Mob> spawnRules() {
        return AoAEntitySpawnPlacements.SpawnBuilder.DEFAULT.noPeacefulSpawn().spawnChance(1 / 10f).noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock();
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<InfernalEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(100)
                .armour(15, 10)
                .moveSpeed(0.23)
                .meleeStrength(14)
                .aggroRange(16)
                .knockbackResist(1f)
                .followRange(24);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericLivingController(this));
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE).transitionLength(0));
    }
}
