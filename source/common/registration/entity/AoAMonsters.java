package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.boss.king_bambambam.EliteKingBamBamBamEntity;
import net.tslat.aoa3.content.entity.boss.king_bambambam.KingBamBamBamEntity;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.EliteNethengeicWitherEntity;
import net.tslat.aoa3.content.entity.boss.nethengeic_wither.NethengeicWitherEntity;
import net.tslat.aoa3.content.entity.boss.skeletron.EliteSkeletronEntity;
import net.tslat.aoa3.content.entity.boss.skeletron.SkeletronEntity;
import net.tslat.aoa3.content.entity.boss.smash.EliteSmashEntity;
import net.tslat.aoa3.content.entity.boss.smash.SmashEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.EliteTyrosaurEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.TyrosaurEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.WoundedTyrosaurEntity;
import net.tslat.aoa3.content.entity.monster.barathos.NospikeEntity;
import net.tslat.aoa3.content.entity.monster.barathos.TharaflyEntity;
import net.tslat.aoa3.content.entity.monster.nether.*;
import net.tslat.aoa3.content.entity.monster.overworld.*;
import net.tslat.aoa3.content.entity.monster.precasia.*;

public final class AoAMonsters {
	public static void init() {}

	public static final DeferredHolder<EntityType<?>, EntityType<AncientGolemEntity>> ANCIENT_GOLEM = register("ancient_golem", EntityTypeRegistrar.monster(AncientGolemEntity::new).sized(1.125f, 2.625f, 2.375f).spawnEgg(0x221D19, 0x211C18));
	public static final DeferredHolder<EntityType<?>, EntityType<AttercopusEntity>> ATTERCOPUS = register("attercopus", EntityTypeRegistrar.monster(AttercopusEntity::new).sized(0.75f, 0.5625f, 0.375f).spawnEgg(0x281F17, 0x433325));
	public static final DeferredHolder<EntityType<?>, EntityType<BombCarrierEntity>> BOMB_CARRIER = register("bomb_carrier", EntityTypeRegistrar.monster(BombCarrierEntity::new).sized(0.5f, 1.75f, 1.53125f).spawnEgg(0x433128, 0x4C9949));
	public static final DeferredHolder<EntityType<?>, EntityType<BushBabyEntity>> BUSH_BABY = register("bush_baby", EntityTypeRegistrar.monster(BushBabyEntity::new).sized(0.85f, 0.9375f, 0.46875f).spawnEgg(0x3BA14C, 0x83C15D));
	public static final DeferredHolder<EntityType<?>, EntityType<ChargerEntity>> CHARGER = register("charger", EntityTypeRegistrar.monster(ChargerEntity::new).sized(0.625f, 1.5f, 1.275f).spawnEgg(0xC35641, 0xC35641));
	public static final DeferredHolder<EntityType<?>, EntityType<ChomperEntity>> CHOMPER = register("chomper", EntityTypeRegistrar.monster(ChomperEntity::new).sized(0.8f, 0.85f, 0.75f).spawnEgg(0x1F3F31, 0x337056));
	public static final DeferredHolder<EntityType<?>, EntityType<CyclopsEntity>> CYCLOPS = register("cyclops", EntityTypeRegistrar.monster(CyclopsEntity::new).sized(0.6f, 2.125f, 1.90625f).spawnEgg(0x664D3D, 0x7B6354));
	public static final DeferredHolder<EntityType<?>, EntityType<DunkleosteusEntity>> DUNKLEOSTEUS = register("dunkleosteus", EntityTypeRegistrar.monster(DunkleosteusEntity::new).sized(1.3125f, 1.59375f, 1.09375f).spawnEgg(0x606F67, 0x5F5057));
	public static final DeferredHolder<EntityType<?>, EntityType<EmbrakeEntity>> EMBRAKE = register("embrake", EntityTypeRegistrar.monster(EmbrakeEntity::new).sized(0.875f, 1.125f, 0.8125f).fireImmune().spawnEgg(0x261B18, 0xDD8701));
	public static final DeferredHolder<EntityType<?>, EntityType<FlamewalkerEntity>> FLAMEWALKER = register("flamewalker", EntityTypeRegistrar.monster(FlamewalkerEntity::new).sized(0.875f, 2.5625f, 2.03125f).fireImmune().spawnEgg(0xE48D12, 0x261815));
	public static final DeferredHolder<EntityType<?>, EntityType<GhostEntity>> GHOST = register("ghost", EntityTypeRegistrar.monster(GhostEntity::new).sized(0.5625f, 1.625f, 1.1875f).spawnEgg(0xB3D2CE, 0x4D5256));
	public static final DeferredHolder<EntityType<?>, EntityType<GoblinEntity>> GOBLIN = register("goblin", EntityTypeRegistrar.monster(GoblinEntity::new).sized(0.6f, 1.8f, 1.59375f).spawnEgg(0x1B765E, 0x44AF5E));
	public static final DeferredHolder<EntityType<?>, EntityType<InfernalEntity>> INFERNAL = register("infernal", EntityTypeRegistrar.monster(InfernalEntity::new).sized(1.25f, 3.75f, 3.09375f).fireImmune().spawnEgg(0x1C1B1B, 0xE38700));
	public static final DeferredHolder<EntityType<?>, EntityType<KingChargerEntity>> KING_CHARGER = register("king_charger", EntityTypeRegistrar.monster(KingChargerEntity::new).sized(1.125f, 2.0625f, 1.84375f).spawnEgg(0xDF7858, 0x8D4347));
	public static final DeferredHolder<EntityType<?>, EntityType<LittleBamEntity>> LITTLE_BAM = register("little_bam", EntityTypeRegistrar.monster(LittleBamEntity::new).sized(0.75f, 1.65625f, 1.4375f).fireImmune().spawnEgg(0x2F180E, 0xDB8702));
	public static final DeferredHolder<EntityType<?>, EntityType<MeganeuropsisEntity>> MEGANEUROPSIS = register("meganeuropsis", EntityTypeRegistrar.monster(MeganeuropsisEntity::new).sized(0.5f, 0.4375f, 0.3125f).spawnEgg(0x715C0D, 0xE2E2E2));
	public static final DeferredHolder<EntityType<?>, EntityType<SasquatchEntity>> SASQUATCH = register("sasquatch", EntityTypeRegistrar.monster(SasquatchEntity::new).sized(0.5625f, 1.8125f, 1.59375f).spawnEgg(0x80605C, 0x45293E));
	public static final DeferredHolder<EntityType<?>, EntityType<ScolopendisEntity>> SCOLOPENDIS = register("scolopendis", EntityTypeRegistrar.monster(ScolopendisEntity::new).sized(1.0625f, 0.75f, 0.6875f).spawnEgg(0xA83319, 0x0A0F16));
	public static final DeferredHolder<EntityType<?>, EntityType<SmilodonEntity>> SMILODON = register("smilodon", EntityTypeRegistrar.monster(SmilodonEntity::new).sized(0.7f, 1.375f, 1.0625f).spawnEgg(0x86552C, 0x372516));
	public static final DeferredHolder<EntityType<?>, EntityType<SpinoledonEntity>> SPINOLEDON = register("spinoledon", EntityTypeRegistrar.monster(SpinoledonEntity::new).sized(0.75f, 1.9375f, 1.9f).spawnEgg(0x3E5234, 0x3B3A38));
	public static final DeferredHolder<EntityType<?>, EntityType<TreeSpiritEntity>> TREE_SPIRIT = register("tree_spirit", EntityTypeRegistrar.monster(TreeSpiritEntity::new).sized(1f, 3f, 2.4f).spawnEgg(0x60502C, 0x9A8448));
	public static final DeferredHolder<EntityType<?>, EntityType<VeloraptorEntity>> VELORAPTOR = register("veloraptor", EntityTypeRegistrar.monster(VeloraptorEntity::new).sized(0.55f, 1.3125f, 1.299375f).spawnEgg(0x514641, 0x8E8975));
	public static final DeferredHolder<EntityType<?>, EntityType<VoidWalkerEntity>> VOID_WALKER = register("void_walker", EntityTypeRegistrar.monster(VoidWalkerEntity::new).sized(0.875f, 1.375f, 1.15625f).spawnEgg(0x171717, 0x332B31));
	public static final DeferredHolder<EntityType<?>, EntityType<YetiEntity>> YETI = register("yeti", EntityTypeRegistrar.monster(YetiEntity::new).sized(0.6875f, 2.25f, 1.9375f).spawnEgg(0xE0E2E2, 0x393D3D));
	public static final DeferredHolder<EntityType<?>, EntityType<NospikeEntity>> NOSPIKE = register("nospike", EntityTypeRegistrar.monster(NospikeEntity::new).sized(0.9375f, 2.3125f, 3.03125f).spawnEgg(0x8C6B58, 0x312D2C));
	public static final DeferredHolder<EntityType<?>, EntityType<TharaflyEntity>> THARAFLY = register("tharafly", EntityTypeRegistrar.monster(TharaflyEntity::new).sized(0.6f, 1.14f, 0.3125f).spawnEgg(0x4B3C38, 0xD86C41));

	public static final DeferredHolder<EntityType<?>, EntityType<IceGiantEntity>> ICE_GIANT = register("ice_giant", EntityTypeRegistrar.monster(IceGiantEntity::new).sized(1.25f, 3.59375f, 2.65625f).spawnEgg(0x8AB5C7, 0x54819A));
	public static final DeferredHolder<EntityType<?>, EntityType<LeafyGiantEntity>> LEAFY_GIANT = register("leafy_giant", EntityTypeRegistrar.monster(LeafyGiantEntity::new).sized(1.25f, 3.75f, 3f).spawnEgg(0x12680D, 0x392513));
	public static final DeferredHolder<EntityType<?>, EntityType<SandGiantEntity>> SAND_GIANT = register("sand_giant", EntityTypeRegistrar.monster(SandGiantEntity::new).sized(1.5f, 3.46875f, 2.9375f).spawnEgg(0xC5B996, 0xAC9974));
	public static final DeferredHolder<EntityType<?>, EntityType<StoneGiantEntity>> STONE_GIANT = register("stone_giant", EntityTypeRegistrar.monster(StoneGiantEntity::new).sized(1.3125f, 5.0625f, 4.34375f).fireImmune().spawnEgg(0x56473C, 0x43352B));
	public static final DeferredHolder<EntityType<?>, EntityType<WoodGiantEntity>> WOOD_GIANT = register("wood_giant", EntityTypeRegistrar.monster(WoodGiantEntity::new).sized(1.5f, 3.375f, 2.875f).spawnEgg(0x291E12, 0x3E311E));
	public static final DeferredHolder<EntityType<?>, EntityType<NethengeicBeastEntity>> NETHENGEIC_BEAST = register("nethengeic_beast", EntityTypeRegistrar.monster(NethengeicBeastEntity::new).sized(1.25f, 3.75f, 3.375f).fireImmune().spawnEgg(0x1A1513, 0xD43D10));
	public static final DeferredHolder<EntityType<?>, EntityType<SkeletalAbominationEntity>> SKELETAL_ABOMINATION = register("skeletal_abomination", EntityTypeRegistrar.monster(SkeletalAbominationEntity::new).sized(0.7f, 1.3125f, 1.65625f).fireImmune().spawnEgg(0xCFCCBE, 0xA69E8C));

	public static final DeferredHolder<EntityType<?>, EntityType<WoundedTyrosaurEntity>> WOUNDED_TYROSAUR = register("wounded_tyrosaur", EntityTypeRegistrar.monster(WoundedTyrosaurEntity::new).sized(1.375f, 1.6875f, 1));

	public static final DeferredHolder<EntityType<?>, EntityType<SmashEntity>> SMASH = register("smash", EntityTypeRegistrar.monster(SmashEntity::new).sized(1.375f, 3.375f, 3.0625f).spawnEgg(0x644E31, 0x3C2E1E));
	public static final DeferredHolder<EntityType<?>, EntityType<EliteSmashEntity>> ELITE_SMASH = register("elite_smash", EntityTypeRegistrar.monster(EliteSmashEntity::new).sized(1.375f, 3.375f, 3.0625f).spawnEgg(0x644E31, 0x3C2E1E));
	public static final DeferredHolder<EntityType<?>, EntityType<NethengeicWitherEntity>> NETHENGEIC_WITHER = register("nethengeic_wither", EntityTypeRegistrar.monster(NethengeicWitherEntity::new).sized(1.375f, 3.6875f, 3).fireImmune().spawnEgg(0x1D1816, 0xDE4D13));
	public static final DeferredHolder<EntityType<?>, EntityType<EliteNethengeicWitherEntity>> ELITE_NETHENGEIC_WITHER = register("elite_nethengeic_wither", EntityTypeRegistrar.monster(EliteNethengeicWitherEntity::new).sized(1.375f, 3.6875f, 3).fireImmune().spawnEgg(0x1D1816, 0xDE4D13));
	public static final DeferredHolder<EntityType<?>, EntityType<KingBamBamBamEntity>> KING_BAMBAMBAM = register("king_bambambam", EntityTypeRegistrar.monster(KingBamBamBamEntity::new).sized(1.125f, 2.75f, 2.15625f).fireImmune().spawnEgg(0x211C1A, 0x4D352B));
	public static final DeferredHolder<EntityType<?>, EntityType<EliteKingBamBamBamEntity>> ELITE_KING_BAMBAMBAM = register("elite_king_bambambam", EntityTypeRegistrar.monster(EliteKingBamBamBamEntity::new).sized(1.125f, 2.75f, 2.15625f).fireImmune().spawnEgg(0x211C1A, 0x4D352B));
	public static final DeferredHolder<EntityType<?>, EntityType<TyrosaurEntity>> TYROSAUR = register("tyrosaur", EntityTypeRegistrar.monster(TyrosaurEntity::new).sized(1.375f, 1.6875f, 1).spawnEgg(0x5A4536, 0x252323));
	public static final DeferredHolder<EntityType<?>, EntityType<EliteTyrosaurEntity>> ELITE_TYROSAUR = register("elite_tyrosaur", EntityTypeRegistrar.monster(EliteTyrosaurEntity::new).sized(1.375f, 1.6875f, 1).spawnEgg(0x5A4536, 0x252323));
	public static final DeferredHolder<EntityType<?>, EntityType<SkeletronEntity>> SKELETRON = register("skeletron", EntityTypeRegistrar.monster(SkeletronEntity::new).sized(1.375f, 1.6875f, 1.0625f).spawnEgg(0x5A4536, 0x252323));
	public static final DeferredHolder<EntityType<?>, EntityType<EliteSkeletronEntity>> ELITE_SKELETRON = register("elite_skeletron", EntityTypeRegistrar.monster(EliteSkeletronEntity::new).sized(1.375f, 1.6875f, 1.0625f).spawnEgg(0x5A4536, 0x252323));

	//public static final DeferredHolder<EntityType<?>, EntityType<AirheadEntity>> AIRHEAD = registerMob("airhead", AirheadEntity::new, 1.0f, 1.6875f, 8506334, 6004943);
	//public static final DeferredHolder<EntityType<?>, EntityType<AlarmoEntity>> ALARMO = registerMob("alarmo", AlarmoEntity::new, 0.625f, 1.375f, 14555160, 6048330);
	//public static final DeferredHolder<EntityType<?>, EntityType<AmphibiorEntity>> AMPHIBIOR = registerMob("amphibior", AmphibiorEntity::new, 0.625f, 2.1875f, 6026738, 16757504);
	//public static final DeferredHolder<EntityType<?>, EntityType<AmphibiyteEntity>> AMPHIBIYTE = registerMob("amphibiyte", AmphibiyteEntity::new, 0.95f, 1.25f, 5140613, 16744695);

	//public static final DeferredHolder<EntityType<?>, EntityType<AnemiaEntity>> ANEMIA = registerMob("anemia", AnemiaEntity::new, 2.25f, 3f, 5179400, 16711680);
	//public static final DeferredHolder<EntityType<?>, EntityType<AnglerEntity>> ANGLER = registerMob("angler", AnglerEntity::new, 0.75f, 0.875f, 0x143A4D, 0x216279);
	//public static final DeferredHolder<EntityType<?>, EntityType<ApparitionEntity>> APPARITION = registerMob("apparition", ApparitionEntity::new, 0.5f, 1.5625f, 7040112, 526344);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArcFlowerEntity>> ARC_FLOWER = registerMob("arc_flower", ArcFlowerEntity::new, 0.6875f, 0.9375f, 5418751, 15073024);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArcWizardEntity>> ARC_WIZARD = registerMob("arc_wizard", ArcWizardEntity::new, 0.6f, 2.3f, 15269714, 4915148);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArcbeastEntity>> ARCBEAST = registerMob("arcbeast", ArcbeastEntity::new, 0.8125f, 1.3125f, 0xF88611, 0x0DA0D0);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArchvineEntity>> ARCHVINE = registerMob("archvine", ArchvineEntity::new, 1.5f, 1.4375f, 15616, 1835776);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArcwormEntity>> ARCWORM = registerMob("arcworm", ArcwormEntity::new, 0.7f, 1.0f, 16768256, 9109501);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArielEntity>> ARIEL = registerMob("ariel", ArielEntity::new, 0.7f, 2.375f, 6809323, 13959166);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArkbackEntity>> ARKBACK = registerMob("arkback", ArkbackEntity::new, 3.8f, 3f, 12099007, 8943244);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArkzyneEntity>> ARKZYNE = registerMob("arkzyne", ArkzyneEntity::new, 0.5625f, 2.6875f, 789516, 10690061);
	//public static final DeferredHolder<EntityType<?>, EntityType<ArocknidEntity>> AROCKNID = registerMob("arocknid", ArocknidEntity::new, 0.875f, 1f, 0x4A4A4A, 0x333333);

	//public static final DeferredHolder<EntityType<?>, EntityType<AxiolightEntity>> AXIOLIGHT = registerMob("axiolight", AxiolightEntity::new, 0.7f, 2.4375f, 16777215, 5820635);
	//public static final DeferredHolder<EntityType<?>, EntityType<BansheeEntity>> BANSHEE = registerMob("banshee", BansheeEntity::new, 0.875f, 2.375f, 11572669, 8202406);
	//public static final DeferredHolder<EntityType<?>, EntityType<BasiliskEntity>> BASILISK = registerMob("basilisk", BasiliskEntity::new, 0.75f, 1.375f, 1644570, 4736842);
	//public static final DeferredHolder<EntityType<?>, EntityType<BaumbaEntity>> BAUMBA = registerMob("baumba", BaumbaEntity::new, 0.5f, 2f, 9184292, 9071156);
	//public static final DeferredHolder<EntityType<?>, EntityType<BloodsuckerEntity>> BLOODSUCKER = registerMob("bloodsucker", BloodsuckerEntity::new, 1.3f, 1.0f, 3673357, 8397860);
	//public static final DeferredHolder<EntityType<?>, EntityType<BoboEntity>> BOBO = registerMob("bobo", BoboEntity::new, 0.6f, 2.125f, 1782866, 2249377);

	//public static final DeferredHolder<EntityType<?>, EntityType<BoneCreeperEntity>> BONE_CREEPER = registerMob("bone_creeper", BoneCreeperEntity::new, 0.5f, 1.625f, 10922149, 2915064);
	//public static final DeferredHolder<EntityType<?>, EntityType<BouncerEntity>> BOUNCER = registerMob("bouncer", BouncerEntity::new, 0.5f, 1.4375f, 5537210, 13685462);
	//public static final DeferredHolder<EntityType<?>, EntityType<BroccoheadEntity>> BROCCOHEAD = registerMob("broccohead", BroccoheadEntity::new, 0.5625f, 2.3625f, 2511901, 5668175);

	//public static final DeferredHolder<EntityType<?>, EntityType<CandyCornyEntity>> CANDY_CORNY = registerMob("candy_corny", CandyCornyEntity::new, 0.625f, 2f, 13932049, 15459377);
	//public static final DeferredHolder<EntityType<?>, EntityType<CaneBugEntity>> CANE_BUG = registerMob("cane_bug", CaneBugEntity::new, 1f, 1.5f, 4352288, 16119280);
	//public static final DeferredHolder<EntityType<?>, EntityType<CarrotopEntity>> CARROTOP = registerMob("carrotop", CarrotopEntity::new, 0.5625f, 2.375f, 16747528, 4914952);
	//public static final DeferredHolder<EntityType<?>, EntityType<CaseConstructEntity>> CASE_CONSTRUCT = registerMob("case_construct", CaseConstructEntity::new, true, 1 + 1 / 16f, 1 + 4 / 16f, 0x3A3535, 0x5D5757);
	//public static final DeferredHolder<EntityType<?>, EntityType<CaveCreepEntity>> CAVE_CREEP = registerMob("cave_creep", CaveCreepEntity::new, 9 / 16f, 13 / 16f, 0x494949, 0x373737);
	//public static final DeferredHolder<EntityType<?>, EntityType<CaveCreepoidEntity>> CAVE_CREEPOID = registerMob("cave_creepoid", CaveCreepoidEntity::new, 0.875f, 1.6875f, 3684406, 8026744);
	//public static final DeferredHolder<EntityType<?>, EntityType<CentinelEntity>> CENTINEL = registerMob("centinel", CentinelEntity::new, 0.6875f, 1.625f, 3951159, 4606532);

	//public static final DeferredHolder<EntityType<?>, EntityType<CherryBlasterEntity>> CHERRY_BLASTER = registerMob("cherry_blaster", CherryBlasterEntity::new, 0.875f, 1.0625f, 14032414, 5400890);
	//public static final DeferredHolder<EntityType<?>, EntityType<ChockoEntity>> CHOCKO = registerMob("chocko", ChockoEntity::new, 0.6f, 2.0f, 8004884, 3346956);

	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfFlightEntity>> CONSTRUCT_OF_FLIGHT = registerMob("construct_of_flight", ConstructOfFlightEntity::new, 0.7f, 0.84375f, 5033917, 1382167);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfMindEntity>> CONSTRUCT_OF_MIND = registerMob("construct_of_mind", ConstructOfMindEntity::new, 2f, 2f, 1711130, 4298362);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfRangeEntity>> CONSTRUCT_OF_RANGE = registerMob("construct_of_range", ConstructOfRangeEntity::new, 1.7f, 1.5f, 6427013, 789261);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfResistanceEntity>> CONSTRUCT_OF_RESISTANCE = registerMob("construct_of_resistance", ConstructOfResistanceEntity::new, 0.625f, 2.375f, 1002578, 3486008);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfSpeedEntity>> CONSTRUCT_OF_SPEED = registerMob("construct_of_speed", ConstructOfSpeedEntity::new, 0.625f, 2.1875f, 11768628, 6907225);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfStrengthEntity>> CONSTRUCT_OF_STRENGTH = registerMob("construct_of_strength", ConstructOfStrengthEntity::new, 1f, 2.375f, 11021588, 3354157);
	//public static final DeferredHolder<EntityType<?>, EntityType<ConstructOfTerrorEntity>> CONSTRUCT_OF_TERROR = registerMob("construct_of_terror", ConstructOfTerrorEntity::new, 1.0f, 1.0f, 4299043, 3354157);
	//public static final DeferredHolder<EntityType<?>, EntityType<CornyEntity>> CORNY = registerMob("corny", CornyEntity::new, 0.625f, 2f, 35104, 13551360);
	//public static final DeferredHolder<EntityType<?>, EntityType<CreeperlockEntity>> CREEPERLOCK = registerMob("creeperlock", CreeperlockEntity::new, 0.6f, 2.37f, 818944, 5777591);
	//public static final DeferredHolder<EntityType<?>, EntityType<CreepirdEntity>> CREEPIRD = registerMob("creepird", CreepirdEntity::new, 0.5f, 0.6875f, 818944, 3780390);
	//public static final DeferredHolder<EntityType<?>, EntityType<CreepupleEntity>> CREEPUPLE = registerMob("creepuple", CreepupleEntity::new, 0.6f, 1.5625f, 818944, 3843364);
	//public static final DeferredHolder<EntityType<?>, EntityType<CrusiliskEntity>> CRUSILISK = registerMob("crusilisk", CrusiliskEntity::new, 0.75f, 1.3125f, 660523, 15569979);
	//public static final DeferredHolder<EntityType<?>, EntityType<CryptidEntity>> CRYPTID = registerMob("cryptid", CryptidEntity::new, true, 0.875f, 1.0625f, 10038339, 10180711);

	//public static final DeferredHolder<EntityType<?>, EntityType<DayseeEntity>> DAYSEE = registerMob("daysee", DayseeEntity::new, 0.5f, 2.0625f, 11913662, 6272881);
	//public static final DeferredHolder<EntityType<?>, EntityType<DestructorEntity>> DESTRUCTOR = registerMob("destructor", DestructorEntity::new, 2.2f, 8.53125f, 2895664, 10696493);
	//public static final DeferredHolder<EntityType<?>, EntityType<DevourerEntity>> DEVOURER = registerMob("devourer", DevourerEntity::new, 0.875f, 1.1875f, 1249827, 3750461);
	//public static final DeferredHolder<EntityType<?>, EntityType<DistorterEntity>> DISTORTER = registerMob("distorter", DistorterEntity::new, 0.6f, 2.125f, 1579066, 2841448);
	//public static final DeferredHolder<EntityType<?>, EntityType<DoublerEntity>> DOUBLER = registerMob("doubler", DoublerEntity::new, 1.75f, (5 * 16 + 11) / 16f, 0x3A3A3A, 0x595959);

	//public static final DeferredHolder<EntityType<?>, EntityType<DustStriderEntity>> DUST_STRIDER = registerMob("dust_strider", DustStriderEntity::new, 0.8f, 1.25f, 986902, 8662823);
	//public static final DeferredHolder<EntityType<?>, EntityType<DusteivaEntity>> DUSTEIVA = registerMob("dusteiva", DusteivaEntity::new, 0.6f, 2.25f, 3150604, 10037031);
	//public static final DeferredHolder<EntityType<?>, EntityType<DustonEntity>> DUSTON = registerMob("duston", DustonEntity::new, 0.6f, 1.5f, 14027274, 857601);
	//public static final DeferredHolder<EntityType<?>, EntityType<DwellerEntity>> DWELLER = registerMob("dweller", DwellerEntity::new, 0.625f, 2.4f, 5197117, 3225407);
	//public static final DeferredHolder<EntityType<?>, EntityType<EchodarEntity>> ECHODAR = registerMob("echodar", EchodarEntity::new, 0.75f, 0.75f, 9388691, 11026825);

	//public static final DeferredHolder<EntityType<?>, EntityType<EmperorBeastEntity>> EMPEROR_BEAST = registerMob("emperor_beast", EmperorBeastEntity::new, 1.7f, 6.8f, 3540548, 7480842);
	//public static final DeferredHolder<EntityType<?>, EntityType<EnforcerEntity>> ENFORCER = registerMob("enforcer", EnforcerEntity::new, 0.75f, 2.25f, 1646128, 3949091);
	//public static final DeferredHolder<EntityType<?>, EntityType<ExoheadEntity>> EXOHEAD = registerMob("exohead", ExoheadEntity::new, 0.6f, 1.8125f, 1841431, 6500657);
	//public static final DeferredHolder<EntityType<?>, EntityType<ExplodotEntity>> EXPLODOT = registerMob("explodot", ExplodotEntity::new, 0.6f, 1f, 1718972, 6452410);
	//public static final DeferredHolder<EntityType<?>, EntityType<FacelessFloaterEntity>> FACELESS_FLOATER = registerMob("faceless_floater", FacelessFloaterEntity::new, 0.75f, 2f, 5187949, 5784171);
	//public static final DeferredHolder<EntityType<?>, EntityType<FakeZorpEntity>> FAKE_ZORP = registerMob("fake_zorp", FakeZorpEntity::new, 0.6f, 1.875f, 1093861, 14347506);
	//public static final DeferredHolder<EntityType<?>, EntityType<FiendEntity>> FIEND = registerMob("fiend", FiendEntity::new, 0.5625f, 1.5f, 4790796, 4662298);
	//public static final DeferredHolder<EntityType<?>, EntityType<FischerEntity>> FISCHER = registerMob("fischer", FischerEntity::new, 0.75f, 1.0f, 3156005, 11349293);

	//public static final DeferredHolder<EntityType<?>, EntityType<FleshEaterEntity>> FLESH_EATER = registerMob("flesh_eater", FleshEaterEntity::new, 1f, 1.25f, 2827535, 9646899);
	//public static final DeferredHolder<EntityType<?>, EntityType<FlowerfaceEntity>> FLOWERFACE = registerMob("flowerface", FlowerfaceEntity::new, 0.5f, 1.5f, 4229424, 11025567);
	//public static final DeferredHolder<EntityType<?>, EntityType<FlyeEntity>> FLYE = registerMob("flye", FlyeEntity::new, 10 / 16f, 19 / 16f, 0xE6725A, 0xFDC570);
	//public static final DeferredHolder<EntityType<?>, EntityType<FungatEntity>> FUNGAT = registerMob("fungat", FungatEntity::new, 0.75f, 1.125f, 1820193, 7281338);
	//public static final DeferredHolder<EntityType<?>, EntityType<FungbackEntity>> FUNGBACK = registerMob("fungback", FungbackEntity::new, 1.0f, 0.875f, 16578797, 4211836);
	//public static final DeferredHolder<EntityType<?>, EntityType<FungikEntity>> FUNGIK = registerMob("fungik", FungikEntity::new, 0.875f, 2.5f, 16578797, 4093269);
	//public static final DeferredHolder<EntityType<?>, EntityType<FungockEntity>> FUNGOCK = registerMob("fungock", FungockEntity::new, 0.875f, 2.125f, 8552047, 7166001);
	//public static final DeferredHolder<EntityType<?>, EntityType<FungungEntity>> FUNGUNG = registerMob("fungung", FungungEntity::new, 0.625f, 2.1875f, 16578797, 2247333);
	//public static final DeferredHolder<EntityType<?>, EntityType<GadgetoidEntity>> GADGETOID = registerMob("gadgetoid", GadgetoidEntity::new, 1f, 1.8125f, 7037752, 12855593);

	//public static final DeferredHolder<EntityType<?>, EntityType<GingerbirdEntity>> GINGERBIRD = registerMob("gingerbird", GingerbirdEntity::new, true, 0.5f, 0.5625f, 7818256, 2979202);
	//public static final DeferredHolder<EntityType<?>, EntityType<GingerbreadManEntity>> GINGERBREAD_MAN = registerMob("gingerbread_man", GingerbreadManEntity::new, true, 0.59375f, 2.125f, 7818256, 7291276);

	//public static final DeferredHolder<EntityType<?>, EntityType<GrillfaceEntity>> GRILLFACE = registerMob("grillface", GrillfaceEntity::new, 0.6875f, 2.25f, 2562160, 10820175);
	//public static final DeferredHolder<EntityType<?>, EntityType<GrobblerEntity>> GROBBLER = registerMob("grobbler", GrobblerEntity::new, 1.5f, 2.375f, 14374157, 2168079);
	//public static final DeferredHolder<EntityType<?>, EntityType<HappyEntity>> HAPPY = registerMob("happy", HappyEntity::new, 0.5f, 2f, 5376265, 14597565);
	//public static final DeferredHolder<EntityType<?>, EntityType<HostEntity>> HOST = registerMob("host", HostEntity::new, 2.1875f, 2.0625f, 4629316, 11321260);
	//public static final DeferredHolder<EntityType<?>, EntityType<HunterEntity>> HUNTER = registerMob("hunter", HunterEntity::new, 1.3f, 1.3f, 3152742, 4007949);


	//public static final DeferredHolder<EntityType<?>, EntityType<InmateXEntity>> INMATE_X = registerMob("inmate_x", InmateXEntity::new, 0.6f, 2f, 14165970, 15138567);
	//public static final DeferredHolder<EntityType<?>, EntityType<InmateYEntity>> INMATE_Y = registerMob("inmate_y", InmateYEntity::new, 0.75f, 1.75f, 792825, 10513678);
	//public static final DeferredHolder<EntityType<?>, EntityType<JaweEntity>> JAWE = registerMob("jawe", JaweEntity::new, 0.8f, 0.9f, 6825253, 6838617);
	//public static final DeferredHolder<EntityType<?>, EntityType<JumboEntity>> JUMBO = registerMob("jumbo", JumboEntity::new, 0.75f, 2.625f, 9197854, 15723594);
	//public static final DeferredHolder<EntityType<?>, EntityType<KeelerEntity>> KEELER = registerMob("keeler", KeelerEntity::new, 0.6875f, 1.4f, 9271950, 10507941);

	//public static final DeferredHolder<EntityType<?>, EntityType<KingCreeperEntity>> KING_CREEPER = registerMob("king_creeper", KingCreeperEntity::new, 0.6f, 1.9375f, 2448937, 8348682);
	//public static final DeferredHolder<EntityType<?>, EntityType<KokoEntity>> KOKO = registerMob("koko", KokoEntity::new, 0.6f, 2.0f, 16189444, 15569811);
	//public static final DeferredHolder<EntityType<?>, EntityType<KrankyEntity>> KRANKY = registerMob("kranky", KrankyEntity::new, 0.6f, 2.3f, 325397, 15569811);

	//public static final DeferredHolder<EntityType<?>, EntityType<LelyetianCasterEntity>> LELYETIAN_CASTER = registerMob("lelyetian_caster", LelyetianCasterEntity::new, 0.6f, 2.375f, 12330277, 14413608);
	//public static final DeferredHolder<EntityType<?>, EntityType<LelyetianWarriorEntity>> LELYETIAN_WARRIOR = registerMob("lelyetian_warrior", LelyetianWarriorEntity::new, 0.6f, 2.375f, 12330277, 13489046);
	//public static final DeferredHolder<EntityType<?>, EntityType<LightwalkerEntity>> LIGHTWALKER = registerMob("lightwalker", LightwalkerEntity::new, 1.0f, 1.5625f, 7765779, 2895137);

	//public static final DeferredHolder<EntityType<?>, EntityType<LollypopperEntity>> LOLLYPOPPER = registerMob("lollypopper", LollypopperEntity::new, 1.0625f, 2.4375f, 13387173, 14069706);
	//public static final DeferredHolder<EntityType<?>, EntityType<LostSoulEntity>> LOST_SOUL = registerMob("lost_soul", LostSoulEntity::new, 0.6f, 2.0f, 1578521, 11774647);
	//public static final DeferredHolder<EntityType<?>, EntityType<LunarcherEntity>> LUNARCHER = registerMob("lunarcher", LunarcherEntity::new, true, 0.6f, 2.0f, 7932315, 13991919);
	//public static final DeferredHolder<EntityType<?>, EntityType<LurkerEntity>> LURKER = registerMob("lurker", LurkerEntity::new, 0.875f, 2.0625f, 525578, 14354440);
	//public static final DeferredHolder<EntityType<?>, EntityType<LuxocronEntity>> LUXOCRON = registerMob("luxocron", LuxocronEntity::new, 0.6875f, 0.9375f, 14871822, 6932197);
	//public static final DeferredHolder<EntityType<?>, EntityType<MagicalCreeperEntity>> MAGICAL_CREEPER = registerMob("magical_creeper", MagicalCreeperEntity::new, 0.6f, 2.37f, 350480, 2765611);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechachronEntity>> MECHACHRON = registerMob("mechachron", MechachronEntity::new, 2f, 1.875f, 2369576, 15920978);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechamatonEntity>> MECHAMATON = registerMob("mechamaton", MechamatonEntity::new, 1.125f, 2.125f, 9340427, 131585);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechyonEntity>> MECHYON = registerMob("mechyon", MechyonEntity::new, 0.75f, 1.5f, 3355435, 4013324);
	//public static final DeferredHolder<EntityType<?>, EntityType<MerkyreEntity>> MERKYRE = registerMob("merkyre", MerkyreEntity::new, 0.5625f, 2f, 2500131, 1835779);
	//public static final DeferredHolder<EntityType<?>, EntityType<MermageEntity>> MERMAGE = registerMob("mermage", MermageEntity::new, 0.6f, 2.125f, 262786, 1756891);
	//public static final DeferredHolder<EntityType<?>, EntityType<ModuloEntity>> MODULO = registerMob("modulo", ModuloEntity::new, 1.0f, 1.2f, 5282713, 9060540);
	//public static final DeferredHolder<EntityType<?>, EntityType<MuncherEntity>> MUNCHER = registerMob("muncher", MuncherEntity::new, 1.0f, 0.5625f, 0x1E2A48, 0xC5A1CF);
	//public static final DeferredHolder<EntityType<?>, EntityType<MushroomSpiderEntity>> MUSHROOM_SPIDER = registerMob("mushroom_spider", MushroomSpiderEntity::new, 1.4f, 0.8125f, 1739049, 12827332);
	//public static final DeferredHolder<EntityType<?>, EntityType<NeptunoEntity>> NEPTUNO = registerMob("neptuno", NeptunoEntity::new, 0.85f, 2.875f, 0x1D4D68, 0xEBB332);

	//public static final DeferredHolder<EntityType<?>, EntityType<NightmareSpiderEntity>> NIGHTMARE_SPIDER = registerMob("nightmare_spider", NightmareSpiderEntity::new, 1.4f, 0.8125f, 1574189, 5180318);
	//public static final DeferredHolder<EntityType<?>, EntityType<NightwingEntity>> NIGHTWING = registerMob("nightwing", NightwingEntity::new, 0.75f, 0.9375f, 4012325, 6298909);
	//public static final DeferredHolder<EntityType<?>, EntityType<NipperEntity>> NIPPER = registerMob("nipper", NipperEntity::new, 8 / 16f, 12 / 16f, 0x3B3B3B, 0x4E4E4E);
	//public static final DeferredHolder<EntityType<?>, EntityType<NospikeEntity>> NOSPIKE = registerMob("nospike", NospikeEntity::new, 1.0f, 1.25f, 11509027, 3683623);
	//public static final DeferredHolder<EntityType<?>, EntityType<OcculentEntity>> OCCULENT = registerMob("occulent", OcculentEntity::new, 0.6f, 1.5f, 4802884, 3671558);
	//public static final DeferredHolder<EntityType<?>, EntityType<OmnilightEntity>> OMNILIGHT = registerMob("omnilight", OmnilightEntity::new, 0.9f, 0.9f, 0x68A0BA, 0xBFDDD2);
	//public static final DeferredHolder<EntityType<?>, EntityType<PaladinEntity>> PALADIN = registerMob("paladin", PaladinEntity::new, 0.6875f, 2.0f, 15921889, 2019053);
	//public static final DeferredHolder<EntityType<?>, EntityType<ParasectEntity>> PARASECT = registerMob("parasect", ParasectEntity::new, 0.6f, 1.375f, 5011275, 2481439);
	//public static final DeferredHolder<EntityType<?>, EntityType<ParaviteEntity>> PARAVITE = registerMob("paravite", ParaviteEntity::new, 0.625f, 1.75f, 16535301, 16552455);
	//public static final DeferredHolder<EntityType<?>, EntityType<PodPlantEntity>> POD_PLANT = registerMob("pod_plant", PodPlantEntity::new, 0.7f, 0.625f, 1307185, 13882270);
	//public static final DeferredHolder<EntityType<?>, EntityType<PolytomEntity>> POLYTOM = registerMob("polytom", PolytomEntity::new, 1f, 1.125f, 7491330, 1972754);
	//public static final DeferredHolder<EntityType<?>, EntityType<RamradonEntity>> RAMRADON = registerMob("ramradon", RamradonEntity::new, 0.875f, 1f, 8365446, 8028027);
	//public static final DeferredHolder<EntityType<?>, EntityType<RawboneEntity>> RAWBONE = registerMob("rawbone", RawboneEntity::new, 0.625f, 1.0625f, 12561595, 14731739);
	//public static final DeferredHolder<EntityType<?>, EntityType<RefluctEntity>> REFLUCT = registerMob("refluct", RefluctEntity::new, 0.6f, 2f, 1177865, 11850163);
	//public static final DeferredHolder<EntityType<?>, EntityType<RockCrawlerEntity>> ROCK_CRAWLER = registerMob("rock_crawler", RockCrawlerEntity::new, 8 / 16f, 1f, 0x525252, 0x3E3E3E);
	//public static final DeferredHolder<EntityType<?>, EntityType<RockCritterEntity>> ROCK_CRITTER = registerMob("rock_critter", RockCritterEntity::new, 8 / 16f, 8 / 16f, 0x555555, 0x3D3D3D);
	//public static final DeferredHolder<EntityType<?>, EntityType<RockbiterEntity>> ROCKBITER = registerMob("rockbiter", RockbiterEntity::new, 1f, 8 / 16f, 0x434343, 0x313131);
	//public static final DeferredHolder<EntityType<?>, EntityType<RunicGolemEntity>> RUNIC_GOLEM = registerMob("runic_golem", RunicGolemEntity::new, 0.75f, 1.75f, 4655720, 2712772);
	//public static final DeferredHolder<EntityType<?>, EntityType<RunicGuardianEntity>> RUNIC_GUARDIAN = registerMob("runic_guardian", RunicGuardianEntity::new, 0.6f, 2f, 336465, 7305092);
	//public static final DeferredHolder<EntityType<?>, EntityType<RunicornEntity>> RUNICORN = registerMob("runicorn", RunicornEntity::new, 0.6875f, 2f, 5411475, 4804431);
	//public static final DeferredHolder<EntityType<?>, EntityType<RunicornRiderEntity>> RUNICORN_RIDER = registerMob("runicorn_rider", RunicornRiderEntity::new, 0.7f, 2.5625f, 5411475, 539296);


	//public static final DeferredHolder<EntityType<?>, EntityType<SeaViperEntity>> SEA_VIPER = registerMob("sea_viper", SeaViperEntity::new, 0.4375f, 0.59375f, 0x2D6773, 0x69C8C1);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShifterEntity>> SHIFTER = registerMob("shifter", ShifterEntity::new, 0.75f, 1.5625f, 1446161, 656902);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShyreKnightEntity>> SHYRE_KNIGHT = registerMob("shyre_knight", ShyreKnightEntity::new, 0.6f, 2f, 967917, 13232654);
	//public static final DeferredHolder<EntityType<?>, EntityType<SilencerEntity>> SILENCER = registerMob("silencer", SilencerEntity::new, 0.5625f, 2f, 4667168, 13880772);
	//public static final DeferredHolder<EntityType<?>, EntityType<SkullCreatureEntity>> SKULL_CREATURE = registerMob("skull_creature", SkullCreatureEntity::new, 0.6875f, 2.3125f, 2496514, 3351359);
	//public static final DeferredHolder<EntityType<?>, EntityType<SlimerEntity>> SLIMER = registerMob("slimer", SlimerEntity::new, 1.7f, 3.125f, 3683898, 978238);

	//public static final DeferredHolder<EntityType<?>, EntityType<SnappyEntity>> SNAPPY = registerMob("snappy", SnappyEntity::new, 0.6f, 2.0f, 1573411, 590407);
	//public static final DeferredHolder<EntityType<?>, EntityType<SoulscorneEntity>> SOULSCORNE = registerMob("soulscorne", SoulscorneEntity::new, 0.6f, 1.8125f, 14809348, 977903);
	//public static final DeferredHolder<EntityType<?>, EntityType<SoulvyreEntity>> SOULVYRE = registerMob("soulvyre", SoulvyreEntity::new, 0.6f, 2.125f, 14743310, 1369563);
	//public static final DeferredHolder<EntityType<?>, EntityType<SpectralWizardEntity>> SPECTRAL_WIZARD = registerMob("spectral_wizard", SpectralWizardEntity::new, 0.5f, 2.1875f, 15267050, 52722);

	//public static final DeferredHolder<EntityType<?>, EntityType<SpiritGuardianEntity>> SPIRIT_GUARDIAN = registerMob("spirit_guardian", SpiritGuardianEntity::new, 0.6f, 1.8125f, 15265007, 13881253);
	//public static final DeferredHolder<EntityType<?>, EntityType<SpiritProtectorEntity>> SPIRIT_PROTECTOR = registerMob("spirit_protector", SpiritProtectorEntity::new, 0.6f, 1.8125f, 15265007, 14867354);
	//public static final DeferredHolder<EntityType<?>, EntityType<SquasherEntity>> SQUASHER = registerMob("squasher", SquasherEntity::new, 0.75f, 1.6f, 5732923, 9476961);
	//public static final DeferredHolder<EntityType<?>, EntityType<SquigglerEntity>> SQUIGGLER = registerMob("squiggler", SquigglerEntity::new, true, 0.5625f, 1.6875f, 7474700, 13533053);
	//public static final DeferredHolder<EntityType<?>, EntityType<StalkerEntity>> STALKER = registerMob("stalker", StalkerEntity::new, 0.6f, 2.5625f, 984582, 1841947);
	//public static final DeferredHolder<EntityType<?>, EntityType<StickyEntity>> STICKY = registerMob("sticky", StickyEntity::new, 0.6f, 2.0f, 16777215, 14084119);
	//public static final DeferredHolder<EntityType<?>, EntityType<StimuloEntity>> STIMULO = registerMob("stimulo", StimuloEntity::new, 0.6f, 1.875f, 653303, 14808864);
	//public static final DeferredHolder<EntityType<?>, EntityType<StimulosusEntity>> STIMULOSUS = registerMob("stimulosus", StimulosusEntity::new, 0.6f, 1.875f, 653303, 13953058);
	//public static final DeferredHolder<EntityType<?>, EntityType<StitchesEntity>> STITCHES = registerMob("stitches", StitchesEntity::new, 0.6f, 2.0f, 14498831, 10187624);

	//public static final DeferredHolder<EntityType<?>, EntityType<SugarfaceEntity>> SUGARFACE = registerMob("sugarface", SugarfaceEntity::new, 0.5625f, 2.125f, 11963839, 15787762);
	//public static final DeferredHolder<EntityType<?>, EntityType<SunnyEntity>> SUNNY = registerMob("sunny", SunnyEntity::new, 0.75f, 2.375f, 653073, 13750551);
	//public static final DeferredHolder<EntityType<?>, EntityType<SyskerEntity>> SYSKER = registerMob("sysker", SyskerEntity::new, 0.6f, 1.5f, 0xF98212, 0x0E9ED0);
	//public static final DeferredHolder<EntityType<?>, EntityType<TerrestrialEntity>> TERRESTRIAL = registerMob("terrestrial", TerrestrialEntity::new, 1.2f, 2.357f, 5636940, 15666901);
	//public static final DeferredHolder<EntityType<?>, EntityType<TharaflyEntity>> THARAFLY = registerMob("tharafly", TharaflyEntity::new, 0.75f, 1.0f, 4470043, 13748410);
	//public static final DeferredHolder<EntityType<?>, EntityType<TipsyEntity>> TIPSY = registerMob("tipsy", TipsyEntity::new, 0.5f, 2.0f, 1107429, 13748410);
	//public static final DeferredHolder<EntityType<?>, EntityType<ToxxulousEntity>> TOXXULOUS = registerMob("toxxulous", ToxxulousEntity::new, 0.75f, 1.125f, 3165970, 2041878);
	//public static final DeferredHolder<EntityType<?>, EntityType<TrackerEntity>> TRACKER = registerMob("tracker", TrackerEntity::new, 10 / 16f, 14 / 16f, 0x884154, 0xF1785C);

	//public static final DeferredHolder<EntityType<?>, EntityType<UndeadTrollEntity>> UNDEAD_TROLL = registerMob("undead_troll", UndeadTrollEntity::new, 0.6f, 1.8125f, 0xD8D2C6, 0x44576C);
	//public static final DeferredHolder<EntityType<?>, EntityType<ValkyrieEntity>> VALKYRIE = registerMob("valkyrie", ValkyrieEntity::new, 0.75f, 1.125f, 3154454, 10502611);

	//public static final DeferredHolder<EntityType<?>, EntityType<VineWizardEntity>> VINE_WIZARD = registerMob("vine_wizard", VineWizardEntity::new, 0.6f, 2.125f, 801297, 12525086);
	//public static final DeferredHolder<EntityType<?>, EntityType<VisularEntity>> VISULAR = registerMob("visular", VisularEntity::new, 0.625f, 0.9375f, 0x3A3A3A, 0x292929);
	//public static final DeferredHolder<EntityType<?>, EntityType<VisulonEntity>> VISULON = registerMob("visulon", VisulonEntity::new, 0.9375f, 1.0625f, 0x383838, 0x2B282C);

	//public static final DeferredHolder<EntityType<?>, EntityType<VoltronEntity>> VOLTRON = registerMob("voltron", VoltronEntity::new, 0.7f, 2f, 10725391, 2302750);
	//public static final DeferredHolder<EntityType<?>, EntityType<WebReaperEntity>> WEB_REAPER = registerMob("web_reaper", WebReaperEntity::new, 0.75f, 3.5625f, 2494474, 1446932);
	//public static final DeferredHolder<EntityType<?>, EntityType<WingedCreeperEntity>> WINGED_CREEPER = registerMob("winged_creeper", WingedCreeperEntity::new, 0.6f, 1.625f, 484870, 6585443);


	//public static final DeferredHolder<EntityType<?>, EntityType<ZargEntity>> ZARG = registerMob("zarg", ZargEntity::new, 0.875f, 2.0625f, 1803734, 13230578);
	//public static final DeferredHolder<EntityType<?>, EntityType<ZhinxEntity>> ZHINX = registerMob("zhinx", ZhinxEntity::new, 0.6f, 0.6875f, 11056831, 13029730);
	//public static final DeferredHolder<EntityType<?>, EntityType<ZorpEntity>> ZORP = registerMob("zorp", ZorpEntity::new, 0.6f, 1.875f, 1093861, 14347506);














	//public static final DeferredHolder<EntityType<?>, EntityType<BaneEntity>> BANE = registerMob("bane", BaneEntity::new, 0.75f, 2f, 7868105, 4619942);
	//public static final DeferredHolder<EntityType<?>, EntityType<BaronessEntity>> BARONESS = registerMob("baroness", BaronessEntity::new, 0.6875f, 2.75f, 5377810, 2362949);
	//public static final DeferredHolder<EntityType<?>, EntityType<BlueFlowerEntity>> BLUE_FLOWER = registerMob("blue_flower", BlueFlowerEntity::new, 0.7f, 2.3125f, 5086275, 4223129);
	//public static final DeferredHolder<EntityType<?>, EntityType<BlueGuardianEntity>> BLUE_GUARDIAN = registerMob("blue_guardian", BlueGuardianEntity::new, 1.5f, 2.625f, 10266029, 8167615);
	//public static final DeferredHolder<EntityType<?>, EntityType<BlueRuneTemplarEntity>> BLUE_RUNE_TEMPLAR = registerMob("blue_rune_templar", BlueRuneTemplarEntity::new, 1.125f, 2f, 16744228, 7194313);
	//public static final DeferredHolder<EntityType<?>, EntityType<BlueRunicLifeformEntity>> BLUE_RUNIC_LIFEFORM = registerMob("blue_runic_lifeform", BlueRunicLifeformEntity::new, 0.75f, 0.99f, 496022, 463763);
	//public static final DeferredHolder<EntityType<?>, EntityType<BonebackEntity>> BONEBACK = registerMob("boneback", BonebackEntity::new, 0.8125f, 1f, 0x612E2D, 0xCBD8E1);
	//public static final DeferredHolder<EntityType<?>, EntityType<BugeyeEntity>> BUGEYE = registerMob("bugeye", BugeyeEntity::new, 1f, 15 / 16f, 0xBC9745, 0x4B7C42);
	//public static final DeferredHolder<EntityType<?>, EntityType<ClunkheadEntity>> CLUNKHEAD = registerMob("clunkhead", ClunkheadEntity::new, 1f, 2.0625f, 1393533, 6322068);
	//public static final DeferredHolder<EntityType<?>, EntityType<CorallusEntity>> CORALLUS = registerMob("corallus", CorallusEntity::new, 0.75f, 2.875f, 36307, 11862175);
	//public static final DeferredHolder<EntityType<?>, EntityType<CottonCandorEntity>> COTTON_CANDOR = registerMob("cotton_candor", CottonCandorEntity::new, 1.5f, 2.375f, 13990125, 12926954);
	//public static final DeferredHolder<EntityType<?>, EntityType<CraexxeusEntity>> CRAEXXEUS = registerMob("craexxeus", CraexxeusEntity::new, 3.5f, 4.4375f, 14847254, 2876115);
	//public static final DeferredHolder<EntityType<?>, EntityType<CreepEntity>> CREEP = registerMob("creep", CreepEntity::new, 0.7f, 1.75f, 818944, 4608621);
	//public static final DeferredHolder<EntityType<?>, EntityType<CrystocoreEntity>> CRYSTOCORE = registerMob("crystocore", CrystocoreEntity::new, 2.5f, 4.875f, 1315589, 3812640);
	//public static final DeferredHolder<EntityType<?>, EntityType<DoppelgangerEntity>> DOPPELGANGER = registerMob("doppelganger", DoppelgangerEntity::new, 0.6F, 1.8F, 0, ColourUtil.WHITE);
	//public static final DeferredHolder<EntityType<?>, EntityType<DracyonEntity>> DRACYON = registerMob("dracyon", DracyonEntity::new, 1.4f, 1.3125f, 402812, 3553336);
	//public static final DeferredHolder<EntityType<?>, EntityType<ElusiveCloneEntity>> ELUSIVE_CLONE = registerMob("elusive_clone", ElusiveCloneEntity::new, 0.7f, 1.625f);
	//public static final DeferredHolder<EntityType<?>, EntityType<ElusiveEntity>> ELUSIVE = registerMob("elusive", ElusiveEntity::new, 0.7f, 1.625f, 1121630, 1711428);
	//public static final DeferredHolder<EntityType<?>, EntityType<FenixEntity>> FENIX = registerMob("fenix", FenixEntity::new, 0.5f, 1.8125f, 5264212, 7370096);
	//public static final DeferredHolder<EntityType<?>, EntityType<FlashEntity>> FLASH = registerMob("flash", FlashEntity::new, 0.8f, 2f, 2828311, 6315292);
	//public static final DeferredHolder<EntityType<?>, EntityType<GhastusEntity>> GHASTUS = registerMob("ghastus", GhastusEntity::new, 0.5f, 0.8125f, 4999750, 16380643);
	//public static final DeferredHolder<EntityType<?>, EntityType<GoalbyEntity>> GOALBY = registerMob("goalby", GoalbyEntity::new, 13 / 16f, 13 / 16f, 0xEEF1D8, 0x9F98A0);
	//public static final DeferredHolder<EntityType<?>, EntityType<GoldumEntity>> GOLDUM = registerMob("goldum", GoldumEntity::new, 0.6f, 1.5f, 9011712, 1711127);
	//public static final DeferredHolder<EntityType<?>, EntityType<GoldusEntity>> GOLDUS = registerMob("goldus", GoldusEntity::new, 0.6f, 1.5f, 11373338, 1711127);
	//public static final DeferredHolder<EntityType<?>, EntityType<GrawEntity>> GRAW = registerMob("graw", GrawEntity::new, 5f, 4f, 16738816, 16764928);
	//public static final DeferredHolder<EntityType<?>, EntityType<GreenFlowerEntity>> GREEN_FLOWER = registerMob("green_flower", GreenFlowerEntity::new, 0.7f, 2.3125f, 2742453, 4435278);
	//public static final DeferredHolder<EntityType<?>, EntityType<GreenGuardianEntity>> GREEN_GUARDIAN = registerMob("green_guardian", GreenGuardianEntity::new, 1.5f, 2.625f, 4148291, 2937123);
	//public static final DeferredHolder<EntityType<?>, EntityType<GreenRuneTemplarEntity>> GREEN_RUNE_TEMPLAR = registerMob("green_rune_templar", GreenRuneTemplarEntity::new, 1.125f, 2f, 6187108, 1645596);
	//public static final DeferredHolder<EntityType<?>, EntityType<GreenRunicLifeformEntity>> GREEN_RUNIC_LIFEFORM = registerMob("green_runic_lifeform", GreenRunicLifeformEntity::new, 0.75f, 0.99f, 13485, 41051);
	//public static final DeferredHolder<EntityType<?>, EntityType<GyroEntity>> GYRO = registerMob("gyro", GyroEntity::new, 1.375f, 1.625f, 16713479, 2895148);
	//public static final DeferredHolder<EntityType<?>, EntityType<HarkosEntity>> HARKOS = registerMob("harkos", HarkosEntity::new, 0.7f, 2.375f, 5198157, 5312786);
	//public static final DeferredHolder<EntityType<?>, EntityType<HiveKingEntity>> HIVE_KING = registerMob("hive_king", HiveKingEntity::new, 1.2f, 1.5f, 13211587, 15340050);
	//public static final DeferredHolder<EntityType<?>, EntityType<HiveWorkerEntity>> HIVE_WORKER = registerMob("hive_worker", HiveWorkerEntity::new, 1.0f, 1.8f, 13211587, 3675965);
	//public static final DeferredHolder<EntityType<?>, EntityType<KajarosEntity>> KAJAROS = registerMob("kajaros", KajarosEntity::new, 0.7f, 2.375f, 3351588, 9189689);
	//public static final DeferredHolder<EntityType<?>, EntityType<KingShroomusEntity>> KING_SHROOMUS = registerMob("king_shroomus", KingShroomusEntity::new, 0.875f, 3.25f, 12368043, 10167758);
	//public static final DeferredHolder<EntityType<?>, EntityType<KlobberEntity>> KLOBBER = registerMob("klobber", KlobberEntity::new, 0.8f, 2f, 3158044, 14211163);
	//public static final DeferredHolder<EntityType<?>, EntityType<KrorEntity>> KROR = registerMob("kror", KrorEntity::new, 1.75f, 3.75f, 8488834, 13620176);
	//public static final DeferredHolder<EntityType<?>, EntityType<MechbotEntity>> MECHBOT = registerMob("mechbot", MechbotEntity::new, 0.9f, 2.4375f, 13882126, 1644815);
	//public static final DeferredHolder<EntityType<?>, EntityType<MirageEntity>> MIRAGE = registerMob("mirage", MirageEntity::new, 0.8f, 2f, 13552553, 1447439);
	//public static final DeferredHolder<EntityType<?>, EntityType<MiskelEntity>> MISKEL = registerMob("miskel", MiskelEntity::new, 0.7f, 2.375f, 10197910, 7021604);
	//public static final DeferredHolder<EntityType<?>, EntityType<NightReaperEntity>> NIGHT_REAPER = registerMob("night_reaper", NightReaperEntity::new, 0.6875f, 2f, 0x2C3137, 0xE7E0D2);
	//public static final DeferredHolder<EntityType<?>, EntityType<NightflyEntity>> NIGHTFLY = registerMob("nightfly", NightflyEntity::new, 0.8125f, 0.8125f, 0x251E2C, 0x64534F);
	//public static final DeferredHolder<EntityType<?>, EntityType<OkazorEntity>> OKAZOR = registerMob("okazor", OkazorEntity::new, 0.7f, 2.375f, 5195591, 4982535);
	//public static final DeferredHolder<EntityType<?>, EntityType<OrangeFlowerEntity>> ORANGE_FLOWER = registerMob("orange_flower", OrangeFlowerEntity::new, 0.7f, 2.3125f, 821547, 12878353);
	//public static final DeferredHolder<EntityType<?>, EntityType<ProshieldEntity>> PROSHIELD = registerMob("proshield", ProshieldEntity::new, 0.8f, 2f, 14146468, 10660420);
	//public static final DeferredHolder<EntityType<?>, EntityType<PurpleFlowerEntity>> PURPLE_FLOWER = registerMob("purple_flower", PurpleFlowerEntity::new, 0.7f, 2.3125f, 2218771, 6757290);
	//public static final DeferredHolder<EntityType<?>, EntityType<RaxxanEntity>> RAXXAN = registerMob("raxxan", RaxxanEntity::new, 0.7f, 2.375f, 5195597, 7023706);
	//public static final DeferredHolder<EntityType<?>, EntityType<ReaverEntity>> REAVER = registerMob("reaver", ReaverEntity::new, 0.375f, 2.5f, 1443591, 10827318);
	//public static final DeferredHolder<EntityType<?>, EntityType<RedGuardianEntity>> RED_GUARDIAN = registerMob("red_guardian", RedGuardianEntity::new, 1.5f, 2.625f, 7829107, 9646642);
	//public static final DeferredHolder<EntityType<?>, EntityType<RedRuneTemplarEntity>> RED_RUNE_TEMPLAR = registerMob("red_rune_templar", RedRuneTemplarEntity::new, 1.125f, 2f, 5328462, 12654873);
	//public static final DeferredHolder<EntityType<?>, EntityType<RedRunicLifeformEntity>> RED_RUNIC_LIFEFORM = registerMob("red_runic_lifeform", RedRunicLifeformEntity::new, 0.75f, 0.99f, 730077, 1224933);
	//public static final DeferredHolder<EntityType<?>, EntityType<RockRiderEntity>> ROCK_RIDER = registerMob("rock_rider", RockRiderEntity::new, 1.3f, 3.375f, 6699285, 2765578);
	//public static final DeferredHolder<EntityType<?>, EntityType<SeaTrollEntity>> SEA_TROLL = registerMob("sea_troll", SeaTrollEntity::new, 0.6f, 1.8125f, 0x4F5293, 0xCEB9D9);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShadeEntity>> SHADE = registerMob("shade", ShadeEntity::new, 0.6f, 1.75f, 4333857, 13549760);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShadowEntity>> SHADOW = registerMob("shadow", ShadowEntity::new, 0.6f, 1.75f, 4333857, 13549760);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShadowlordEntity>> SHADOWLORD = registerMob("shadowlord", ShadowlordEntity::new, 4f, 6.75f, 460551, 10722203);
	//public static final DeferredHolder<EntityType<?>, EntityType<ShavoEntity>> SHAVO = registerMob("shavo", ShavoEntity::new, 0.6875f, 1.4375f, 15256780, 2959400);
	//public static final DeferredHolder<EntityType<?>, EntityType<SkeledonEntity>> SKELEDON = registerMob("skeledon", SkeledonEntity::new, 0.625f, 1.3125f, 14403243, 16249322);
	//public static final DeferredHolder<EntityType<?>, EntityType<SkelekyteEntity>> SKELEKYTE = registerMob("skelekyte", SkelekyteEntity::new, 0.5625f, 2f, 14403243, 13881807);
	//public static final DeferredHolder<EntityType<?>, EntityType<SkeletronEntity>> SKELETRON = registerMob("skeletron", SkeletronEntity::new, 1.2f, 1.25f, 16052195, 4802370);
	//public static final DeferredHolder<EntityType<?>, EntityType<TyrosaurEntity>> TYROSAUR = registerMob("tyrosaur", TyrosaurEntity::new, 0.8f, 1.3125f, 5530376, 13356729);
	//public static final DeferredHolder<EntityType<?>, EntityType<UriohEntity>> URIOH = registerMob("urioh", UriohEntity::new, 0.5f, 0.9375f, 2959920, 13946587);
	//public static final DeferredHolder<EntityType<?>, EntityType<UrvEntity>> URV = registerMob("urv", UrvEntity::new, 0.75f, 2f, 2960171, 8327184);
	//public static final DeferredHolder<EntityType<?>, EntityType<VinocorneEntity>> VINOCORNE = registerMob("vinocorne", VinocorneEntity::new, 1.2f, 3f, 4944456, 10838423);
	//public static final DeferredHolder<EntityType<?>, EntityType<VisageEntity>> VISAGE = registerMob("visage", VisageEntity::new, 0.7f, 1.5f, 4143934, 14709955);
	//public static final DeferredHolder<EntityType<?>, EntityType<VisualentEntity>> VISUALENT = registerMob("visualent", VisualentEntity::new, 1.4f, 1.5f, 5194571, 8590745);
	//public static final DeferredHolder<EntityType<?>, EntityType<VoxxulonEntity>> VOXXULON = registerMob("voxxulon", VoxxulonEntity::new, 2f, 2.375f, 2568993, 5954330);
	//public static final DeferredHolder<EntityType<?>, EntityType<XxeusEntity>> XXEUS = registerMob("xxeus", XxeusEntity::new, 1f, 3.125f, 9206543, 1432509);
	//public static final DeferredHolder<EntityType<?>, EntityType<YellowFlowerEntity>> YELLOW_FLOWER = registerMob("yellow_flower", YellowFlowerEntity::new, 0.7f, 2.3125f, 1168939, 14283790);
	//public static final DeferredHolder<EntityType<?>, EntityType<YellowGuardianEntity>> YELLOW_GUARDIAN = registerMob("yellow_guardian", YellowGuardianEntity::new, 1.5f, 2.625f, 7105891, 14347529);
	//public static final DeferredHolder<EntityType<?>, EntityType<YellowRuneTemplarEntity>> YELLOW_RUNE_TEMPLAR = registerMob("yellow_rune_templar", YellowRuneTemplarEntity::new, 1.125f, 2f, 2302754, 986892);
	//public static final DeferredHolder<EntityType<?>, EntityType<YellowRunicLifeformEntity>> YELLOW_RUNIC_LIFEFORM = registerMob("yellow_runic_lifeform", YellowRunicLifeformEntity::new, 0.75f, 0.99f, 1083877, 14152204);

	private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryName, EntityTypeRegistrar<T> builder) {
		final DeferredHolder<EntityType<?>, EntityType<T>> registryObject = AoARegistries.ENTITIES.register(registryName, () -> builder.build(registryName));

		if (builder.hasSpawnEgg())
			AoAItems.registerItem(registryName + "_spawn_egg", () -> new DeferredSpawnEggItem(registryObject, builder.getSpawnEggBackgroundColour(), builder.getSpawnEggDotsColour(), new Item.Properties()), CreativeModeTabs.SPAWN_EGGS);

		return registryObject;
	}
}
