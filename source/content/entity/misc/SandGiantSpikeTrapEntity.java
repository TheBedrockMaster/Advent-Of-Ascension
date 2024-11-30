package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class SandGiantSpikeTrapEntity extends BasicMiscEntity {
	private static final EntityPredicate<Entity> damagePredicate = new EntityPredicate<>().isAlive().isNot(AoAMonsters.SAND_GIANT.get()).isDamageable();

	public SandGiantSpikeTrapEntity(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	public SandGiantSpikeTrapEntity(Level level, Vec3 pos) {
		super(AoAMiscEntities.SAND_GIANT_SPIKE_TRAP.get(), level);

		setPos(pos);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid(FluidType type) {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public void checkDespawn() {
		if (level().getDifficulty() == Difficulty.PEACEFUL)
			discard();
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide()) {
			if (tickCount > 6000) {
				discard();

				return;
			}

			if (tickCount > 28 && tickCount % 10 == 0) {
				for (Entity entity : EntityRetrievalUtil.<Entity>getEntities(level(), getBoundingBox(), damagePredicate)) {
					entity.hurt(level().damageSources().stalagmite(), 3);
				}
			}
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.getSpawnController(this, state -> this, 41));
	}
}
