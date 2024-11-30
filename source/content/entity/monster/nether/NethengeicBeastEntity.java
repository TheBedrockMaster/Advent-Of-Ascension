package net.tslat.aoa3.content.entity.monster.nether;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.*;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.FireballEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessHeldAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomDelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRetaliateTarget;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;


public class NethengeicBeastEntity extends AoARangedMob<NethengeicBeastEntity> {
    private static final EntityDataAccessor<Boolean> FLAME_AURA = SynchedEntityData.defineId(NethengeicBeastEntity.class, EntityDataSerializers.BOOLEAN);
    private static final RawAnimation FLAMETHROWER_ANIM = RawAnimation.begin().thenPlay("attack.flamethrower.start").thenPlay("attack.flamethrower.hold");
    private static final RawAnimation FLAMETHROWER_RELEASE_ANIM = RawAnimation.begin().thenPlay("attack.flamethrower.end");
    private static final RawAnimation FIRE_AURA_ANIM = RawAnimation.begin().thenPlay("misc.fire_aura");
    private static final RawAnimation FIRE_SPIN_ANIM = RawAnimation.begin().thenPlay("attack.fire_spin");
    private static final int FIREBALL = 0;
    private static final int FLAMETHROWER_OPEN = 1;
    private static final int FLAMETHROWER = 2;

    public NethengeicBeastEntity(EntityType<? extends NethengeicBeastEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public BrainActivityGroup<NethengeicBeastEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new WalkOrRunToWalkTarget<>().startCondition(entity -> !isDoingStationaryActivity()),
                new FloatToSurfaceOfFluid<>());
    }

    @Override
    public BrainActivityGroup<NethengeicBeastEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new SetRetaliateTarget<>()
                        .attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>().speedModifier(0.9f),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
    }

    @Override
    public BrainActivityGroup<NethengeicBeastEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new OneRandomBehaviour<>(
                        Pair.of(
                                new AnimatableRangedAttack<>(0)
                                        .attackInterval(entity -> entity.getRandom().nextIntBetweenInclusive(10, 20))
                                        .attackRadius(24)
                                        .whenStarting(entity -> ATTACK_STATE.set(this, FIREBALL))
                                        .whenStopping(entity -> BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 10)),
                                30
                        ),
                        Pair.of(
                                new CustomDelayedBehaviour<>(20)
                                        .whenActivating(entity -> toggleAura())
                                        .cooldownFor(entity -> entity.getRandom().nextIntBetweenInclusive(180, 360))
                                        .whenStarting(entity -> {
                                            triggerAnim("posing", "fire_aura");

                                            if (!hasAura())
                                                level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_NETHENGEIC_BEAST_FLAME_AURA_ACTIVATE.get(), SoundSource.HOSTILE, 1, 1);
                                        })
                                        .whenStopping(entity -> BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 25)),
                                1
                        ),
                        Pair.of(
                                new FlamethrowerAttack(),
                                7
                        ),
                        Pair.of(
                                new FlameSpinAttack(),
                                7
                        )
                ).startCondition(entity -> !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))
        );
    }

    private static class FlameSpinAttack extends ConditionlessHeldAttack<NethengeicBeastEntity> {
        public FlameSpinAttack() {
            requiresTarget();
            runFor(entity -> 20);
            onTick(entity -> {
                if (getRunningTime() <= 5)
                    return true;

                TELParticlePacket packet = new TELParticlePacket();

                for (int i = -180; i <= 180; i += 8) {
                    double angle = Math.toRadians(i * entity.tickCount);
                    double x = entity.getX() + 2 * Math.cos(angle);
                    double y = entity.getY() + 1.3f;
                    double z = entity.getZ() + 2 * Math.sin(angle);
                    double velocityX = x - entity.getX();
                    double velocityZ = z - entity.getZ();

                    packet.particle(ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.BURNING_FLAME, entity), x, y, z)
                            .colourOverride(0f, 0f, 0f, 0f)
                            .scaleMod(0.35f)
                            .lifespan(Mth.ceil(3 / (entity.random.nextFloat() * 0.8f + 0.2f)))
                            .velocity(velocityX, -0.6f, velocityZ));
                    packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMALL_FLAME, x, y, z)
                            .velocity(velocityX, -0.6f, velocityZ));
                    packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMOKE, x, y, z));
                }

                packet.sendToAllNearbyPlayers((ServerLevel)entity.level(), EntityUtil.getEntityCenter(entity), 64);

                if (getRunningTime() % 9 == 0 || getRunningTime() % 19 == 0)
                    entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 1);

                return true;
            });
            startCondition(entity -> {
                if (entity.hasAura())
                    return false;

                Entity target = BrainUtils.getTargetOfEntity(entity);

                if (target == null)
                    return false;

                return entity.distanceToSqr(target) < 25 || !EntityRetrievalUtil.getEntities(entity, 5, entity2 -> PlayerUtil.getPlayerOrOwnerIfApplicable(entity2) != null).isEmpty();
            });
            cooldownFor(entity -> entity.getRandom().nextIntBetweenInclusive(100, 140));
            whenStarting(entity -> entity.triggerAnim("posing", "fire_spin"));
            whenStopping(entity -> BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 20));
        }
    }

    private static class FlamethrowerAttack extends ConditionlessHeldAttack<NethengeicBeastEntity> {
        public FlamethrowerAttack() {
            requiresTarget();
            onTick(entity -> {
                if (getRunningTime() <= 24)
                    return true;

                ATTACK_STATE.set(entity, FLAMETHROWER);

                Vec3 position = entity.position();
                double baseX = position.x;
                double baseY = entity.getEyeY() - 1;
                double baseZ = position.z;
                TELParticlePacket packet = new TELParticlePacket(ParticleBuilder.forPosition(ParticleTypes.LARGE_SMOKE, baseX, baseY, baseZ));

                for (int i = 0; i < 5; i++) {
                    Vec3 velocity = this.target.getEyePosition().subtract(baseX + RandomUtil.randomScaledGaussianValue(0.5f), baseY + RandomUtil.randomScaledGaussianValue(0.5f), baseZ + RandomUtil.randomScaledGaussianValue(0.5f)).normalize().scale(0.75f);

                    packet.particle(ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.BURNING_FLAME, entity), baseX, baseY, baseZ)
                            .colourOverride(0f, 0f, 0f, 0f)
                            .scaleMod(0.35f)
                            .velocity(velocity.x, velocity.y, velocity.z));
                    packet.particle(ParticleBuilder.forPosition(ParticleTypes.SMALL_FLAME, baseX, baseY, baseZ)
                            .velocity(velocity.x, velocity.y, velocity.z));
                }

                packet.sendToAllNearbyPlayers((ServerLevel)entity.level(), EntityUtil.getEntityCenter(entity), 64);

                if (getRunningTime() % 9 == 0 || getRunningTime() % 19 == 0)
                    entity.playSound(AoASounds.FLAMETHROWER.get(), 2, 1);

                return true;
            });
            startCondition(entity -> {
                LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

                if (target == null)
                    return false;

                double dist = target.distanceToSqr(entity);
                double aggroRange = entity.getAttributeValue(AoAAttributes.AGGRO_RANGE);

                return dist <= aggroRange * aggroRange && BrainUtils.canSee(entity, target);
            });
            stopIf(entity -> {
                LivingEntity target = BrainUtils.getMemory(entity, MemoryModuleType.ATTACK_TARGET);

                if (target == null)
                    return true;

                double dist = target.distanceToSqr(entity);
                double distanceCutoff = entity.getAttributeValue(Attributes.FOLLOW_RANGE) * 0.75f;

                return dist > distanceCutoff * distanceCutoff || (getRunningTime() > 80 && entity.getRandom().nextInt(100) == 0) || !BrainUtils.canSee(entity, target);
            });
            cooldownFor(entity -> 160);
            whenStopping(entity -> {
                BrainUtils.setForgettableMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get(), true, 20);
                ATTACK_STATE.set(entity, FIREBALL);
            });
            whenStarting(entity -> ATTACK_STATE.set(entity, FLAMETHROWER_OPEN));
        }
    }

    @Override
    public int calculateKillXp() {
        int xp = super.calculateKillXp();

        return xp > 0 ? xp + 15 : 0;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundEvents.BLAZE_SHOOT;
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new FireballEntity(this.level(), this, BaseMobProjectile.Type.PHYSICAL);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(FLAME_AURA, false);
    }

    @Override
    public int getMaxHeadYRot() {
        return 10;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAMPFIRE_CRACKLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_NETHENGEIC_BEAST_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_NETHENGEIC_BEAST_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockState) {}

    public boolean hasAura() {
        return getEntityData().get(FLAME_AURA);
    }

    public void toggleAura() {
        getEntityData().set(FLAME_AURA, !getEntityData().get(FLAME_AURA));
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return type != NeoForgeMod.EMPTY_TYPE.value();
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return effect.getEffect() != AoAMobEffects.NETHENGEIC_CURSE.get() && super.canBeAffected(effect);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (level().isClientSide()) {
            if (hasAura()) {
                for (int i = 0; i < 3; i++) {
                    double cos = Math.cos(getX() * RandomUtil.randomValueBetween(-1, 1));
                    double sin = Math.sin(getZ() * RandomUtil.randomValueBetween(-1, 1));
                    double startX = cos * getBbWidth() + getX();
                    double startZ = sin * getBbWidth() + getZ();
                    double startY = getRandomY();

                    ParticleBuilder.forPosition(EntityTrackingParticleOptions.fromEntity(AoAParticleTypes.FIRE_AURA, this), startX, startY, startZ)
                            .scaleMod(0.25f)
                            .colourOverride(1f, 1f, 1f, 0.75f)
                            .velocity(RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1, RandomUtil.fiftyFifty() ? -1 : 1)
                            .spawnParticles(level());
                }
            }

            ParticleBuilder.forPosition(ParticleTypes.FLAME, getX() + RandomUtil.randomValueBetween(-0.2f, 0.2f), getEyeY() - 1 + RandomUtil.randomValueBetween(-0.2f, 0.2f), getZ() + RandomUtil.randomValueBetween(-0.2f, 0.2f)).spawnParticles(level());

            if (getRandom().nextInt(10) == 0) {
                ParticleBuilder.forPosition(ParticleTypes.SMOKE, getX(), getEyeY() - 1, getZ()).spawnParticles(level());

                if (getDeltaMovement().horizontalDistanceSqr() == 0)
                    ParticleBuilder.forPosition(ParticleTypes.DRIPPING_LAVA, getX(), getEyeY() - 1, getZ()).spawnParticles(level());
            }
        }
        else if (hasAura() && BrainUtils.getTargetOfEntity(this) == null) {
            toggleAura();
        }
    }

    @Override
    public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
        super.doRangedAttackEntity(projectile, target);

        if (projectile == null) {
            DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(AoADamageTypes.MOB_FLAMETHROWER, this, position()), target, 1);

            if (RandomUtil.oneInNChance(4) && target.getRemainingFireTicks() < 300)
                target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 1);
        }
    }

    @Override
    public void onProjectileAttack(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
        target.igniteForSeconds((int)Math.ceil(Math.max(0, target.getRemainingFireTicks()) / 20f) + 3);
    }

    @Override
    public void doRangedAttackBlock(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
        if (EventHooks.canEntityGrief(level(), projectile.getOwner()) && projectile instanceof FireballEntity) {
            BlockPos firePos = pos.offset(sideHit.getNormal());

            if (!this.level().getBlockState(firePos).canBeReplaced()) { // Because vanilla collision detection is stupid
                firePos = pos.relative(Direction.UP);

                if (!this.level().getBlockState(firePos).canBeReplaced())
                    return;
            }

            this.level().setBlock(firePos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (hasAura()) {
            if (DamageUtil.isMeleeDamage(source)) {
                if (source.getDirectEntity() instanceof LivingEntity attacker) {
                    DamageUtil.safelyDealDamage(DamageUtil.entityDamage(AoADamageTypes.MOB_FIRE_RECOIL, this), attacker, 3);
                    attacker.igniteForSeconds((int)Math.ceil(Math.max(0, attacker.getRemainingFireTicks()) / 20f) + 2);
                    attacker.addEffect(new MobEffectInstance(AoAMobEffects.NETHENGEIC_CURSE, 200));
                }
            }
            else if (DamageUtil.isEnergyDamage(source)) {
                heal(amount);

                return false;
            }
        }

        return super.hurt(source, amount);
    }

    public static SpawnPlacements.SpawnPredicate<Mob> spawnRules() {
        return AoAEntitySpawnPlacements.SpawnBuilder.DEFAULT.noPeacefulSpawn().noSpawnOn(Blocks.NETHER_WART_BLOCK).ifValidSpawnBlock().and((entityType, levelAccessor, spawnReason, blockPos, rand) -> {
            if (!(levelAccessor instanceof Level level) || (spawnReason != MobSpawnType.STRUCTURE && spawnReason != MobSpawnType.NATURAL))
                return true;

            return EntityRetrievalUtil.getEntities(level,
                    new AABB(blockPos.getX() - 15, blockPos.getY() - 2, blockPos.getZ() - 15, blockPos.getX() + 15, blockPos.getY() + 2, blockPos.getZ() + 15),
                    entity -> entity.getType() == AoAMonsters.NETHENGEIC_BEAST.get()).isEmpty();
        });
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<NethengeicBeastEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(120)
                .moveSpeed(0.25)
                .projectileDamage(10)
                .aggroRange(16)
                .followRange(24);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(new AnimationController<>(this, "living", 0, event -> {
            if (!ATTACK_STATE.is(this, FLAMETHROWER)) {
                event.getController().setAnimationSpeed(1 + (1 - (getHealth() / getMaxHealth())) * 5);
                event.getController().setAnimation(DefaultAnimations.LIVING);

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
        controllers.add(AoAAnimations.genericHeldPoseController(this, FLAMETHROWER_ANIM, FLAMETHROWER_RELEASE_ANIM, entity -> ATTACK_STATE.isAny(entity, FLAMETHROWER_OPEN, FLAMETHROWER))
                .triggerableAnim("fire_aura", FIRE_AURA_ANIM)
                .triggerableAnim("fire_spin", FIRE_SPIN_ANIM));
    }
}
