package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class OccultShovel extends BaseShovel {
	public OccultShovel(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return super.getDestroySpeed(stack, state) * (1 + (stack.getDamageValue() / (float)stack.getMaxDamage()));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
