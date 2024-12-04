package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.content.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.content.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;


public class BaseBullet extends ThrowableProjectile implements HardProjectile {
	private float dmgMulti;
	private int lifespan;
	private int pierceCount;
	private int age;
	private BaseGun weapon;
	private Entity lastPierceTarget;
	private InteractionHand hand;

	private Entity cachedOwner = null;

	public BaseBullet(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
		this.age = 0;
	}

	public BaseBullet(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, BaseGun gun, float dmgMultiplier, int piercingValue, float velocity) {
		super(entityType, shooter.level());
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = 60;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = InteractionHand.MAIN_HAND;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());

		Vec3 velocityVector = EntityUtil.getVelocityVectorForFacing(this);

		shoot(velocityVector.x(), velocityVector.y(), velocityVector.z(), velocity, 0.0f);
	}

	public BaseBullet(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(entityType, shooter.level());
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());

		boolean right = true;

		if (shooter instanceof Player) {
			if (hand == InteractionHand.MAIN_HAND) {
				if (shooter.getMainArm() == HumanoidArm.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HumanoidArm.RIGHT)
					right = false;
			}
		}

		setOwner(shooter);
		shoot(-Mth.sin(getYRot() * Mth.DEG_TO_RAD) * Mth.cos(getXRot() * Mth.DEG_TO_RAD) + xMod, -Mth.sin(getXRot() * Mth.DEG_TO_RAD) + yMod, Mth.cos(getYRot() * Mth.DEG_TO_RAD) * Mth.cos(getXRot() * Mth.DEG_TO_RAD) + zMod,
				3.0f,2.0f);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(Mth.cos(getYRot() / Mth.RAD_TO_DEG) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(Mth.sin(getYRot() / Mth.RAD_TO_DEG) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(Mth.cos(getYRot() / Mth.RAD_TO_DEG) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(Mth.sin(getYRot() / Mth.RAD_TO_DEG) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableProjectile> entityType, LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue) {
		super(entityType, shooter.level());
		this.age = 0;
		this.dmgMulti = dmgMultiplier;
		this.lifespan = maxAge;
		this.pierceCount = piercingValue;
		this.weapon = gun;
		this.hand = hand;

		setOwner(shooter);
		moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());

		boolean right = shooter.getMainArm() == HumanoidArm.RIGHT;

		if (shooter instanceof Player) {
			if (hand == InteractionHand.MAIN_HAND) {
				if (shooter.getMainArm() == HumanoidArm.LEFT)
					right = false;
			}
			else {
				if (shooter.getMainArm() == HumanoidArm.RIGHT)
					right = false;
			}
		}

		shoot(-Mth.sin(getYRot() * Mth.DEG_TO_RAD) * Mth.cos(getXRot() * Mth.DEG_TO_RAD), -Mth.sin(getXRot() * Mth.DEG_TO_RAD), Mth.cos(getYRot() * Mth.DEG_TO_RAD) * Mth.cos(getXRot() * Mth.DEG_TO_RAD),
				3f,0);

		if (right) {
			setPos(getDeltaMovement().x() * 0.5f + getX() - ((double)(Mth.cos(getYRot() / Mth.RAD_TO_DEG) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() + ((double)(Mth.sin(getYRot() / Mth.RAD_TO_DEG) * 0.4F)));
		}
		else {
			setPos(getDeltaMovement().x() * 0.5f + getX() + ((double)(Mth.cos(getYRot() / Mth.RAD_TO_DEG) * 0.4F)), getDeltaMovement().y() * 0.5f + getY() - 0.3D, getDeltaMovement().z() * 0.5f + getZ() - ((double)(Mth.sin(getYRot() / Mth.RAD_TO_DEG) * 0.4F)));
		}
	}

	public BaseBullet(EntityType<? extends ThrowableProjectile> entityType, Level world, double x, double y, double z) {
		super(entityType, world);

		age = 0;
		lifespan = 60;

		setPos(x, y, z);
	}

	@Nullable
	@Override
	public Entity getOwner() {
		if (this.cachedOwner != null && this.cachedOwner.isAlive())
			return this.cachedOwner;

		this.cachedOwner = super.getOwner();

		return this.cachedOwner;
	}

	@Override
	public void shoot(double directionX, double directionY, double directionZ, float velocity, float inaccuracy) {
		Vec3 motionVec = (new Vec3(directionX, directionY, directionZ)).normalize().add(random.nextGaussian() * 0.0075F * inaccuracy, random.nextGaussian() * 0.0075F * inaccuracy, random.nextGaussian() * (double)0.0075F * inaccuracy).scale(velocity);

		setDeltaMovement(motionVec);

		double lat = motionVec.horizontalDistance();
		setYRot((float)(Mth.atan2(motionVec.x, motionVec.z) * Mth.RAD_TO_DEG));
		setXRot((float)(Mth.atan2(motionVec.y, lat) * Mth.RAD_TO_DEG));
		this.yRotO = getYRot();
		this.xRotO = getXRot();
		this.hasImpulse = true;
	}

	@Override
	protected void onHitBlock(BlockHitResult rayTrace) {
		BlockPos resultPos = rayTrace.getBlockPos();
		BlockState block = level().getBlockState(resultPos);

		if (!level().isClientSide) {
			TELParticlePacket packet = new TELParticlePacket(5);

			for (int i = 0; i < 5; i++) {
				packet.particle(ParticleBuilder.forPositions(new BlockParticleOption(ParticleTypes.BLOCK, block), rayTrace.getLocation()).scaleMod((float) RandomUtil.randomValueBetween(0.5f, 0.75f)).cutoffDistance(64));
			}

			packet.sendToAllPlayersTrackingEntity((ServerLevel)level(), this);
		}

		block.onProjectileHit(level(), block, rayTrace, this);

		if (AoAGameRules.checkDestructiveWeaponPhysics(level())) {
			float hardness = block.getDestroySpeed(level(), resultPos);

			if (hardness >= 0 && hardness <= 0.3f) {
				if (random.nextBoolean()) {
					level().destroyBlock(resultPos, true);
				}
				else {
					level().setBlockAndUpdate(resultPos, Blocks.AIR.defaultBlockState());
				}

				if (random.nextFloat() > hardness / 1.5f)
					return;
			}
		}

		if (!block.blocksMotion())
			return;

		doBlockImpact(rayTrace.getLocation(), rayTrace.getDirection(), rayTrace.getBlockPos());
		discard();
	}

	@Override
	protected void onHitEntity(EntityHitResult rayTrace) {
		Entity entityResult = rayTrace.getEntity();

		if (entityResult != lastPierceTarget) {
			if (this.weapon != null && getOwner() instanceof LivingEntity owner)
				weapon.doImpactDamage(entityResult, owner, this, rayTrace.getLocation(), dmgMulti);

			doEntityImpact(entityResult, rayTrace.getLocation());

			pierceCount--;
		}

		if (pierceCount >= 0) {
			lastPierceTarget = entityResult;

			return;
		}

		discard();
	}

	@Override
	protected void onHit(HitResult result) {
		if (level().isClientSide || result.getType() == HitResult.Type.MISS || !isAlive())
			return;

		if (result.getType() == HitResult.Type.BLOCK) {
			onHitBlock((BlockHitResult)result);
		}
		else {
			onHitEntity((EntityHitResult)result);
		}
	}

	public void doEntityImpact(Entity target, Vec3 impactLocation) {}

	@Override
	public void doBlockImpact(Vec3 impactLocation, Direction face, BlockPos blockPos) {}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {}

	@Override
	public void tick() {
		if (!isAlive())
			return;

		Vec3 motion = getDeltaMovement();
		Vec3 position;
		AABB boundingBox;

		if (tickCount == 1 && !(weapon instanceof BaseThrownWeapon)) {
			float mod = weapon instanceof BaseSniper ? 0.05f : 0.5f;

			position = new Vec3(getX() - motion.x() * mod, getY() - motion.y() * mod, getZ() - motion.z() * mod);
			boundingBox = new AABB(position.x(), position.y(), position.z(), getX(), getY(), getZ());
		}
		else {
			boundingBox = getBoundingBox();
			position = new Vec3(getX(), getY(), getZ());
		}

		Vec3 velocityAdjustedPosition = new Vec3(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		HitResult intersectedBlocksTrace = level().clip(new ClipContext(position, velocityAdjustedPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

		if (intersectedBlocksTrace.getType() != HitResult.Type.MISS) {
			velocityAdjustedPosition = new Vec3(intersectedBlocksTrace.getLocation().x, intersectedBlocksTrace.getLocation().y, intersectedBlocksTrace.getLocation().z);
		}
		else {
			velocityAdjustedPosition = new Vec3(getX() + motion.x(), getY() + motion.y(), getZ() + motion.z());
		}

		Entity shooter = getOwner();
		EntityHitResult entityTrace = EntityUtil.getEntityCollisionWithPrecision(level(), this, position, velocityAdjustedPosition, boundingBox.expandTowards(motion.x(), motion.y(), motion.z()).inflate(0.1d), entity -> entity.isAlive() && entity.isPickable() && !entity.isSpectator() && entity != shooter, 0.3f);

		if (entityTrace != null)
			intersectedBlocksTrace = entityTrace;

		if (intersectedBlocksTrace.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, intersectedBlocksTrace))
			onHit(intersectedBlocksTrace);

		if (!isAlive())
			return;

		xOld = getX();
		yOld = getY();
		zOld = getZ();

		super.tick();

		if (!level().isClientSide) {
			if (age > lifespan) {
				discard();
			}
			else {
				age++;
			}
		}
	}

	@Override
	protected double getDefaultGravity() {
		return 0.0f;
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}

	public InteractionHand getHand() {
		return hand;
	}

	public BaseBullet setMaxAge(final int maxAge) {
		this.lifespan = maxAge;
		return this;
	}

	public BaseBullet setPierceCount(final int pierceCount) {
		this.pierceCount = pierceCount;
		return this;
	}

	public int getAge() {
		return age;
	}

	@Nullable
	public BaseGun getWeapon() {
		return weapon;
	}

	public ItemStack getWeaponStack(Item fallback) {
		if (getWeapon() == null || !(getOwner() instanceof LivingEntity owner))
			return fallback.getDefaultInstance();

		ItemStack heldStack = owner.getItemInHand(getHand());

		if (heldStack.is(getWeapon()))
			return heldStack;

		return fallback.getDefaultInstance();
	}
}
