package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.event.AoAPlayerEvents;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class FishingCageEntity extends Entity implements OwnableEntity {
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_1 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[0], (cage, stack) -> cage.loot[0] = stack);
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_2 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[1], (cage, stack) -> cage.loot[1] = stack);
	public static final EntityDataHolder<ItemStack> CAUGHT_STACK_3 = EntityDataHolder.register(FishingCageEntity.class, EntityDataSerializers.ITEM_STACK, ItemStack.EMPTY, cage -> cage.loot[2], (cage, stack) -> cage.loot[2] = stack);

	public static final ResourceKey<LootTable> FISHING_CAGE_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, AdventOfAscension.id("misc/fishing_cage_catches"));

	private UUID ownerUUID = null;
	private int damage;
	private final ItemStack[] loot = new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};

	public FishingCageEntity(Level world, Player player, ItemStack stack) {
		this(AoAMiscEntities.FISHING_CAGE.get(), world);

		setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
		setRot(player.getYRot(), player.getXRot());

		setDeltaMovement(EntityUtil.getDirectionForFacing(player).multiply(0.75f, 0.75f, 0.75f));

		this.ownerUUID = player.getUUID();
		this.damage = stack.getDamageValue();
	}

	public FishingCageEntity(EntityType<? extends Entity> entityType, Level world) {
		super(entityType, world);

		this.blocksBuilding = true;
	}

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.ownerUUID;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer pl && (ownerUUID == null || pl.getUUID().equals(ownerUUID))) {
			ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());
			int damage = this.damage + 1;

			if (damage < fishingCage.getMaxDamage()) {
				fishingCage.setDamageValue(damage);

				if (!pl.getAbilities().instabuild)
					InventoryUtil.giveItemTo(pl, fishingCage);
			}

			if (hasCatches()) {
				AoAPlayerEvents.handleCustomInteraction(pl, "fishing_cage_harvest", loot);

				for (ItemStack drop : loot) {
					if (drop.is(ItemTags.FISHES))
						pl.awardStat(Stats.FISH_CAUGHT, 1);

					InventoryUtil.giveItemTo(pl, drop);
				}
			}

			pl.awardStat(Stats.ITEM_USED.get(AoATools.FISHING_CAGE.get()));
			discard();

			return InteractionResult.SUCCESS;
		}

		return level().isClientSide() ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	public boolean canChangeDimensions(Level from, Level to) {
		return false;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		CAUGHT_STACK_1.defineDefault(builder);
		CAUGHT_STACK_2.defineDefault(builder);
		CAUGHT_STACK_3.defineDefault(builder);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (!CAUGHT_STACK_1.checkSync(this, key) && !CAUGHT_STACK_2.checkSync(this, key))
			CAUGHT_STACK_3.checkSync(this, key);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		if (this.ownerUUID != null)
			compound.putUUID("OwnerUUID", this.ownerUUID);

		compound.putInt("Damage", this.damage);

		ListTag lootList = new ListTag();

		for (int i = 0; i < 3; i++) {
			if (!this.loot[i].isEmpty())
				lootList.add(this.loot[i].save(registryAccess()));
		}

		if (!lootList.isEmpty())
			compound.put("loot", lootList);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("OwnerUUID"))
			this.ownerUUID = compound.getUUID("OwnerUUID");

		if (compound.contains("Damage", Tag.TAG_INT))
			this.damage = compound.getInt("Damage");

		if (compound.contains("loot")) {
			ListTag lootList = compound.getList("loot", Tag.TAG_COMPOUND);

			if (lootList.size() > 2)
				CAUGHT_STACK_3.set(this, ItemUtil.loadStackFromNbt(level(), lootList.getCompound(2)));

			if (lootList.size() > 1)
				CAUGHT_STACK_2.set(this, ItemUtil.loadStackFromNbt(level(), lootList.getCompound(1)));

			CAUGHT_STACK_1.set(this, ItemUtil.loadStackFromNbt(level(), lootList.getCompound(0)));
		}
	}

	@NotNull
	public ItemStack[] getLoot() {
		return this.loot;
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return tickCount > 1;
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		return AoATools.FISHING_CAGE.get().getDefaultInstance();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return !source.is(Tags.DamageTypes.IS_TECHNICAL);
	}

	protected void doFishingCheckTick() {
		if (level().isClientSide() || ownerUUID == null)
			return;

		if (!CAUGHT_STACK_3.is(this, ItemStack.EMPTY))
			return;

		if (!onGround() || !isInWater())
			return;

		if (RandomUtil.oneInNChance(5000)) {
			FluidState fluid = level().getFluidState(blockPosition());

			if (!(fluid.getType() instanceof FlowingFluid flowingFluid)) {
				if (!RandomUtil.oneInNChance(10))
					return;
			}
			else if (flowingFluid.getFlow(level(), blockPosition(), fluid).lengthSqr() == 0 && !RandomUtil.fiftyFifty()) {
				return;
			}

			if (!RandomUtil.oneInNChance(Math.max(1, EntityRetrievalUtil.getEntities(this, 5, entity -> entity instanceof FishingCageEntity).size())))
				return;

			Player owner = level().getPlayerByUUID(this.ownerUUID);

			if (owner != null) {
				LootParams.Builder lootContext = new LootParams.Builder((ServerLevel)this.level())
						.withParameter(LootContextParams.ORIGIN, this.position())
						.withParameter(LootContextParams.TOOL, new ItemStack(AoATools.FISHING_CAGE.get()))
						.withParameter(LootContextParams.THIS_ENTITY, this)
						.withParameter(LootContextParams.ATTACKING_ENTITY, owner)
						.withLuck(2 + owner.getLuck());
				LootTable lootTable = level().getServer().reloadableRegistries().getLootTable(FISHING_CAGE_LOOT_TABLE);
				List<ItemStack> loot = lootTable.getRandomItems(lootContext.create(LootContextParamSets.FISHING));

				for (int i = 0; i < 3 && i < loot.size(); i++) {
					if (CAUGHT_STACK_1.is(this, ItemStack.EMPTY)) {
						CAUGHT_STACK_1.set(this, loot.get(i));
					}
					else if (CAUGHT_STACK_2.is(this, ItemStack.EMPTY)) {
						CAUGHT_STACK_2.set(this, loot.get(i));
					}
					else {
						CAUGHT_STACK_3.set(this, loot.get(i));
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		boolean wasInWater = isInWater();

		baseTick();
		doFishingCheckTick();

		Vec3 velocity = getDeltaMovement();
		double gravity = 0.08d;

		if (isInWater()) {
			double yPos = this.getY();

			if (!wasInWater) {
				setDeltaMovement(velocity.multiply(0.1f, 0.1f, 0.1f));
			}

			if (velocity.y() < -0.023f)
				level().addParticle(ParticleTypes.BUBBLE, getX() + random.nextGaussian() * getBbWidth() * 0.5f, getY(), getZ() + random.nextGaussian() * getBbWidth() * 0.5f, 0, 0, 0);

			move(MoverType.SELF, getDeltaMovement());

			Vec3 motion = getDeltaMovement().multiply(0.8f, 0.8f, 0.8f);
			double yVelocity;

			if (velocity.y() <= 0 && Math.abs(motion.y() - 0.005D) >= 0.003d && Math.abs(motion.y() - gravity / 16d) < 0.003d) {
				yVelocity = -0.003d;
			}
			else {
				yVelocity = motion.y() - gravity / 16d;
			}

			Vec3 newVelocity = new Vec3(motion.x(), yVelocity, motion.z());

			setDeltaMovement(newVelocity);

			if (horizontalCollision && isFree(newVelocity.x(), newVelocity.y() + (double)0.6f - getY() + yPos, newVelocity.z()))
				setDeltaMovement(newVelocity.x(), 0.3F, newVelocity.z());
		}
		else {
			BlockPos feetPos = getBlockPosBelowThatAffectsMyMovement();
			float blockSlipperiness = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getFriction(level(), this.getBlockPosBelowThatAffectsMyMovement(), this);
			float friction = this.onGround() ? blockSlipperiness * 0.91f : 0.91f;

			move(MoverType.SELF, getDeltaMovement());

			Vec3 newVelocity = getDeltaMovement();
			double newYVelocity = newVelocity.y();

			if (this.level().isClientSide && !this.level().hasChunkAt(feetPos)) {
				if (getY() > 0) {
					newYVelocity = -0.1d;
				}
				else {
					newYVelocity = 0;
				}
			}
			else if (!this.isNoGravity()) {
				newYVelocity -= gravity;
			}

			setDeltaMovement(newVelocity.x() * (double)friction, newYVelocity * (double)0.98f, newVelocity.z() * (double)friction);
		}

		setDeltaMovement(getDeltaMovement().multiply(0.98f, 0.98f, 0.98f));

		if (!level().isClientSide())
			setSharedFlag(6, hasGlowingTag());
	}

	@Override
	public void baseTick() {
		this.level().getProfiler().push("entityBaseTick");

		updateInWaterStateAndDoFluidPushing();

		if (!level().isClientSide()) {
			if (getY() < level().getMinBuildHeight() - 20)
				onBelowWorld();

			if (isInLava())
				lavaHurt();

			if (isAlive() && tickCount > 40 && onGround() && !isInWater()) {
				ItemStack fishingCage = new ItemStack(AoATools.FISHING_CAGE.get());

				if (hasCatches())
					this.damage += 1;

				fishingCage.setDamageValue(this.damage);
				spawnAtLocation(fishingCage);
				discard();
			}
		}

		this.level().getProfiler().pop();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (isInvulnerableTo(source))
			return false;

		if (!level().isClientSide() && amount >= 1) {
			markHurt();
			discard();
		}

		return true;
	}

	@Override
	public void remove(RemovalReason reason) {
		if (!level().isClientSide() && hasCatches()) {
			for (ItemStack stack : loot) {
				spawnAtLocation(stack);
			}
		}

		super.remove(reason);
	}

	public boolean hasCatches() {
		return !CAUGHT_STACK_1.is(this, ItemStack.EMPTY);
	}
}
