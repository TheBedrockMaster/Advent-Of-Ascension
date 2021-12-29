package net.nevermine.mob.entity.lunalus;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.mob.ai.EntityAIFlying;

import java.util.List;

public class EntityExplodot extends EntityAIFlying {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;

	public EntityExplodot(final World var1) {
		super(var1);
		final double moveSpeed = 0.3499999940395355;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		tasks.addTask(9, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		flyTimer = 0;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (EntityPlayer pl : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(2.0, 2.0, 2.0))) {
			if (pl.capabilities.isCreativeMode)
				continue;

			if (!canEntityBeSeen(pl))
				continue;

			worldObj.createExplosion(this, posX, posY, posZ, 3.0f, false);

			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3499999940395355);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95f;
	}

	protected String getLivingSound() {
		return "nevermine:ExplodotLiving";
	}

	protected String getHurtSound() {
		return "nevermine:ExplodotHit";
	}

	protected String getDeathSound() {
		return "nevermine:ExplodotDeath";
	}

	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.6000000238418579;
	}

	protected void updateAITasks() {
		super.updateAITasks();
		if (getAttackTarget() != null) {
			final int var1 = (int)getAttackTarget().posX;
			final int var2 = (int)getAttackTarget().posY;
			final int var3 = (int)getAttackTarget().posZ;
			currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
		}
		else if (flyTimer != 0) {
			flyTimer = 120;
			currentFlightTarget = new ChunkCoordinates((int)(posX + rand.nextInt(16)) - 8, (int)(posY + rand.nextInt(32)) - 16, (int)(posZ + rand.nextInt(16)) - 8);
		}
		if (currentFlightTarget != null) {
			final double var4 = currentFlightTarget.posX - posX;
			final double var5 = currentFlightTarget.posY + rand.nextFloat() + 1.0 - posY;
			final double var6 = currentFlightTarget.posZ - posZ;
			if (Math.signum(var4) != 0.0 || Math.signum(var5) != 0.0 || Math.signum(var6) != 0.0) {
				motionX += (Math.signum(var4) * 0.15 - motionX) * 0.10000000149011612;
				motionY += (Math.signum(var5) * 1.699999988079071 - motionY) * 0.10000000149011612;
				motionZ += (Math.signum(var6) * 0.15 - motionZ) * 0.10000000149011612;
				final float var7 = (float)(Math.atan2(motionZ, motionX) * 180.0 / 3.141592653589793) - 90.0f;
				final float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
				moveForward = 0.5f;
				rotationYaw += var8;
			}
			--flyTimer;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(final float par1) {
	}

	@Override
	protected void updateFallState(final double par1, final boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public void onCollideWithPlayer(final EntityPlayer par1EntityPlayer) {
		if (!isDead)
			attackEntityAsMob(par1EntityPlayer);
	}
}