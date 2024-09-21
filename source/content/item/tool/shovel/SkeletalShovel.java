package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.List;

public class SkeletalShovel extends BaseShovel implements LootModifyingItem {
	public SkeletalShovel(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return super.getDestroySpeed(stack, state);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || getDestroySpeed(getToolStack(lootContext), harvestedBlock) <= 1)
			return;

		if (RandomUtil.oneInNChance(10)) {
			int dropChoice = RandomUtil.randomNumberUpTo(50);
			ItemStack drop;

			if (dropChoice == 0) {
				drop = new ItemStack(RandomUtil.getRandomSelection(
						AoAItems.SKULLBONE_FRAGMENT.get(),
						AoAItems.CHESTBONE_FRAGMENT.get(),
						AoAItems.LEGBONE_FRAGMENT.get(),
						AoAItems.FOOTBONE_FRAGMENT.get()));
			}
			else if (dropChoice < 10) {
				drop = new ItemStack(Items.BONE_MEAL);
			}
			else {
				drop = new ItemStack(Items.BONE);
			}

			existingLoot.add(drop);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SKELETAL_TOOL_DESCRIPTION, LocaleUtil.ItemDescriptionType.UNIQUE));
	}
}
