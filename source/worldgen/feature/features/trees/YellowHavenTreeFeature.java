package net.tslat.aoa3.worldgen.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.tslat.aoa3.block.functional.sapling.SaplingBlock;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.tslat.aoa3.common.registration.AoABlocks;

import java.util.function.Supplier;

public class YellowHavenTreeFeature extends HavenTreeFeature {
	public YellowHavenTreeFeature(Codec<BlockStateFeatureConfig> codec, Supplier<SaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.YELLOW_HAVEN_LEAVES.get().defaultBlockState();
	}
}