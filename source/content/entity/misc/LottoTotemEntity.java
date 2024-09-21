package net.tslat.aoa3.content.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LootUtil;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class LottoTotemEntity extends Entity implements OwnableEntity {
	private UUID winnerUUID = null;
	private UUID ownerUUID = null;

	public LottoTotemEntity(Level world, BlockPos pos, UUID winnerUUID, UUID ownerUUID) {
		this(AoAMiscEntities.LOTTO_TOTEM.get(), world);

		this.winnerUUID = winnerUUID;
		this.ownerUUID = ownerUUID;
		VoxelShape floorShape = world.getBlockState(pos).getShape(world, pos);

		setPos(pos.getX() + 0.5d, pos.getY() + floorShape.max(Direction.Axis.Y), pos.getZ() + 0.5d);
	}

	public LottoTotemEntity(EntityType<? extends Entity> entityType, Level world) {
		super(entityType, world);

		blocksBuilding = true;
	}

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.ownerUUID;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		return tickCount >= 12000 || (!level().isClientSide && (ownerUUID == null || player.getUUID().equals(ownerUUID))) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}

	@Override
	public boolean canChangeDimensions(Level from, Level to) {
		return false;
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
		if (isAlive() && (ownerUUID == null || player.getUUID().equals(ownerUUID))) {
			if (player instanceof ServerPlayer serverPlayer) {
				if (winnerUUID != null && winnerUUID.equals(getUUID())) {
					for (ItemStack stack : LootUtil.generateLoot(AdventOfAscension.id("misc/lotto_totem"), LootUtil.getGiftParameters(serverPlayer.serverLevel(), position(), 5, serverPlayer))) {
						ItemEntity drop = spawnAtLocation(stack, 0);

						if (drop != null)
							drop.setThrower(serverPlayer);

						AdvancementUtil.grantCriterion(serverPlayer, AdventOfAscension.id("completionist/winner_winner"), "lotto_win");
					}

					ItemEntity drop = spawnAtLocation(new ItemStack(AoABlocks.LOTTO_BANNER.base()), 0);

					if (drop != null)
						drop.setThrower(serverPlayer);

					level().playSound(null, blockPosition(), AoASounds.LOTTO_WIN.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
				}

				for (LottoTotemEntity totem : level().getEntitiesOfClass(LottoTotemEntity.class, new AABB(blockPosition()).inflate(2d))) {
					totem.discard();
				}
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	public void onRemovedFromLevel() {
		super.onRemovedFromLevel();

		if (level().isClientSide && !isAlive()) {
			for (int i = 0; i < 3; i++) {
				level().addParticle(ParticleTypes.LARGE_SMOKE, getX(), getY() + 0.3d, getZ(), 0, 0.1, 0);
			}
		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		if (compound.hasUUID("WinningUUID"))
			winnerUUID = compound.getUUID("WinningUUID");

		if (compound.hasUUID("OwnerUUID"))
			ownerUUID = compound.getUUID("OwnerUUID");
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (winnerUUID != null)
			compound.putUUID("WinningUUID", winnerUUID);

		if (ownerUUID != null)
			compound.putUUID("OwnerUUID", ownerUUID);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return true;
	}

	@Nullable
	@Override
	public ItemStack getPickResult() {
		return AoAItems.LOTTO_TOTEM.get().getDefaultInstance();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return !source.is(Tags.DamageTypes.IS_TECHNICAL);
	}

	@Override
	public void tick() {
		baseTick();
	}

	@Override
	public void baseTick() {
		this.level().getProfiler().push("entityBaseTick");

		if (getY() < -64.0D)
			onBelowWorld();

		this.level().getProfiler().pop();
	}
}
