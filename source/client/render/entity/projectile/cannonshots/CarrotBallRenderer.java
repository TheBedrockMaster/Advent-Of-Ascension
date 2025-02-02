package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.CarrotBallEntity;
import net.tslat.effectslib.api.particle.ParticleBuilder;

public class CarrotBallRenderer extends TexturedProjectileRenderer<CarrotBallEntity> {
	public CarrotBallRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(CarrotBallEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.2d, 0))
				.spawnNTimes(8)
				.colourOverride(0xDF9900)
				.spawnParticles(entity.level());

		for (int i = 0; i < 8; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.2f, 0))
					.colourOverride(colourMod, colourMod, 0, 1f)
					.spawnParticles(entity.level());
		}
	}
}