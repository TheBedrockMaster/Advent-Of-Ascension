package net.tslat.aoa3.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.advent.AdventOfAscension;

public class HelmOfTheAlchemistTrimModel extends SkillHelmetModel {
	public HelmOfTheAlchemistTrimModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_alchemist_trim.png"));

		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}