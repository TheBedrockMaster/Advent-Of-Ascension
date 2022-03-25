package net.tslat.aoa3.client.model.entity.mob.creeponia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.content.entity.mob.creeponia.AoACreeponiaCreeper;

public class KingCreeperModel extends EntityModel<AoACreeponiaCreeper> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer head8;
	private final ModelRenderer head9;
	private final ModelRenderer head10;
	private final ModelRenderer head11;
	private final ModelRenderer head12;
	private final ModelRenderer head13;
	private final ModelRenderer head14;
	private final ModelRenderer head15;
	private final ModelRenderer head16;
	private final ModelRenderer head17;

	public KingCreeperModel(float delta) {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 33, 7)).addBox(3.5f, -13.0f, 2.0f, 1, 2, 1, delta);
		head.setPos(0.0f, 6.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, delta);
		body.setPos(0.0f, 6.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg3.setPos(-2.0f, 18.0f, -4.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg4.setPos(2.0f, 18.0f, -4.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg1.setPos(-4.0f, 18.0f, 4.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg2.setPos(2.0f, 18.0f, 4.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, delta);
		head2.setPos(0.0f, 6.0f, 0.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, -11.0f, -4.0f, 8, 3, 1, delta);
		head3.setPos(0.0f, 6.0f, -0.5f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, -11.0f, 3.5f, 8, 3, 1, delta);
		head4.setPos(0.0f, 6.0f, 0.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 33, 7)).addBox(-4.5f, -13.0f, 2.0f, 1, 2, 1, delta);
		head5.setPos(0.0f, 6.0f, 0.0f);
		head5.setTexSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 41, 0)).addBox(3.5f, -11.0f, -3.0f, 1, 3, 6, delta);
		head6.setPos(0.0f, 6.0f, 0.0f);
		head6.setTexSize(64, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 41, 0)).addBox(-4.5f, -11.0f, -3.0f, 1, 3, 6, delta);
		head7.setPos(0.0f, 6.0f, 0.0f);
		head7.setTexSize(64, 64);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 33, 7)).addBox(3.5f, -13.0f, -1.0f, 1, 2, 2, delta);
		head8.setPos(0.0f, 6.0f, 0.0f);
		head8.setTexSize(64, 64);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 33, 7)).addBox(-4.5f, -13.0f, -1.0f, 1, 2, 2, delta);
		head9.setPos(0.0f, 6.0f, 0.0f);
		head9.setTexSize(64, 64);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 33, 7)).addBox(-1.0f, -13.0f, 3.5f, 2, 2, 1, delta);
		head10.setPos(0.0f, 6.0f, 0.0f);
		head10.setTexSize(64, 64);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 33, 7)).addBox(-4.5f, -13.0f, -3.0f, 1, 2, 1, delta);
		head11.setPos(0.0f, 6.0f, 0.0f);
		head11.setTexSize(64, 64);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new ModelRenderer(this, 33, 7)).addBox(3.5f, -13.0f, -3.0f, 1, 2, 1, delta);
		head12.setPos(0.0f, 6.0f, 0.0f);
		head12.setTexSize(64, 64);
		head12.mirror = true;
		setRotation(head12, 0.0f, 0.0f, 0.0f);
		(head13 = new ModelRenderer(this, 33, 7)).addBox(2.0f, -13.0f, 3.5f, 1, 2, 1, delta);
		head13.setPos(0.0f, 6.0f, 0.0f);
		head13.setTexSize(64, 64);
		head13.mirror = true;
		setRotation(head13, 0.0f, 0.0f, 0.0f);
		(head14 = new ModelRenderer(this, 33, 7)).addBox(-3.0f, -13.0f, 3.5f, 1, 2, 1, delta);
		head14.setPos(0.0f, 6.0f, 0.0f);
		head14.setTexSize(64, 64);
		head14.mirror = true;
		setRotation(head14, 0.0f, 0.0f, 0.0f);
		(head15 = new ModelRenderer(this, 33, 7)).addBox(-3.0f, -13.0f, -4.0f, 1, 2, 1, delta);
		head15.setPos(0.0f, 6.0f, -0.5f);
		head15.setTexSize(64, 64);
		head15.mirror = true;
		setRotation(head15, 0.0f, 0.0f, 0.0f);
		(head16 = new ModelRenderer(this, 33, 7)).addBox(-1.0f, -13.0f, -4.0f, 2, 2, 1, delta);
		head16.setPos(0.0f, 6.0f, -0.5f);
		head16.setTexSize(64, 64);
		head16.mirror = true;
		setRotation(head16, 0.0f, 0.0f, 0.0f);
		(head17 = new ModelRenderer(this, 33, 7)).addBox(2.0f, -13.0f, -4.0f, 1, 2, 1, delta);
		head17.setPos(0.0f, 6.0f, -0.5f);
		head17.setTexSize(64, 64);
		head17.mirror = true;
		setRotation(head17, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head17.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(AoACreeponiaCreeper entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 57.295776f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 57.295776f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 57.295776f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 57.295776f;
		head5.yRot = netHeadYaw / 57.295776f;
		head5.xRot = headPitch / 57.295776f;
		head6.yRot = netHeadYaw / 57.295776f;
		head6.xRot = headPitch / 57.295776f;
		head7.yRot = netHeadYaw / 57.295776f;
		head7.xRot = headPitch / 57.295776f;
		head8.yRot = netHeadYaw / 57.295776f;
		head8.xRot = headPitch / 57.295776f;
		head9.yRot = netHeadYaw / 57.295776f;
		head9.xRot = headPitch / 57.295776f;
		head10.yRot = netHeadYaw / 57.295776f;
		head10.xRot = headPitch / 57.295776f;
		head11.yRot = netHeadYaw / 57.295776f;
		head11.xRot = headPitch / 57.295776f;
		head12.yRot = netHeadYaw / 57.295776f;
		head12.xRot = headPitch / 57.295776f;
		head13.yRot = netHeadYaw / 57.295776f;
		head13.xRot = headPitch / 57.295776f;
		head14.yRot = netHeadYaw / 57.295776f;
		head14.xRot = headPitch / 57.295776f;
		head15.yRot = netHeadYaw / 57.295776f;
		head15.xRot = headPitch / 57.295776f;
		head16.yRot = netHeadYaw / 57.295776f;
		head16.xRot = headPitch / 57.295776f;
		head17.yRot = netHeadYaw / 57.295776f;
		head17.xRot = headPitch / 57.295776f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
