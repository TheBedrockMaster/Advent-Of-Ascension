package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.NethengeicWitherEntity;
import net.tslat.aoa3.content.entity.monster.nether.NethengeicBeastEntity;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NethengeicCallstone extends BossSpawningItem<NethengeicWitherEntity> {
	public NethengeicCallstone() {
		super(0, new Properties().rarity(Rarity.RARE).durability(3).setNoRepair());
	}

	@Override
	public NethengeicWitherEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount) {
		NethengeicWitherEntity wither = EntitySpawningUtil.spawnEntity(level, AoAMonsters.NETHENGEIC_WITHER.get(), position, MobSpawnType.TRIGGERED);

		if (playerCount > 1 && wither != null) {
			AttributeUtil.applyPermanentModifier(wither, Attributes.MAX_HEALTH, getPerPlayerHealthBuff(playerCount));
			wither.setHealth(wither.getMaxHealth());
		}

		return wither;
	}

	@Override
	@Nullable
	public EntityType<NethengeicWitherEntity> getEntityType(ItemStack stack) {
		return stack.isDamaged() ? null : AoAMonsters.NETHENGEIC_WITHER.get();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).isDamaged())
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return super.use(level, player, hand);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!stack.isDamaged())
			return true;

		if (stack.getDamageValue() == 2) {
			if (!(target instanceof WitherBoss))
				return true;
		}
		else if (stack.getDamageValue() == 1) {
			if (!(target instanceof NethengeicBeastEntity))
				return true;
		}

		stack.setDamageValue(stack.getDamageValue() - 1);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else if (stack.getDamageValue() == 2) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 4));
		}
	}
}
