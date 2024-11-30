package net.tslat.aoa3.content.entity.monster.overworld;

import com.google.common.base.Suppliers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootTable;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityDataSerializers;
import net.tslat.aoa3.common.registration.entity.AoAEntitySpawnPlacements;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.variant.ChargerVariant;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class ChargerEntity extends AoAMeleeMob<ChargerEntity> {
	private static final EntityDataHolder<ChargerVariant> VARIANT = EntityDataHolder.register(ChargerEntity.class, AoAEntityDataSerializers.CHARGER_VARIANT.get(), ChargerVariant.PLAINS.get(), entity -> entity.variant, (entity, value) -> entity.variant = value);

	private ChargerVariant variant = ChargerVariant.PLAINS.get();

	public ChargerEntity(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		registerDataParams(builder, VARIANT);
	}

	@Override
	public BrainActivityGroup<ChargerEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.125f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData);

		VARIANT.set(this, ChargerVariant.getVariantForSpawn(world.getLevel(), difficulty, reason, this, Suppliers.memoize(() -> level().getBiome(blockPosition())), spawnData));

		return spawnData;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CHARGER_HURT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	public ChargerVariant getVariant() {
		return this.variant;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putString("Variant", AoARegistries.CHARGER_VARIANTS.getKey(VARIANT.get(this)).toString());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Variant", Tag.TAG_STRING))
			VARIANT.set(this, AoARegistries.CHARGER_VARIANTS.getEntry(ResourceLocation.tryParse(compound.getString("Variant"))));
	}

	@Override
	protected ResourceKey<LootTable> getDefaultLootTable() {
		return getVariant().lootTable().orElseGet(super::getDefaultLootTable);
	}

	public static SpawnPlacements.SpawnPredicate<Mob> spawnRules() {
		return AoAEntitySpawnPlacements.SpawnBuilder.DEFAULT_DAY_MONSTER;
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<ChargerEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(16)
				.moveSpeed(0.31)
				.meleeStrength(6)
				.followRange(30)
				.aggroRange(12);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(AoAAnimations.genericWalkRunSwimIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
	}
}
