package net.tslat.aoa3.content.block.generation.log;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LogBlock extends RotatedPillarBlock {
	private final Supplier<BlockState> strippedBlock;

	public LogBlock(BlockBehaviour.Properties properties, @Nullable Supplier<? extends Block> strippedBlock) {
		super(properties);

		this.strippedBlock = strippedBlock == null ? null : () -> strippedBlock.get().defaultBlockState();
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
		return 5;
	}

	@Nullable
	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility ItemAbility, boolean simulate) {
		if (strippedBlock == null)
			return super.getToolModifiedState(state, context, ItemAbility, simulate);

		return ItemAbilities.AXE_STRIP.equals(ItemAbility) ? strippedBlock.get() : null;
	}
}