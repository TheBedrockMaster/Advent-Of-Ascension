package net.tslat.aoa3.client.model.entities.boss.kingshroomus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelKingShroomus extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head2;
	private ModelRenderer leftArm;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm3;
	private ModelRenderer leftArm4;
	private ModelRenderer leftArm5;
	private ModelRenderer leftArm6;
	private ModelRenderer leftArm7;
	private ModelRenderer leftArm8;
	private ModelRenderer leftArm9;
	private ModelRenderer leftArm10;
	private ModelRenderer leftArm11;
	private ModelRenderer leftArm12;
	private ModelRenderer leftArm13;
	private ModelRenderer leftArm14;
	private ModelRenderer leftArm15;
	private ModelRenderer leftArm16;
	private ModelRenderer leftArm17;
	private ModelRenderer leftArm18;

	public ModelKingShroomus() {
		textureWidth = 256;
		textureHeight = 64;
		(head = new ModelRenderer(this, 116, 0)).addBox(-8.0f, -12.0f, -8.0f, 16, 12, 16);
		head.setRotationPoint(0.0f, -16.0f, 0.0f);
		head.setTextureSize(256, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 94, 38)).addBox(-7.0f, 0.0f, -2.0f, 14, 18, 6);
		body.setRotationPoint(0.0f, -8.0f, -1.0f);
		body.setTextureSize(256, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 42)).addBox(-3.0f, 0.0f, -3.0f, 6, 14, 6);
		rightLeg.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightLeg.setTextureSize(256, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 42)).addBox(-3.0f, 0.0f, -3.0f, 6, 14, 6);
		leftLeg.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftLeg.setTextureSize(256, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, -8.0f, 0.0f);
		head2.setTextureSize(256, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 200, 30)).addBox(-2.0f, -3.0f, -3.0f, 6, 6, 6);
		leftArm.setRotationPoint(-18.0f, 10.0f, 0.0f);
		leftArm.setTextureSize(256, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 45)).addBox(-3.0f, -10.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-7.0f, -6.0f, 0.0f);
		rightArm.setTextureSize(256, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, -1.047198f);
		(leftArm2 = new ModelRenderer(this, 40, 45)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setRotationPoint(7.0f, 6.0f, 0.0f);
		leftArm2.setTextureSize(256, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, -1.047198f);
		(rightArm2 = new ModelRenderer(this, 40, 45)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setRotationPoint(-7.0f, 6.0f, 0.0f);
		rightArm2.setTextureSize(256, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 1.047198f);
		(leftArm3 = new ModelRenderer(this, 40, 45)).addBox(-1.0f, -10.0f, -2.0f, 4, 12, 4);
		leftArm3.setRotationPoint(7.0f, -6.0f, 0.0f);
		leftArm3.setTextureSize(256, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 1.047198f);
		(leftArm4 = new ModelRenderer(this, 227, 30)).addBox(-2.0f, -3.0f, -3.0f, 6, 6, 6);
		leftArm4.setRotationPoint(16.0f, 10.0f, 0.0f);
		leftArm4.setTextureSize(256, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 200, 16)).addBox(-2.0f, -3.0f, -3.0f, 6, 6, 6);
		leftArm5.setRotationPoint(-18.0f, -10.0f, 0.0f);
		leftArm5.setTextureSize(256, 64);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 227, 16)).addBox(-2.0f, -3.0f, -3.0f, 6, 6, 6);
		leftArm6.setRotationPoint(16.0f, -10.0f, 0.0f);
		leftArm6.setTextureSize(256, 64);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new ModelRenderer(this, 204, 46)).addBox(-1.0f, 6.0f, -13.0f, 4, 4, 4);
		leftArm7.setRotationPoint(-18.0f, -10.0f, 0.0f);
		leftArm7.setTextureSize(256, 64);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(leftArm8 = new ModelRenderer(this, 226, 46)).addBox(-1.0f, 6.0f, -13.0f, 4, 4, 4);
		leftArm8.setRotationPoint(16.0f, -10.0f, 0.0f);
		leftArm8.setTextureSize(256, 64);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0f, 0.0f, 0.0f);
		(leftArm9 = new ModelRenderer(this, 226, 55)).addBox(-1.0f, 6.0f, -13.0f, 4, 4, 4);
		leftArm9.setRotationPoint(16.0f, 10.0f, 0.0f);
		leftArm9.setTextureSize(256, 64);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0f, 0.0f, 0.0f);
		(leftArm10 = new ModelRenderer(this, 204, 55)).addBox(-1.0f, 6.0f, -13.0f, 4, 4, 4);
		leftArm10.setRotationPoint(-18.0f, 10.0f, 0.0f);
		leftArm10.setTextureSize(256, 64);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0f, 0.0f, 0.0f);
		(leftArm11 = new ModelRenderer(this, 63, 49)).addBox(-1.0f, 0.0f, -2.0f, 4, 10, 4);
		leftArm11.setRotationPoint(16.0f, -10.0f, 0.0f);
		leftArm11.setTextureSize(256, 64);
		leftArm11.mirror = true;
		setRotation(leftArm11, 0.0f, 0.0f, 0.0f);
		(leftArm12 = new ModelRenderer(this, 63, 49)).addBox(-1.0f, 0.0f, -2.0f, 4, 10, 4);
		leftArm12.setRotationPoint(16.0f, 10.0f, 0.0f);
		leftArm12.setTextureSize(256, 64);
		leftArm12.mirror = true;
		setRotation(leftArm12, 0.0f, 0.0f, 0.0f);
		(leftArm13 = new ModelRenderer(this, 63, 49)).addBox(-1.0f, 0.0f, -2.0f, 4, 10, 4);
		leftArm13.setRotationPoint(-18.0f, 10.0f, 0.0f);
		leftArm13.setTextureSize(256, 64);
		leftArm13.mirror = true;
		setRotation(leftArm13, 0.0f, 0.0f, 0.0f);
		(leftArm14 = new ModelRenderer(this, 63, 49)).addBox(-1.0f, 0.0f, -2.0f, 4, 10, 4);
		leftArm14.setRotationPoint(-18.0f, -10.0f, 0.0f);
		leftArm14.setTextureSize(256, 64);
		leftArm14.mirror = true;
		setRotation(leftArm14, 0.0f, 0.0f, 0.0f);
		(leftArm15 = new ModelRenderer(this, 40, 16)).addBox(0.0f, 7.0f, -9.0f, 2, 2, 10);
		leftArm15.setRotationPoint(16.0f, -10.0f, 0.0f);
		leftArm15.setTextureSize(256, 64);
		leftArm15.mirror = true;
		setRotation(leftArm15, 0.0f, 0.0f, 0.0f);
		(leftArm16 = new ModelRenderer(this, 40, 16)).addBox(0.0f, 7.0f, -9.0f, 2, 2, 10);
		leftArm16.setRotationPoint(16.0f, 10.0f, 0.0f);
		leftArm16.setTextureSize(256, 64);
		leftArm16.mirror = true;
		setRotation(leftArm16, 0.0f, 0.0f, 0.0f);
		(leftArm17 = new ModelRenderer(this, 40, 16)).addBox(0.0f, 7.0f, -9.0f, 2, 2, 10);
		leftArm17.setRotationPoint(-18.0f, 10.0f, 0.0f);
		leftArm17.setTextureSize(256, 64);
		leftArm17.mirror = true;
		setRotation(leftArm17, 0.0f, 0.0f, 0.0f);
		(leftArm18 = new ModelRenderer(this, 40, 16)).addBox(0.0f, 7.0f, -9.0f, 2, 2, 10);
		leftArm18.setRotationPoint(-18.0f, -10.0f, 0.0f);
		leftArm18.setTextureSize(256, 64);
		leftArm18.mirror = true;
		setRotation(leftArm18, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		head2.render(par7);
		leftArm.render(par7);
		rightArm.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
		leftArm3.render(par7);
		leftArm4.render(par7);
		leftArm5.render(par7);
		leftArm6.render(par7);
		leftArm7.render(par7);
		leftArm8.render(par7);
		leftArm9.render(par7);
		leftArm10.render(par7);
		leftArm11.render(par7);
		leftArm12.render(par7);
		leftArm13.render(par7);
		leftArm14.render(par7);
		leftArm15.render(par7);
		leftArm16.render(par7);
		leftArm17.render(par7);
		leftArm18.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}