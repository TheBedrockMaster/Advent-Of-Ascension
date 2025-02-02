package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class SelyanScythe extends BaseGreatblade {
	public SelyanScythe(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		AoAResource.Instance spirit = target instanceof ServerPlayer ? PlayerUtil.getResource((ServerPlayer)target, AoAResources.SPIRIT.get()) : null;
		float consumeAmount = (spirit != null ? Math.min(50, spirit.getCurrentValue()) : 50) * attackCooldown;

		attacker.heal(attackCooldown);

		if (consumeAmount > 0) {
			if (spirit != null && !spirit.consume(consumeAmount, true))
				return;

			if (attacker instanceof ServerPlayer)
				PlayerUtil.addResourceToPlayer((ServerPlayer)attacker, AoAResources.SPIRIT.get(), consumeAmount);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.LEECHES_SPIRIT, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
