package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.RPGEntity;
import net.tslat.aoa3.util.ColourUtil;

public class RPGRenderer extends TexturedProjectileRenderer<RPGEntity> {
	public RPGRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(RPGEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 0.3, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY() - 0.3, entity.getZ(), 0, 0, 0);
		}
	}
}