package net.tslat.aoa3.content.item.misc;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;

import java.util.List;
import java.util.Locale;

public class ReservedItem extends Item implements SequenceVerifiedItem {
	private final String sequenceName;

	public ReservedItem(String sequenceName) {
		super(new Item.Properties().rarity(Rarity.EPIC).component(AoADataComponents.RESERVED_ITEM_STAGE, ""));

		this.sequenceName = sequenceName;
	}

	@Override
	public String getSequenceName() {
		return this.sequenceName;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			((Player)entityIn).getInventory().setItem(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
		if (context.getPlayer() == null)
			return super.onItemUseFirst(stack, context);

		Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();

		if (block == AoABlocks.CHARGING_TABLE.get()) {
			if (this == AoAItems.FLESHY_BONES.get()) {
				context.getPlayer().setItemInHand(context.getHand(), ((ReservedItem)AoAItems.DARK_BONES.get()).newValidStack());


				return InteractionResult.SUCCESS;
			}
		}
		else if (block == AoABlocks.INFUSED_PRESS.get()) {
			if (this == AoAItems.DARK_BONES.get()) {
				context.getPlayer().setItemInHand(context.getHand(), ((ReservedItem)AoAItems.ROCK_BONES.get()).newValidStack());


				return InteractionResult.SUCCESS;
			}
		}

		return super.onItemUseFirst(stack, context);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!target.level().isClientSide) {
			if (stack.getItem() == AoAItems.MILLENNIUM_UPGRADER.get()) {
				if (target.isInLava() && attacker.isInLava()) {
					attacker.setItemInHand(InteractionHand.MAIN_HAND, ((ReservedItem)AoAItems.MOLTEN_UPGRADER.get()).newValidStack());

					return true;
				}
			}
			else if (stack.getItem() == AoAItems.MOONSTONE.get()) {
				//if (target instanceof ArcwormEntity)
				//	EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.LEVITATION, 200).level(11).hideParticles());

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.discard();
		}

		return false;
	}

	public ItemStack newValidStack() {
		ItemStack stack = getDefaultInstance();

		stack.set(AoADataComponents.RESERVED_ITEM_STAGE, this.sequenceName);

		return stack;
	}

	public static void handlePlayerToss(ItemTossEvent ev) {
		Level world = ev.getEntity().level();

		if (!world.isClientSide()) {
			if (ev.getEntity().getItem().getItem() == AoAItems.MOLTEN_UPGRADER.get() && world.getMoonBrightness() == 1)
				ev.getEntity().setItem(((ReservedItem)AoAItems.MOONSTONE.get()).newValidStack());
		}
	}

	/*public static void handleArcworm(ArcwormEntity arcworm) {
		if (arcworm.getY() > 275 && arcworm.getKillCredit() instanceof Player) {
			if (arcworm.getKillCredit().getMainHandItem().getItem() == AoAItems.MOONSTONE.get()) {
				arcworm.spawnAtLocation(((ExperimentW801)AoAWeapons.EXPERIMENT_W_801.get()).newValidStack(), 0);
				arcworm.getKillCredit().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
				arcworm.discard();
			}
		}
	}*/

	public static void handlePlayerDeath(Player pl) {
		if (pl.getHealth() > 0 && !pl.level().getLevelData().isHardcore())
			return;

		if (!RegistryUtil.getId(pl.getMainHandItem().getItem()).getPath().toLowerCase(Locale.ENGLISH).startsWith("a"))
			return;

		NonNullList<ItemStack> armour = pl.getInventory().armor;

		if (!RegistryUtil.getId(armour.get(3).getItem()).getPath().toLowerCase(Locale.ENGLISH).startsWith("l"))
			return;

		if (!RegistryUtil.getId(armour.get(2).getItem()).getPath().toLowerCase(Locale.ENGLISH).startsWith("i"))
			return;

		if (!RegistryUtil.getId(armour.get(1).getItem()).getPath().toLowerCase(Locale.ENGLISH).startsWith("e"))
			return;

		if (!RegistryUtil.getId(armour.get(0).getItem()).getPath().toLowerCase(Locale.ENGLISH).startsWith("n"))
			return;

		if (InventoryUtil.hasItem(pl, AoAItems.ALIEN_ORB))
			return;

		pl.spawnAtLocation(((ReservedItem)AoAItems.ALIEN_ORB.get()).newValidStack(), 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (stack.getItem() == AoAItems.ALIEN_ORB.get())
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
