package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class GoofyAxe extends BaseAxe {
	public GoofyAxe(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	// TODO see about sound effect on hit

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (!world.isClientSide && stack.isDamaged()) {
			int modulo;

			if (selected) {
				modulo = 120;
			}
			else if (slot < 9) {
				modulo = 200;
			}
			else {
				modulo = 280;
			}

			if (world.getGameTime() % modulo == 0) {
				stack.setDamageValue(stack.getDamageValue() - 1);

				if (entity instanceof Player pl)
					pl.inventoryMenu.broadcastChanges();
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.GOOFY_TOOL_REGEN, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.NO_DAMAGE, LocaleUtil.ItemDescriptionType.HARMFUL));
	}
}
