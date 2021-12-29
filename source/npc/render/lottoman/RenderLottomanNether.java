package net.nevermine.npc.render.lottoman;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.model.modelLottoman;

@SideOnly(Side.CLIENT)
public class RenderLottomanNether extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelLottoman model;

	public RenderLottomanNether(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelLottoman)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderLottomanNether.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/lottomanNether.png");
	}
}