package net.tslat.aoa3.client.model.entities.mobs.abyss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSlimer extends ModelBase {
	private ModelRenderer body1;
	private ModelRenderer rightArm1;
	private ModelRenderer leftArm1;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer leftArm4;
	private ModelRenderer rightArm5;
	private ModelRenderer leftArm5;
	private ModelRenderer rightArm6;
	private ModelRenderer leftArm6;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer body6;

	public ModelSlimer() {
		textureWidth = 128;
		textureHeight = 32;
		(body1 = new ModelRenderer(this, 62, 16)).addBox(5.0f, -1.0f, -3.0f, 3, 2, 4);
		body1.setRotationPoint(0.0f, 0.0f, 0.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 105, 9)).addBox(1.0f, -2.0f, -3.0f, 2, 5, 6);
		rightArm1.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm1.setTextureSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 105, 9)).addBox(-3.0f, -2.0f, -3.0f, 2, 5, 6);
		leftArm1.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm1.setTextureSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 47, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
		rightLeg.setRotationPoint(-4.0f, 15.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 47, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
		leftLeg.setRotationPoint(4.0f, 15.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 61, 16)).addBox(1.0f, 14.0f, -3.0f, 0, 5, 6);
		rightArm2.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 61, 16)).addBox(5.0f, 14.0f, -3.0f, 0, 5, 6);
		leftArm2.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 78, 0)).addBox(-5.0f, -1.0f, -3.0f, 6, 15, 6);
		rightArm3.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm3.setTextureSize(128, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 78, 0)).addBox(-1.0f, -1.0f, -3.0f, 6, 15, 6);
		leftArm3.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm3.setTextureSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 61, 27)).addBox(-5.0f, 14.0f, -3.0f, 6, 5, 0);
		rightArm4.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm4.setTextureSize(128, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 61, 27)).addBox(-1.0f, 14.0f, -3.0f, 6, 5, 0);
		leftArm4.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm4.setTextureSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 61, 27)).addBox(-5.0f, 14.0f, 3.0f, 6, 5, 0);
		rightArm5.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm5.setTextureSize(128, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 61, 27)).addBox(-1.0f, 14.0f, 3.0f, 6, 5, 0);
		leftArm5.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm5.setTextureSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 61, 16)).addBox(-5.0f, 14.0f, -3.0f, 0, 5, 6);
		rightArm6.setRotationPoint(-11.0f, 2.0f, 0.0f);
		rightArm6.setTextureSize(128, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 61, 16)).addBox(-1.0f, 14.0f, -3.0f, 0, 5, 6);
		leftArm6.setRotationPoint(11.0f, 2.0f, 0.0f);
		leftArm6.setTextureSize(128, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 0)).addBox(-8.0f, 0.0f, -3.0f, 16, 14, 6);
		body2.setRotationPoint(0.0f, 1.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 0, 30)).addBox(-5.0f, -1.0f, -3.0f, 10, 2, 0);
		body3.setRotationPoint(0.0f, 0.0f, 0.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 21, 28)).addBox(-8.0f, -1.0f, 1.0f, 16, 2, 2);
		body4.setRotationPoint(0.0f, 0.0f, 0.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 46, 16)).addBox(-8.0f, -1.0f, -3.0f, 3, 2, 4);
		body5.setRotationPoint(0.0f, 0.0f, 0.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 1, 21)).addBox(-8.0f, -2.0f, -3.0f, 16, 1, 6);
		body6.setRotationPoint(0.0f, 0.0f, 0.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body1.render(par7);
		rightArm1.render(par7);
		leftArm1.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		rightArm3.render(par7);
		leftArm3.render(par7);
		rightArm4.render(par7);
		leftArm4.render(par7);
		rightArm5.render(par7);
		leftArm5.render(par7);
		rightArm6.render(par7);
		leftArm6.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm1.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		leftArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm3.rotateAngleZ = 0.0f;
		leftArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm4.rotateAngleZ = 0.0f;
		leftArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm5.rotateAngleZ = 0.0f;
		leftArm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm6.rotateAngleZ = 0.0f;
		rightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm1.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm4.rotateAngleZ = 0.0f;
		rightArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm5.rotateAngleZ = 0.0f;
		rightArm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm6.rotateAngleZ = 0.0f;
	}
}