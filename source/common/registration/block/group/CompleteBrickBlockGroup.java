package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class CompleteBrickBlockGroup {
	public final DeferredBlock<Block> stone;
	public final DeferredBlock<Block> cracked;
	public final DeferredBlock<Block> chiseled;
	public final DeferredBlock<SlabBlock> slab;
	public final DeferredBlock<StairBlock> stairs;
	public final DeferredBlock<WallBlock> wall;

	public CompleteBrickBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar) {
		this.stone = registry.register(baseId + "s", baseBlockRegistrar);
		this.cracked = registry.register("cracked_" + baseId + "s", baseBlockRegistrar);
		this.chiseled = registry.register("chiseled_" + baseId + "s", baseBlockRegistrar);
		this.slab = registry.register(baseId + "_slab", registrar -> registrar.baseSlab(this.stone));
		this.stairs = registry.register(baseId + "_stairs", registrar -> registrar.baseStairs(this.stone));
		this.wall = registry.register(baseId + "_wall", registrar -> registrar.baseWall(this.stone));
	}

	public Block stone() {
		return this.stone.get();
	}

	public Block crackedStone() {
		return this.cracked.get();
	}

	public Block chiseledStone() {
		return this.chiseled.get();
	}

	public SlabBlock slab() {
		return this.slab.get();
	}

	public StairBlock stairs() {
		return this.stairs.get();
	}

	public WallBlock wall() {
		return this.wall.get();
	}
}
