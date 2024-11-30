package net.tslat.aoa3.common.registration.block;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.item.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.block.decoration.banner.BannerBlock;
import net.tslat.aoa3.content.block.generation.log.LogBlock;
import net.tslat.aoa3.content.block.generation.misc.StaticMushroomBlock;
import net.tslat.aoa3.content.block.generation.plants.GenericPlantBlock;
import net.tslat.aoa3.content.block.generation.plants.GenericWaterPlant;
import net.tslat.aoa3.util.BlockUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

public final class BlockRegistrar<T extends Block> {
	private final List<Consumer<T>> callbacks = new ObjectArrayList<>();
	private DeferredBlock<T> registryObject = null;

	private BlockBehaviour.Properties properties = BlockBehaviour.Properties.of();
	private Function<BlockBehaviour.Properties, Block> factory = Block::new;

	ResourceKey<CreativeModeTab>[] creativeTabs = null;
	Item.Properties itemProperties = new Item.Properties();
	BiFunction<T, Item.Properties, ? extends Item> itemFactory = BlockItem::new;

	public BlockRegistrar<T> basedOn(DeferredBlock<? extends Block> block) {
		return basedOn(block.get());
	}

	public BlockRegistrar<T> basedOn(Block block) {
		this.properties = BlockBehaviour.Properties.ofFullCopy(block);

		return this;
	}

	public BlockRegistrar<T> baseStone() {
		basedOn(Blocks.STONE);
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseCobblestone() {
		basedOn(Blocks.COBBLESTONE);
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseGrass() {
		basedOn(Blocks.GRASS_BLOCK);
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseDirt() {
		basedOn(Blocks.DIRT);
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseSand(int colour) {
		basedOn(Blocks.SAND);
		factory(properties -> new ColoredFallingBlock(new ColorRGBA(colour), properties));
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseLeaves() {
		mapColour(MapColor.PLANT);
		stats(0.2f);
		//randomTicks();
		sounds(SoundType.GRASS);
		noOcclusion();
		specialSpawns((state, level, pos, entityType) -> entityType == EntityType.OCELOT || entityType == EntityType.PARROT);
		breathable();
		noScreenCover();
		flammable();
		pistonBreaks();
		noRedstone();
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseFlammable(int spreadSpeed, int flammability) {
		factory(properties -> new Block(properties) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return spreadSpeed;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return flammability;
			}
		});

		return this;
	}

	public BlockRegistrar<T> baseLog(MapColor endColour, MapColor sideColour, Supplier<? extends Block> strippedBlock) {
		mapColour(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? endColour : sideColour);
		factory(properties -> new LogBlock(properties, strippedBlock));
		instrument(NoteBlockInstrument.BASS);
		stats(2);
		sounds(SoundType.WOOD);
		flammable();
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseSapling() {
		basedOn(Blocks.OAK_SAPLING);
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseMushroom() {
		mapColour(MapColor.PLANT);
		sounds(SoundType.FUNGUS);
		noClip();
		neverSolid();
		pistonBreaks();
		instabreak();
		factory(StaticMushroomBlock::new);
		needsPostPlacementCheck();
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseWaterPlant() {
		mapColour(MapColor.PLANT);
		sounds(SoundType.FUNGUS);
		noClip();
		neverSolid();
		pistonBreaks();
		modelOffset(BlockBehaviour.OffsetType.XZ);
		instabreak();
		replaceable();
		factory(GenericWaterPlant::new);
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> basePlant(Predicate<BlockState> validSurface, float width, float height) {
		mapColour(MapColor.PLANT);
		sounds(SoundType.GRASS);
		flammable();
		noClip();
		neverSolid();
		pistonBreaks();
		modelOffset(BlockBehaviour.OffsetType.XZ);
		instabreak();
		replaceable();
		factory(properties -> new GenericPlantBlock(properties, validSurface, width, height));
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> basePlantOld() {
		mapColour(MapColor.PLANT);
		sounds(SoundType.GRASS);
		flammable();
		noClip();
		neverSolid();
		pistonBreaks();
		modelOffset(BlockBehaviour.OffsetType.XZ);
		instabreak();
		replaceable();
		factory(properties -> new BushBlock(properties) {
			@Override
			protected MapCodec<? extends BushBlock> codec() {
				return null;
			}
		});
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseStackablePlant(Function<BlockBehaviour.Properties, Block> factory) {
		basePlantOld();
		modelOffset(BlockBehaviour.OffsetType.NONE);
		factory(factory);

		return this;
	}

	public BlockRegistrar<T> baseFlower(@Nullable Holder<? extends MobEffect> potionEffect, int duration) {
		mapColour(MapColor.PLANT);
		sounds(SoundType.GRASS);
		noClip();
		instabreak();
		modelOffset(BlockBehaviour.OffsetType.XZ);
		pistonBreaks();
		factory(properties -> new FlowerBlock((Holder<MobEffect>)potionEffect, duration, properties));
		generationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseOre(IntProvider xpProvider) {
		basedOn(Blocks.IRON_ORE);
		generationBlocksTab();
		factory(properties -> new DropExperienceBlock(xpProvider, properties));

		return this;
	}

	public BlockRegistrar<T> banner() {
		mapColour(MapColor.METAL);
		stats(0.5f, 1);
		sounds(SoundType.WOOL);
		noClip();
		noOcclusion();
		pistonBreaks();
		alwaysSolid();
		factory(BannerBlock::new);
		instrument(NoteBlockInstrument.BASS);
		inTabs(AoACreativeModeTabs.BANNERS);

		return this;
	}

	public BlockRegistrar<T> basePottedPlant(Holder<? extends Block> plant) {
		basedOn(Blocks.FLOWER_POT);
		factory(properties -> new FlowerPotBlock(null, plant::value, properties));
		addPottedPlant(plant);
		noItem();

		return this;
	}

	public BlockRegistrar<T> baseCrop() {
		basedOn(Blocks.WHEAT);
		noItem();

		return this;
	}

	public BlockRegistrar<T> basePortal() {
		noClip();
		unbreakable();
		sounds(SoundType.GLASS);
		light(11);
		stopsPistons();
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseGlass() {
		factory(TransparentBlock::new);
		stats(0.3f);
		sounds(SoundType.GLASS);
		instrument(NoteBlockInstrument.HAT);
		noOcclusion();
		breathable();
		noRedstone();
		noScreenCover();
		noSpawns();
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseButton(DeferredBlock<? extends Block> base) {
		basedOn(base);
		noClip();
		stats(0.5f);
		pistonBreaks();
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> basePressurePlate(DeferredBlock<? extends Block> base) {
		basedOn(base);
		alwaysSolid();
		instrument(NoteBlockInstrument.BASS);
		noClip();
		stats(0.5f);
		flammable();
		pistonBreaks();
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseDoor(BlockSetType blockSet) {
		basedOn(Blocks.OAK_DOOR);
		factory(properties -> new DoorBlock(blockSet, properties));
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseTrapdoor(BlockSetType blockSet) {
		basedOn(Blocks.OAK_TRAPDOOR);
		factory(properties -> new TrapDoorBlock(blockSet, properties));
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseCarpet() {
		basedOn(Blocks.ORANGE_CARPET);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseRawOreBlock() {
		basedOn(Blocks.IRON_ORE);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseMineralBlock() {
		basedOn(Blocks.IRON_BLOCK);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> basePlanks() {
		basedOn(Blocks.OAK_PLANKS);
		factory(properties -> new Block(properties) {
			@Override
			public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 5;
			}

			@Override
			public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
				return 20;
			}
		});
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseFence(DeferredBlock<? extends Block> block) {
		Block base = block.get();

		instrument(base.defaultBlockState().instrument());
		stats(base.defaultDestroyTime(), base.getExplosionResistance());
		sounds(base.defaultBlockState().getSoundType());
		mapColour(base.defaultMapColor());
		light(base.defaultBlockState().getLightEmission());
		alwaysSolid();
		decorationBlocksTab();
		factory(FenceBlock::new);

		if (base.defaultBlockState().ignitedByLava())
			flammable();

		return this;
	}

	public BlockRegistrar<T> baseWall(DeferredBlock<? extends Block> block) {
		basedOn(block);
		alwaysSolid();
		factory(WallBlock::new);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseStairs(DeferredBlock<? extends Block> block) {
		basedOn(block);
		factory(properties -> new StairBlock(block.get().defaultBlockState(), properties));
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseSlab(DeferredBlock<? extends Block> block) {
		basedOn(block);
		factory(SlabBlock::new);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseSign(DeferredBlock<? extends Block> block, boolean hanging) {
		basedOn(block);
		alwaysSolid();
		instrument(NoteBlockInstrument.BASS);
		noClip();
		stats(1);
		flammable();
		decorationBlocksTab();
		addToBlockEntity(hanging ? BlockEntityType.HANGING_SIGN : BlockEntityType.SIGN);

		return this;
	}

	public BlockRegistrar<T> baseBricks() {
		basedOn(Blocks.NETHER_BRICKS);
		decorationBlocksTab();

		return this;
	}

	public BlockRegistrar<T> baseLadder() {
		basedOn(Blocks.LADDER);
		factory(LadderBlock::new);
		utilityBlocksTab();

		return this;
	}

	public BlockRegistrar<T> itemFactory(BiFunction<T, Item.Properties, ? extends Item> factory) {
		this.itemFactory = factory;

		return this;
	}

	public BlockRegistrar<T> itemProperties(Consumer<Item.Properties> properties) {
		properties.accept(Objects.requireNonNull(this.itemProperties));

		return this;
	}

	public BlockRegistrar<T> noItem() {
		this.itemProperties = null;

		return this;
	}

	public BlockRegistrar<T> generationBlocksTab() {
		return inTabs(AoACreativeModeTabs.GENERATION_BLOCKS);
	}

	public BlockRegistrar<T> decorationBlocksTab() {
		return inTabs(AoACreativeModeTabs.DECORATION_BLOCKS);
	}

	public BlockRegistrar<T> utilityBlocksTab() {
		return inTabs(AoACreativeModeTabs.FUNCTIONAL_BLOCKS);
	}

	public BlockRegistrar<T> inTabs(DeferredHolder<CreativeModeTab, CreativeModeTab>... tabs) {
		return inTabs(Arrays.stream(tabs).map(DeferredHolder::getKey).toArray(ResourceKey[]::new));
	}

	public BlockRegistrar<T> inTabs(ResourceKey<CreativeModeTab>... tabs) {
		this.creativeTabs = tabs;

		return this;
	}

	public BlockRegistrar<T> replaceable() {
		this.properties.replaceable();

		return this;
	}

	public BlockRegistrar<T> noParticles() {
		this.properties.noTerrainParticles();

		return this;
	}

	public BlockRegistrar<T> instrument(NoteBlockInstrument instrument) {
		this.properties.instrument(instrument);

		return this;
	}

	public BlockRegistrar<T> alwaysSolid() {
		this.properties.forceSolidOn();

		return this;
	}

	public BlockRegistrar<T> neverSolid() {
		this.properties.forceSolidOff();

		return this;
	}

	public BlockRegistrar<T> needsPostPlacementCheck() {
		return needsPostPlacementCheck((state, level, pos) -> true);
	}

	public BlockRegistrar<T> needsPostPlacementCheck(BlockBehaviour.StatePredicate predicate) {
		this.properties.hasPostProcess(predicate);

		return this;
	}

	public BlockRegistrar<T> flammable() {
		this.properties.ignitedByLava();

		return this;
	}

	public BlockRegistrar<T> useDropsFrom(Supplier<? extends Block> block) {
		this.properties.lootFrom(block);

		return this;
	}

	public BlockRegistrar<T> liquid() {
		this.properties.liquid();

		return this;
	}

	public BlockRegistrar<T> noPistonPulling() {
		return pistonPush(PushReaction.PUSH_ONLY);
	}

	public BlockRegistrar<T> pistonDeletes() {
		return pistonPush(PushReaction.IGNORE);
	}

	public BlockRegistrar<T> stopsPistons() {
		return pistonPush(PushReaction.BLOCK);
	}

	public BlockRegistrar<T> pistonBreaks() {
		return pistonPush(PushReaction.DESTROY);
	}

	public BlockRegistrar<T> pistonPush(PushReaction pushing) {
		this.properties.pushReaction(pushing);

		return this;
	}

	public BlockRegistrar<T> air() {
		this.properties.air();

		return this;
	}

	public BlockRegistrar<T> emissive() {
		return emissive((state, world, pos) -> true);
	}

	public BlockRegistrar<T> emissive(BlockBehaviour.StatePredicate when) {
		this.properties.emissiveRendering(when);

		return this;
	}

	public BlockRegistrar<T> renderAdjust(BlockBehaviour.StatePredicate when) {
		this.properties.hasPostProcess(when);

		return this;
	}

	public BlockRegistrar<T> noScreenCover() {
		return coversScreen((state, world, pos) -> false);
	}

	public BlockRegistrar<T> coversScreen(BlockBehaviour.StatePredicate when) {
		this.properties.isViewBlocking(when);

		return this;
	}

	public BlockRegistrar<T> noRedstone() {
		return conductRedstone((state, world, pos) -> false);
	}

	public BlockRegistrar<T> conductRedstone(BlockBehaviour.StatePredicate when) {
		this.properties.isRedstoneConductor(when);

		return this;
	}

	public BlockRegistrar<T> noSpawns() {
		return specialSpawns((state, world, pos, entityType) -> false);
	}

	public BlockRegistrar<T> specialSpawns(BlockBehaviour.StateArgumentPredicate<EntityType<?>> when) {
		this.properties.isValidSpawn(when);

		return this;
	}

	public BlockRegistrar<T> breathable() {
		return suffocate((state, world, pos) -> false);
	}

	public BlockRegistrar<T> suffocate(BlockBehaviour.StatePredicate when) {
		this.properties.isSuffocating(when);

		return this;
	}

	public BlockRegistrar<T> unbreakable() {
		noDrops();

		return stats(BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE);
	}

	public BlockRegistrar<T> instabreak() {
		this.properties.instabreak();

		return this;
	}

	public BlockRegistrar<T> modelOffset(BlockBehaviour.OffsetType offset) {
		this.properties.offsetType(offset);

		return this;
	}

	public BlockRegistrar<T> noDrops() {
		this.properties.noLootTable();

		return this;
	}

	public BlockRegistrar<T> needsTool() {
		this.properties.requiresCorrectToolForDrops();

		return this;
	}

	public BlockRegistrar<T> dynamicShape() {
		this.properties.dynamicShape();

		return this;
	}

	public BlockRegistrar<T> randomTicks() {
		this.properties.randomTicks();

		return this;
	}

	public BlockRegistrar<T> stats(float hardness) {
		return stats(hardness, hardness);
	}

	public BlockRegistrar<T> stats(float hardness, float blastResist) {
		this.properties.strength(hardness, blastResist);

		return this;
	}

	public BlockRegistrar<T> light(int light) {
		return light(state -> light);
	}

	public BlockRegistrar<T> light(ToIntFunction<BlockState> light) {
		this.properties.lightLevel(light);

		return this;
	}

	public BlockRegistrar<T> sounds(SoundType sound) {
		this.properties.sound(sound);

		return this;
	}

	public BlockRegistrar<T> jumpMod(float modifier) {
		this.properties.jumpFactor(modifier);

		return this;
	}

	public BlockRegistrar<T> speedMod(float modifier) {
		this.properties.speedFactor(modifier);

		return this;
	}

	public BlockRegistrar<T> friction(float friction) {
		this.properties.friction(friction);

		return this;
	}

	public BlockRegistrar<T> noOcclusion() {
		this.properties.noOcclusion();

		return this;
	}

	public BlockRegistrar<T> noClip() {
		this.properties.noCollission();
		noScreenCover();

		return this;
	}

	public BlockRegistrar<T> mapColour(MapColor colour) {
		this.properties.mapColor(colour);

		return this;
	}

	public BlockRegistrar<T> mapColour(DyeColor colour) {
		this.properties.mapColor(colour);

		return this;
	}

	public BlockRegistrar<T> mapColour(Function<BlockState, MapColor> colour) {
		this.properties.mapColor(colour);

		return this;
	}

	public BlockRegistrar<T> factory(Function<BlockBehaviour.Properties, Block> factory) {
		this.factory = factory;

		return this;
	}

	public BlockRegistrar<T> addToBlockEntity(BlockEntityType<?> blockEntity) {
		this.callbacks.add(block -> AoABlockEntities.addBlockToExistingBlockEntityType(blockEntity, block));

		return this;
	}

	public BlockRegistrar<T> addPottedPlant(Holder<? extends Block> plant) {
		this.callbacks.add(block -> ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(plant.getKey().location(), this.registryObject));

		return this;
	}

	ResourceLocation getId() {
		return this.registryObject.getId();
	}

	T build(Consumer<BlockRegistrar<T>> registrar) {
		registrar.accept(this);

		if (this.itemProperties != null && this.registryObject != null)
			AoAItems.registerItem(getId().getPath(), () -> this.itemFactory.apply(this.registryObject.get(), this.itemProperties), this.creativeTabs);

		return (T)this.factory.apply(this.properties);
	}

	void setRegistryObject(DeferredBlock<T> registryObject) {
		this.registryObject = registryObject;
	}

	T doCallbacks(final T block) {
		this.callbacks.forEach(consumer -> consumer.accept(block));

		return block;
	}
}
