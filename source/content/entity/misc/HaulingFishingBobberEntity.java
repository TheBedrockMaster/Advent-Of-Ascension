package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.item.tool.misc.HaulingRod;
import net.tslat.aoa3.content.skill.hauling.HaulingEntity;
import net.tslat.aoa3.content.skill.hauling.HaulingSpawnPool;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HaulingFishingBobberEntity extends FishingHook {
	protected static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(HaulingFishingBobberEntity.class, EntityDataSerializers.INT);

	protected final ItemStack rod;
	protected float luck;
	protected float lureReduction;

	protected float fishingBonusMod = 1;
	protected int timeUntilFishSpawn = -1;
	protected Entity hookedEntity = null;
	protected Entity spawnedFish = null;
	protected long hookTime = 0;
	protected double lastFishDist = 0;

	protected State state = State.MIDAIR;

	public HaulingFishingBobberEntity(EntityType<? extends HaulingFishingBobberEntity> entityType, Level level) {
		super(entityType, level);

		this.rod = null;
		this.xo = getX();
		this.yo = getY();
		this.zo = getZ();
		this.luck = 0;
		this.lureReduction = 0;
		getEntityData().set(DATA_HOOKED_ENTITY, -1);
	}

	public HaulingFishingBobberEntity(ServerPlayer player, Level world, ItemStack rod) {
		this(player, world, rod, 0, 0);

		this.luck = calculateLuck(player, rod);
		this.lureReduction = calculateLureTimeReduction(player, rod);
	}

	public HaulingFishingBobberEntity(Player player, Level world, ItemStack rod, float luck, float lureReduction) {
		super(player, world, 0, 0);

		this.rod = rod;
		this.luck = luck;
		this.lureReduction = lureReduction;
		setOwner(player);
		float castStrength = getCastStrength();

		setDeltaMovement(getDeltaMovement().multiply(castStrength, castStrength, castStrength));
		getEntityData().set(DATA_HOOKED_ENTITY, -1);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(STATE, 0);
	}

	protected float calculateLuck(ServerPlayer player, ItemStack rod) {
		float luck = player.getLuck();

		if (rod.getItem() instanceof HaulingRod) {
			luck += ((HaulingRod)rod.getItem()).getLuckMod(player, rod);
		}
		else {
			luck += EnchantmentHelper.getFishingLuckBonus(player.serverLevel(), rod, player);
		}

		return luck;
	}

	protected float calculateLureTimeReduction(ServerPlayer player, ItemStack rod) {
		if (rod.getItem() instanceof HaulingRod) {
			return ((HaulingRod)rod.getItem()).getLureMod(player, rod);
		}
		else {
			return EnchantmentHelper.getFishingTimeReduction(player.serverLevel(), rod, player);
		}
	}

	protected int minLureTime() {
		return 40;
	}

	protected int maxLureTime() {
		return 700;
	}

	public State getState() {
		return this.state;
	}

	public float getLuck() {
		return this.luck;
	}

	public ItemStack getRod() {
		return this.rod;
	}

	protected void calculateFishingLureBonus() {
		this.fishingBonusMod = 1;

		Holder<Biome> biome = level().getBiome(blockPosition());
		float temperature = biome.value().getTemperature(blockPosition());

		if (temperature < 0.15f) {
			this.fishingBonusMod *= 0.9f;
		}
		else if (temperature > 1.5f) {
			this.fishingBonusMod *= 0.8f;
		}

		if (biome.value().getPrecipitationAt(blockPosition()) == Biome.Precipitation.RAIN)
			this.fishingBonusMod *= 1.1f;

		if (level().isRainingAt(blockPosition()))
			this.fishingBonusMod *= 1.1f;

		this.fishingBonusMod *= fishingBonusModForBiome(biome);

		int nearbyFluidBlocks = WorldUtil.getBlocksWithinAABB(level(), getBoundingBox().inflate(3, 2, 3), (state, pos) -> state.getFluidState().is(getApplicableFluid()) && state.getFluidState().isSource()).size();

		if (nearbyFluidBlocks <=  50) {
			this.fishingBonusMod *= 0.5f;

			if (nearbyFluidBlocks < 15)
				this.fishingBonusMod *= 0.5f;
		}

		this.fishingBonusMod *= 1 + (nearbyFluidBlocks * 0.0025f);
		this.fishingBonusMod += 0.25f * lureReduction / 5f;
		this.fishingBonusMod *= (1 - Math.min(5, EntityRetrievalUtil.getPlayers(level(), getBoundingBox().inflate(5)).size()) * 0.1f);
	}

	protected float fishingBonusModForBiome(Holder<Biome> biome) {
		for (TagKey<Biome> tag : biome.tags().toList()) {
			if (tag == BiomeTags.IS_OCEAN || tag == BiomeTags.IS_RIVER || tag == Tags.Biomes.IS_SWAMP)
				return 1.25f;

			if (tag == Tags.Biomes.IS_DEAD || tag == Tags.Biomes.IS_DRY || tag == Tags.Biomes.IS_HOT)
				return 0.5f;
		}

		return 1f;
	}

	@Override
	public void tick() {
		handleSuperTick();

		Player player = getPlayerOwner();

		if (!level().isClientSide() && !checkStillValid(player)) {
			discard();

			return;
		}

		if (isInLava() && getApplicableFluid() != FluidTags.LAVA) {
			discard();

			return;
		}

		if (!level().isClientSide())
			updateState();

		if (this.hookedEntity == null)
			checkIfCollided();

		if (!level().isClientSide() && position().distanceToSqr(player.position()) > Math.pow(getMaxCastDistance() * 2f, 2)) {
			discard();

			return;
		}

		BlockPos pos = blockPosition();
		FluidState fluidState = level().getFluidState(pos);

		if (this.state == State.HOOKED_FISH || this.state == State.HOOKED_IN_ENTITY) {
			if (hookedEntity != null) {
				setPos(hookedEntity.getX(), hookedEntity.getY(0.8d) - 0.1d, this.hookedEntity.getZ());

				if (this.state == State.HOOKED_FISH) {
					if (!level().isClientSide() && this.hookTime-- <= 0) {
						stopFishing();
					}
					else {
						if (EntityUtil.isEntityMoving(player))
							this.hookTime -= 2;

						if (this.hookedEntity instanceof PathfinderMob) {
							if (!level().isClientSide()) {
								PathfinderMob creature = (PathfinderMob)this.hookedEntity;

								if (creature.getNavigation().isDone()) {
									Vec3 targetPos = DefaultRandomPos.getPosAway(creature, 30, 5, player.position());

									if (targetPos != null)
										creature.getNavigation().moveTo(creature.getNavigation().createPath(BlockPos.containing(targetPos), 0), 5);
								}
							}
						}
						else {
							if (this.hookedEntity.onGround()) {
								stopFishing();
							}
							else {
								this.hookedEntity.setDeltaMovement(this.hookedEntity.getDeltaMovement().subtract(0, 0.008, 0));
							}
						}
					}
				}
			}

			return;
		}

		if (!level().isClientSide()) {
			if (timeUntilFishSpawn >= 0) {
				if (timeUntilFishSpawn-- == 0)
					spawnFish((ServerPlayer)player);
			}
			else if (state == State.IN_FLUID) {
				if (spawnedFish != null && spawnedFish.isAlive()) {
					if (spawnedFish instanceof PathfinderMob creature) {
						BlockPos targetPos = blockPosition();
						PathNavigation navigator = creature.getNavigation();
						float dist = (float)creature.distanceToSqr(this);

						if (lastFishDist - dist < 0.2 || dist <= 5) {
							EntityUtil.pullEntityIn(this, creature, 0.025f, false);

							if (dist <= 5)
								navigator.stop();
						}
						else if (!creature.isPathFinding() || !navigator.getTargetPos().equals(targetPos)) {
							navigator.moveTo(navigator.createPath(targetPos, 0), 0.5f);
						}

						lastFishDist = dist;
					}
				}
				else {
					startFishing();
				}
			}
		}

		doBobbing(fluidState);

		if (state == State.STUCK || state == State.MIDAIR)
			setDeltaMovement(getDeltaMovement().subtract(0, 0.03d, 0));

		if (state == State.IN_FLUID)
			setDeltaMovement(getDeltaMovement().scale(0.92d));

		move(MoverType.SELF, getDeltaMovement());
		updateRotation();
		reapplyPosition();
	}

	protected void doBobbing(FluidState fluidState) {
		if (state == State.IN_FLUID) {
			BlockPos pos = blockPosition();
			float fluidHeight = fluidState.getHeight(level(), pos);
			Vec3 vector3d = this.getDeltaMovement();
			double fluidAdjustedHeight = this.getY() + vector3d.y - (double)pos.getY() - (double)fluidHeight + 0.1;

			if (Math.abs(fluidAdjustedHeight) < 0.01D)
				fluidAdjustedHeight += Math.signum(fluidAdjustedHeight) * 0.1D;

			setDeltaMovement(vector3d.x * 0.9D, vector3d.y - fluidAdjustedHeight * (double)this.random.nextFloat() * 0.2D, vector3d.z * 0.9D);
		}
	}

	@Override
	public boolean save(CompoundTag tag) {
		if (this.spawnedFish != null && this.spawnedFish.isAlive())
			this.spawnedFish.remove(RemovalReason.DISCARDED);

		return super.save(tag);
	}

	protected void updateState() {
		State fromState = getState();

		if (onGround() || horizontalCollision) {
			state = State.STUCK;
		}
		else if (state == State.STUCK) {
			state = State.MIDAIR;
		}

		if (hookedEntity != null && hookedEntity.isAlive()) {
			state = hookedEntity == spawnedFish ? State.HOOKED_FISH : State.HOOKED_IN_ENTITY;
		}
		else if (state == State.HOOKED_FISH || state == State.HOOKED_IN_ENTITY) {
			state = State.MIDAIR;
		}

		if (state == State.IN_FLUID && spawnedFish != null && spawnedFish.distanceToSqr(this) < 0.25f)
			state = State.HOOKED_FISH;

		FluidState fluidState = level().getFluidState(blockPosition());

		if (fluidState.is(getApplicableFluid())) {
			if (state == State.MIDAIR) {
				state = State.IN_FLUID;

			}
		}
		else if (state == State.IN_FLUID) {
			state = State.MIDAIR;
		}

		if (getState() != fromState)
			onStateChange(fromState, getState());
	}

	protected void onStateChange(State fromState, State toState) {
		if (fromState == State.IN_FLUID) {
			if (hookedEntity == null && spawnedFish == null) {
				stopFishing();
			}
			else if (toState == State.HOOKED_FISH) {
				hookedEntity = spawnedFish;
				hookTime = 1200;
			}
		}

		if (toState == State.HOOKED_IN_ENTITY || toState == State.HOOKED_FISH || toState == State.STUCK) {
			setDeltaMovement(Vec3.ZERO);

			if (toState == State.STUCK) {
				stopFishing();
			}
			else {
				updateHookedEntity();
			}
		}
		else if (toState == State.IN_FLUID) {
			setDeltaMovement(getDeltaMovement().multiply(0.3d, 0.2d, 0.3d));
			startFishing();
		}

		if (fromState == State.IN_FLUID && hookedEntity == null && spawnedFish == null)
			stopFishing();

		if (fromState == State.HOOKED_FISH || fromState == State.HOOKED_IN_ENTITY)
			updateHookedEntity();

		getEntityData().set(STATE, state.value());
	}

	protected void startFishing() {
		stopFishing();
		calculateFishingLureBonus();

		int minTime = minLureTime();
		int maxTime = maxLureTime();

		if (fishingBonusMod < 0.9f) {
			minTime /= fishingBonusMod / 2f;
			maxTime /= fishingBonusMod / 3f;
		}
		else {
			maxTime /= fishingBonusMod;
		}

		if (minTime < 1)
			minTime = 1;

		if (maxTime < 1)
			maxTime = 1;

		timeUntilFishSpawn = RandomUtil.randomNumberBetween(minTime, Math.max(minTime + 50, maxTime));
	}

	protected void stopFishing() {
		timeUntilFishSpawn = -1;
		hookTime = 0;
		lastFishDist = 0;

		if (hookedEntity != null) {
			if (hookedEntity == spawnedFish)
				hookedEntity.discard();

			hookedEntity = null;
		}

		if (spawnedFish != null) {
			spawnedFish.discard();
			spawnedFish = null;
		}

		if (!isRemoved())
			updateHookedEntity();
	}

	protected void spawnFish(ServerPlayer player) {
		boolean isLava = Fluids.LAVA.is(getApplicableFluid());
		Optional<HaulingSpawnPool> pool = HaulingSpawnPool.getPoolForLocation(level(), blockPosition(), isLava ? NeoForgeMod.LAVA_TYPE.value() : NeoForgeMod.WATER_TYPE.value());
		Optional<HaulingEntity> entry = pool.flatMap(pool2 -> pool2.getEntry(player, getLuck()));

		AoAEvents.fireCheckHaulingEntitySpawn(pool.orElse(null), entry.map(haulingEntity -> haulingEntity.apply(level(), isLava)).orElse(null), player, this).ifPresent(entity -> {
			if (entity instanceof Mob mob) {
				BlockPos pos = RandomUtil.getRandomPositionWithinRange(this.blockPosition(), 10, 10, 10, 2, 2, 2, false, level(), 5, (state, statePos) -> state.getFluidState().getType() == Fluids.WATER);

				mob.setPos(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
				mob.getNavigation().createPath(blockPosition(), 0);
				level().addFreshEntity(mob);
			}
			else {
				entity.setPos(getX(), getY() - entity.getBbHeight(), getZ());
				level().addFreshEntity(entity);
			}

			this.spawnedFish = entity;
		});
	}

	protected void checkIfCollided() {
		HitResult rayTrace = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

		if (rayTrace.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, rayTrace))
			this.onHit(rayTrace);
	}

	@Override
	protected boolean canHitEntity(Entity entity) {
		if (entity.isSpectator() || !entity.isAlive())
			return false;

		if (entity instanceof ItemEntity)
			return true;

		if (!entity.isPickable())
			return false;

		Entity owner = getOwner();

		return owner == null || !owner.isPassengerOfSameVehicle(entity);
	}

	private void handleSuperTick() {
		if (!this.leftOwner)
			this.leftOwner = checkLeftOwner();

		if (!level().isClientSide())
			setSharedFlag(6, hasGlowingTag());

		baseTick();
	}

	protected boolean checkStillValid(Player owner) {
		if (!isAlive())
			return false;

		if (owner == null || !owner.isAlive())
			return false;

		if (rod == null || rod.getItem() == Items.AIR)
			return false;

		float maxCastDistance = getMaxCastDistance();

		if (owner.distanceToSqr(this) > maxCastDistance * maxCastDistance)
			return false;

		return InventoryUtil.isHoldingItem(owner, rod.getItem());
	}

	@Override
	protected void onHitEntity(EntityHitResult rayTrace) {
		if (!level().isClientSide()) {
			this.hookedEntity = rayTrace.getEntity();

			updateHookedEntity();
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> param) {
		if (param.equals(DATA_HOOKED_ENTITY)) {
			int id = getEntityData().get(DATA_HOOKED_ENTITY);

			this.hookedEntity = id == -1 ? null : this.level().getEntity(id);
		}
		else if (param.equals(STATE)) {
			this.state = State.byValue(getEntityData().get(STATE));
		}
		else if (param.equals(DATA_POSE)) {
			this.refreshDimensions();
		}
	}

	protected void updateHookedEntity() {
		if (!level().isClientSide())
			getEntityData().set(DATA_HOOKED_ENTITY, this.hookedEntity == null ? -1 : this.hookedEntity.getId());
	}

	@Override
	public EntityType<?> getType() {
		return AoAMiscEntities.REINFORCED_BOBBER.get();
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return getType().getDimensions();
	}

	protected float getMaxCastDistance() {
		return 32;
	}

	protected float getCastStrength() {
		return 1;
	}

	public TagKey<Fluid> getApplicableFluid() {
		return FluidTags.WATER;
	}

	@Override
	public void handleEntityEvent(byte p_70103_1_) {}

	@Nullable
	@Override
	public Entity getHookedIn() {
		return this.hookedEntity;
	}

	@Override
	public void remove(RemovalReason pReason) {
		super.remove(pReason);

		stopFishing();
	}

	public enum State {
		MIDAIR(0),
		HOOKED_FISH(1),
		HOOKED_IN_ENTITY(2),
		IN_FLUID(3),
		STUCK(4);

		private final int value;

		State(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

		public static State byValue(int value) {
			return switch (value) {
				case 0 -> MIDAIR;
				case 1 -> HOOKED_FISH;
				case 2 -> HOOKED_IN_ENTITY;
				case 3 -> IN_FLUID;
				default -> STUCK;
			};
		}
	}
}
