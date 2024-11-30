package net.tslat.aoa3.content.item.weapon.vulcane;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.AoASoundBuilderPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.VulcaneStats;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public abstract class BaseVulcane extends Item {
	public BaseVulcane(Item.Properties properties) {
		super(properties);
	}

	public VulcaneStats vulcaneStats(ItemStack stack) {
		return stack.get(AoADataComponents.VULCANE_STATS.get());
	}

	public double getVulcaneDamage(ItemStack stack) {
		return vulcaneStats(stack).damage();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!(player instanceof ServerPlayer))
			return InteractionResultHolder.fail(stack);

		AoAResource.Instance rage = PlayerUtil.getResource(player, AoAResources.RAGE.get());

		if (!rage.hasAmount(50) || player.getLastHurtByMob() == null)
			return InteractionResultHolder.fail(stack);

		return activate(rage, stack, hand);
	}

	public InteractionResultHolder<ItemStack> activate(AoAResource.Instance rage, ItemStack vulcane, InteractionHand hand) {
		Player pl = rage.getPlayerDataManager().getPlayer();
		float targetHealth = pl.getLastHurtByMob().getHealth();
		float damage = (float) getVulcaneDamage(vulcane) * (1 + ((rage.getCurrentValue() - 50) / 100));

		if (DamageUtil.doVulcaneAttack(pl, pl.getLastHurtByMob(), damage)) {
			doAdditionalEffect(pl.getLastHurtByMob(), pl, Math.min(targetHealth, damage));
			AoANetworking.sendToAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(AoASounds.ITEM_VULCANE_USE.get()).isPlayer().followEntity(pl)), pl);
			ItemUtil.damageItemForUser(pl, vulcane, hand);
			rage.consume(rage.getCurrentValue(), true);

			return InteractionResultHolder.success(vulcane);
		}

		return InteractionResultHolder.fail(vulcane);
	}

	public void doAdditionalEffect(LivingEntity target, Player player, float damageDealt) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(getVulcaneDamage(stack))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_COST, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_GRACE_PERIOD, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
