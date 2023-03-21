package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.content.entity.projectile.arrow.PopShotEntity;
import net.tslat.aoa3.content.entity.projectile.blaster.*;
import net.tslat.aoa3.content.entity.projectile.cannon.*;
import net.tslat.aoa3.content.entity.projectile.gun.*;
import net.tslat.aoa3.content.entity.projectile.misc.*;
import net.tslat.aoa3.content.entity.projectile.mob.*;
import net.tslat.aoa3.content.entity.projectile.staff.*;
import net.tslat.aoa3.content.entity.projectile.thrown.*;

public final class AoAProjectiles {
	public static void init() {}

	public static final RegistryObject<EntityType<AnemiaBombEntity>> ANEMIA_BOMB = registerProjectile("anemia_bomb", AnemiaBombEntity::new);
	public static final RegistryObject<EntityType<CustomArrowEntity>> ARROW = registerProjectile("arrow", CustomArrowEntity::new);
	public static final RegistryObject<EntityType<AquaballEntity>> AQUABALL = registerProjectile("aquaball", AquaballEntity::new);
	public static final RegistryObject<EntityType<AquaticShotEntity>> AQUATIC_SHOT = registerProjectile("aquatic_shot", AquaticShotEntity::new);
	public static final RegistryObject<EntityType<ArcwormShotEntity>> ARCWORM_SHOT = registerProjectile("arcworm_shot", ArcwormShotEntity::new);
	public static final RegistryObject<EntityType<AtomizerBounceEntity>> ATOMIZER_BOUNCE = registerProjectile("atomizer_bounce", AtomizerBounceEntity::new);
	public static final RegistryObject<EntityType<AtomizerShotEntity>> ATOMIZER_SHOT = registerProjectile("atomizer_shot", AtomizerShotEntity::new);
	public static final RegistryObject<EntityType<BalloonBombEntity>> BALLOON_BOMB = registerProjectile("balloon_bomb", BalloonBombEntity::new);
	public static final RegistryObject<EntityType<BaronShotEntity>> BARON_SHOT = registerProjectile("baron_shot", BaronShotEntity::new);
	public static final RegistryObject<EntityType<BaronessShotEntity>> BARONESS_SHOT = registerProjectile("baroness_shot", BaronessShotEntity::new);
	public static final RegistryObject<EntityType<BeamerShotEntity>> BEAMER_SHOT = registerProjectile("beamer_shot", BeamerShotEntity::new);
	public static final RegistryObject<EntityType<BloodDrainerEntity>> BLOOD_DRAINER = registerProjectile("blood_drainer", BloodDrainerEntity::new);
	public static final RegistryObject<EntityType<BloodballEntity>> BLOODBALL = registerProjectile("bloodball", BloodballEntity::new);
	public static final RegistryObject<EntityType<BlueBulletEntity>> BLUE_BULLET = registerProjectile("blue_bullet", BlueBulletEntity::new);
	public static final RegistryObject<EntityType<BlueGuardianShotEntity>> BLUE_GUARDIAN_SHOT = registerProjectile("blue_guardian_shot", BlueGuardianShotEntity::new);
	public static final RegistryObject<EntityType<BombCarrierDynamiteEntity>> BOMB_CARRIER_DYNAMITE = registerProjectile("bomb_carrier_dynamite", BombCarrierDynamiteEntity::new, 0.375f, 0.1875f);
	public static final RegistryObject<EntityType<BoneBulletEntity>> BONE_BULLET = registerProjectile("bone_bullet", BoneBulletEntity::new);
	public static final RegistryObject<EntityType<BonePelletEntity>> BONE_PELLET = registerProjectile("bone_pellet", BonePelletEntity::new);
	public static final RegistryObject<EntityType<BozoBallEntity>> BOZO_BALL = registerProjectile("bozo_ball", BozoBallEntity::new);
	public static final RegistryObject<EntityType<BubbleShotEntity>> BUBBLE_SHOT = registerProjectile("bubble_shot", BubbleShotEntity::new);
	public static final RegistryObject<EntityType<BulletShotEntity>> BULLET_SHOT = registerProjectile("bullet_shot", BulletShotEntity::new);
	public static final RegistryObject<EntityType<CannonballEntity>> CANNONBALL = registerProjectile("cannonball", CannonballEntity::new);
	public static final RegistryObject<EntityType<CarrotBallEntity>> CARROT_BALL = registerProjectile("carrot_ball", CarrotBallEntity::new);
	public static final RegistryObject<EntityType<CelestialFallEntity>> CELESTIAL_FALL = registerProjectile("celestial_fall", CelestialFallEntity::new);
	public static final RegistryObject<EntityType<ChakramEntity>> CHAKRAM = registerProjectile("chakram", ChakramEntity::new);
	public static final RegistryObject<EntityType<CherryShotEntity>> CHERRY_SHOT = registerProjectile("cherry_shot", CherryShotEntity::new);
	public static final RegistryObject<EntityType<ChilliShotEntity>> CHILLI_SHOT = registerProjectile("chilli_shot", ChilliShotEntity::new);
	public static final RegistryObject<EntityType<ClownBallEntity>> CLOWN_BALL = registerProjectile("clown_ball", ClownBallEntity::new);
	public static final RegistryObject<EntityType<LimoniteBulletEntity>> BULLET = registerProjectile("bullet", LimoniteBulletEntity::new);
	public static final RegistryObject<EntityType<ClownShotEntity>> CLOWN_SHOT = registerProjectile("clown_shot", ClownShotEntity::new);
	public static final RegistryObject<EntityType<ConfettiClusterEntity>> CONFETTI_CLUSTER = registerProjectile("confetti_cluster", ConfettiClusterEntity::new);
	public static final RegistryObject<EntityType<ConfettiShotEntity>> CONFETTI_SHOT = registerProjectile("confetti_shot", ConfettiShotEntity::new);
	public static final RegistryObject<EntityType<ConstructShotEntity>> CONSTRUCT_SHOT = registerProjectile("construct_shot", ConstructShotEntity::new);
	//public static final RegistryObject<EntityType<CorallusShotEntity>> CORALLUS_SHOT = registerProjectile("corallus_shot", CorallusShotEntity::new, 1.1f, 1.1f);
	public static final RegistryObject<EntityType<CottonCandorShotEntity>> COTTON_CANDOR_SHOT = registerProjectile("cotton_candor_shot", CottonCandorShotEntity::new);
	public static final RegistryObject<EntityType<CraexxeusNukeEntity>> CRAEXXEUS_NUKE = registerProjectile("craexxeus_nuke", CraexxeusNukeEntity::new);
	public static final RegistryObject<EntityType<CraexxeusShotEntity>> CRAEXXEUS_SHOT = registerProjectile("craexxeus_shot", CraexxeusShotEntity::new);
	public static final RegistryObject<EntityType<CreeperShotEntity>> CREEPER_SHOT = registerProjectile("creeper_shot", CreeperShotEntity::new);
	public static final RegistryObject<EntityType<CreepBombEntity>> CREEP_BOMB = registerProjectile("creep_bomb", CreepBombEntity::new);
	public static final RegistryObject<EntityType<CreepTubeEntity>> CREEP_TUBE = registerProjectile("creep_tube", CreepTubeEntity::new);
	public static final RegistryObject<EntityType<CyanShotEntity>> CYAN_SHOT = registerProjectile("cyan_shot", CyanShotEntity::new);
	public static final RegistryObject<EntityType<DeathRayEntity>> DEATH_RAY = registerProjectile("death_ray", DeathRayEntity::new);
	public static final RegistryObject<EntityType<DestroyerShotEntity>> DESTROYER_SHOT = registerProjectile("destroyer_shot", DestroyerShotEntity::new);
	public static final RegistryObject<EntityType<DestructionShotEntity>> DESTRUCTION_SHOT = registerProjectile("destruction_shot", DestructionShotEntity::new);
	public static final RegistryObject<EntityType<DischargeShotEntity>> DISCHARGE_SHOT = registerProjectile("discharge_shot", DischargeShotEntity::new);
	public static final RegistryObject<EntityType<DischargeSlugEntity>> DISCHARGE_SLUG = registerProjectile("discharge_slug", DischargeSlugEntity::new);
	public static final RegistryObject<EntityType<DoomShotEntity>> DOOM_SHOT = registerProjectile("doom_shot", DoomShotEntity::new);
	public static final RegistryObject<EntityType<EnergyShotEntity>> ENERGY_SHOT = registerProjectile("energy_shot", EnergyShotEntity::new);
	public static final RegistryObject<EntityType<EradicatorShotEntity>> ERADICATOR_SHOT = registerProjectile("eradicator_shot", EradicatorShotEntity::new);
	public static final RegistryObject<EntityType<ErebonSticklerShotEntity>> EREBON_STICKLER_SHOT = registerProjectile("erebon_stickler_shot", ErebonSticklerShotEntity::new);
	public static final RegistryObject<EntityType<ErebonSticklerStuckEntity>> EREBON_STICKLER_STUCK = registerProjectile("erebon_stickler_stuck", ErebonSticklerStuckEntity::new);
	public static final RegistryObject<EntityType<FireflyShotEntity>> FIREFLY_SHOT = registerProjectile("firefly_shot", FireflyShotEntity::new);
	public static final RegistryObject<EntityType<FirestormFallEntity>> FIRESTORM_FALL = registerProjectile("firestorm_fall", FirestormFallEntity::new);
	public static final RegistryObject<EntityType<FireBulletEntity>> FIRE_BULLET = registerProjectile("fire_bullet", FireBulletEntity::new);
	public static final RegistryObject<EntityType<FloroRPGEntity>> FLORO_RPG = registerProjectile("floro_rpg", FloroRPGEntity::new);
	public static final RegistryObject<EntityType<FlowerBallEntity>> FLOWER_BALL = registerProjectile("flower_ball", FlowerBallEntity::new);
	public static final RegistryObject<EntityType<FlowerShotEntity>> FLOWER_SHOT = registerProjectile("flower_shot", FlowerShotEntity::new);
	public static final RegistryObject<EntityType<FragmentShotEntity>> FRAGMENT_SHOT = registerProjectile("fragment_shot", FragmentShotEntity::new);
	public static final RegistryObject<EntityType<FungalBallEntity>> FUNGAL_BALL = registerProjectile("fungal_ball", FungalBallEntity::new);
	public static final RegistryObject<EntityType<FungalRockFragmentEntity>> FUNGAL_ROCK_FRAGMENT = registerProjectile("fungal_rock_fragment", FungalRockFragmentEntity::new);
	public static final RegistryObject<EntityType<GhoulBallEntity>> GHOUL_BALL = registerProjectile("ghoul_ball", GhoulBallEntity::new);
	public static final RegistryObject<EntityType<GhoulShotEntity>> GHOUL_SHOT = registerProjectile("ghoul_shot", GhoulShotEntity::new);
	public static final RegistryObject<EntityType<GigaGreenBallEntity>> GIGA_GREEN_BALL = registerProjectile("giga_green_ball", GigaGreenBallEntity::new);
	public static final RegistryObject<EntityType<GoldenCannonballEntity>> GOLDEN_CANNONBALL = registerProjectile("golden_cannonball", GoldenCannonballEntity::new);
	public static final RegistryObject<EntityType<GoldShotEntity>> GOLD_SHOT = registerProjectile("gold_shot", GoldShotEntity::new);
	public static final RegistryObject<EntityType<GooBallEntity>> GOO_BALL = registerProjectile("goo_ball", GooBallEntity::new);
	public static final RegistryObject<EntityType<GrawShotEntity>> GRAW_SHOT = registerProjectile("graw_shot", GrawShotEntity::new);
	public static final RegistryObject<EntityType<GreenBulletEntity>> GREEN_BULLET = registerProjectile("green_bullet", GreenBulletEntity::new);
	public static final RegistryObject<EntityType<GreenGuardianShotEntity>> GREEN_GUARDIAN_SHOT = registerProjectile("green_guardian_shot", GreenGuardianShotEntity::new);
	public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE = registerProjectile("grenade", GrenadeEntity::new);
	public static final RegistryObject<EntityType<HagShotEntity>> HAG_SHOT = registerProjectile("hag_shot", HagShotEntity::new);
	public static final RegistryObject<EntityType<HardenedParapiranhaEntity>> HARDENED_PARAPIRANHA = registerProjectile("hardened_parapiranha", HardenedParapiranhaEntity::new);
	public static final RegistryObject<EntityType<HaunterShotEntity>> HAUNTER_SHOT = registerProjectile("haunter_shot", HaunterShotEntity::new);
	public static final RegistryObject<EntityType<HeavyBlueCannonballEntity>> HEAVY_BLUE_CANNONBALL = registerProjectile("heavy_blue_cannonball", HeavyBlueCannonballEntity::new);
	public static final RegistryObject<EntityType<HeavyBoneCannonballEntity>> HEAVY_BONE_CANNONBALL = registerProjectile("heavy_bone_cannonball", HeavyBoneCannonballEntity::new);
	public static final RegistryObject<EntityType<HeavyCannonballEntity>> HEAVY_CANNONBALL = registerProjectile("heavy_cannonball", HeavyCannonballEntity::new);
	public static final RegistryObject<EntityType<HeavyGrenadeEntity>> HEAVY_GRENADE = registerProjectile("heavy_grenade", HeavyGrenadeEntity::new);
	public static final RegistryObject<EntityType<HeavyRedBulletEntity>> HEAVY_RED_BULLET = registerProjectile("heavy_red_bullet", HeavyRedBulletEntity::new);
	public static final RegistryObject<EntityType<HeavyRedCannonballEntity>> HEAVY_RED_CANNONBALL = registerProjectile("heavy_red_cannonball", HeavyRedCannonballEntity::new);
	public static final RegistryObject<EntityType<HeavyRunicGuardianShotEntity>> HEAVY_RUNIC_GUARDIAN_SHOT = registerProjectile("heavy_runic_guardian_shot", HeavyRunicGuardianShotEntity::new);
	public static final RegistryObject<EntityType<HeavyShadowballEntity>> HEAVY_SHADOWBALL = registerProjectile("heavy_shadowball", HeavyShadowballEntity::new);
	public static final RegistryObject<EntityType<BaseEnergyShot>> HEAVY_SHOWER_SHOT = registerProjectile("heavy_shower_shot", HeavyShowerShotEntity::new);
	public static final RegistryObject<EntityType<HeavyTriDischargeShotEntity>> HEAVY_TRI_DISCHARGE_SHOT = registerProjectile("heavy_tri_discharge_shot", HeavyTriDischargeShotEntity::new);
	public static final RegistryObject<EntityType<HeavyWitherBallEntity>> HEAVY_WITHER_BALL = registerProjectile("heavy_wither_ball", HeavyWitherBallEntity::new);
	public static final RegistryObject<EntityType<HellfireEntity>> HELLFIRE = registerProjectile("hellfire", HellfireEntity::new);
	public static final RegistryObject<EntityType<HellfireProjectileEntity>> HELLFIRE_TAIL = registerProjectile("hellfire_tail", HellfireProjectileEntity::new);
	public static final RegistryObject<EntityType<HellBubbleShotEntity>> HELL_BUBBLE_SHOT = registerProjectile("hell_bubble", HellBubbleShotEntity::new);
	public static final RegistryObject<EntityType<HiveBallEntity>> HIVE_BALL = registerProjectile("hive_ball", HiveBallEntity::new);
	public static final RegistryObject<EntityType<HotShotEntity>> HOT_SHOT = registerProjectile("hot_shot", HotShotEntity::new);
	public static final RegistryObject<EntityType<IceShotEntity>> ICE_SHOT = registerProjectile("ice_shot", IceShotEntity::new);
	public static final RegistryObject<EntityType<IllusionShotEntity>> ILLUSION_SHOT = registerProjectile("illusion_shot", IllusionShotEntity::new);
	public static final RegistryObject<EntityType<IonShotEntity>> ION_SHOT = registerProjectile("ion_shot", IonShotEntity::new, 0.25f, 0.3f);
	public static final RegistryObject<EntityType<IroMinerShotEntity>> IRO_MINER_SHOT = registerProjectile("iro_miner_shot", IroMinerShotEntity::new);
	public static final RegistryObject<EntityType<LaserShotEntity>> LASER_SHOT = registerProjectile("laser_shot", LaserShotEntity::new);
	public static final RegistryObject<EntityType<LelyetianShotEntity>> LELYETIAN_SHOT = registerProjectile("lelyetian_shot", LelyetianShotEntity::new);
	public static final RegistryObject<EntityType<LightBlasterShotEntity>> LIGHT_BLASTER_SHOT = registerProjectile("light_blaster_shot", LightBlasterShotEntity::new);
	public static final RegistryObject<EntityType<LightIronShotEntity>> LIGHT_IRON_SHOT = registerProjectile("light_iron_shot", LightIronShotEntity::new);
	public static final RegistryObject<EntityType<LightRunicGuardianShotEntity>> LIGHT_RUNIC_GUARDIAN_SHOT = registerProjectile("light_runic_guardian_shot", LightRunicGuardianShotEntity::new);
	public static final RegistryObject<EntityType<LightSparkEntity>> LIGHT_SPARK = registerProjectile("light_spark", LightSparkEntity::new);
	public static final RegistryObject<EntityType<LunarFallEntity>> LUNAR_FALL = registerProjectile("lunar_fall", LunarFallEntity::new);
	public static final RegistryObject<EntityType<LunaShotEntity>> LUNA_SHOT = registerProjectile("luna_shot", LunaShotEntity::new);
	public static final RegistryObject<EntityType<LuxonSticklerShotEntity>> LUXON_STICKLER_SHOT = registerProjectile("luxon_stickler_shot", LuxonSticklerShotEntity::new);
	public static final RegistryObject<EntityType<LuxonSticklerStuckEntity>> LUXON_STICKLER_STUCK = registerProjectile("luxon_stickler_stuck", LuxonSticklerStuckEntity::new);
	public static final RegistryObject<EntityType<LyonicShotEntity>> LYONIC_SHOT = registerProjectile("lyonic_shot", LyonicShotEntity::new);
	public static final RegistryObject<EntityType<MagicBallEntity>> MAGIC_BALL = registerProjectile("magic_ball", MagicBallEntity::new);
	//public static final RegistryObject<EntityType<MechFallEntity>> MECH_FALL = registerProjectile("mech_fall", MechFallEntity::new);
	//public static final RegistryObject<EntityType<MechShotEntity>> MECH_SHOT = registerProjectile("mech_shot", MechShotEntity::new);
	public static final RegistryObject<EntityType<MetalSlugEntity>> METAL_SLUG = registerProjectile("metal_slug", MetalSlugEntity::new);
	public static final RegistryObject<EntityType<MeteorFallEntity>> METEOR_FALL = registerProjectile("meteor_fall", MeteorFallEntity::new);
	public static final RegistryObject<EntityType<MindBlasterShotEntity>> MIND_BLASTER_SHOT = registerProjectile("mind_blaster_shot", MindBlasterShotEntity::new);
	public static final RegistryObject<EntityType<MiniGreenBallEntity>> MINI_GREEN_BALL = registerProjectile("mini_green_ball", MiniGreenBallEntity::new);
	public static final RegistryObject<EntityType<ModuloShotEntity>> MODULO_SHOT = registerProjectile("modulo_shot", ModuloShotEntity::new);
	public static final RegistryObject<EntityType<MoonlightFallEntity>> MOONLIGHT_FALL = registerProjectile("moonlight_fall", MoonlightFallEntity::new);
	public static final RegistryObject<EntityType<MoonDestroyerShotEntity>> MOON_DESTROYER_SHOT = registerProjectile("moon_destroyer_shot", MoonDestroyerShotEntity::new);
	public static final RegistryObject<EntityType<MoonMakerEntity>> MOON_MAKER = registerProjectile("moon_maker", MoonMakerEntity::new);
	public static final RegistryObject<EntityType<MoonShinerEntity>> MOON_SHINER_SHOT = registerProjectile("moon_shiner_shot", MoonShinerEntity::new);
	public static final RegistryObject<EntityType<MoonShotEntity>> MOON_SHOT = registerProjectile("moon_shot", MoonShotEntity::new);
	public static final RegistryObject<EntityType<MultiplyingGrenadeEntity>> MULTIPLYING_GRENADE = registerProjectile("multiplying_grenade", MultiplyingGrenadeEntity::new);
	public static final RegistryObject<EntityType<NightmareFallEntity>> NIGHTMARE_FALL = registerProjectile("nightmare_fall", NightmareFallEntity::new);
	public static final RegistryObject<EntityType<NoxiousShotEntity>> NOXIOUS_SHOT = registerProjectile("noxious_shot", NoxiousShotEntity::new);
	public static final RegistryObject<EntityType<OdiousEntity>> ODIOUS_SHOT = registerProjectile("odious_shot", OdiousEntity::new);
	public static final RegistryObject<EntityType<OmnilightShotEntity>> OMNILIGHT_SHOT = registerProjectile("omnilight_shot", OmnilightShotEntity::new);
	public static final RegistryObject<EntityType<OrangeCannonballEntity>> ORANGE_CANNONBALL = registerProjectile("orange_cannonball", OrangeCannonballEntity::new);
	public static final RegistryObject<EntityType<OrbocronEntity>> ORBOCRON_SHOT = registerProjectile("orbocron_shot", OrbocronEntity::new);
	public static final RegistryObject<EntityType<ParalyzerShotEntity>> PARALYZER_SHOT = registerProjectile("paralyzer_shot", ParalyzerShotEntity::new);
	public static final RegistryObject<EntityType<PartyPopperEntity>> PARTY_POPPER_SHOT = registerProjectile("party_popper_shot", PartyPopperEntity::new);
	public static final RegistryObject<EntityType<PhantomShotEntity>> PHANTOM_SHOT = registerProjectile("phantom_shot", PhantomShotEntity::new);
	public static final RegistryObject<EntityType<PlutonSticklerShotEntity>> PLUTON_STICKLER_SHOT = registerProjectile("pluton_stickler_shot", PlutonSticklerShotEntity::new);
	public static final RegistryObject<EntityType<PlutonSticklerStuckEntity>> PLUTON_STICKLER_STUCK = registerProjectile("pluton_stickler_stuck", PlutonSticklerStuckEntity::new);
	public static final RegistryObject<EntityType<PoisonPlungerEntity>> POISON_PLUNGER_SHOT = registerProjectile("poison_plunger_shot", PoisonPlungerEntity::new);
	public static final RegistryObject<EntityType<PoisonShotEntity>> POISON_SHOT = registerProjectile("poison_shot", PoisonShotEntity::new);
	public static final RegistryObject<EntityType<PolymorphShotEntity>> POLYMORPH_SHOT = registerProjectile("polymorph_shot", PolymorphShotEntity::new);
	public static final RegistryObject<EntityType<PolytomShotEntity>> POLYTOM_SHOT = registerProjectile("polytom_shot", PolytomShotEntity::new);
	public static final RegistryObject<EntityType<PopShotEntity>> POP_SHOT = registerProjectile("pop_shot", PopShotEntity::new);
	public static final RegistryObject<EntityType<PowerRayEntity>> POWER_RAY = registerProjectile("power_ray", PowerRayEntity::new);
	public static final RegistryObject<EntityType<PowerShotEntity>> POWER_SHOT = registerProjectile("power_shot", PowerShotEntity::new);
	public static final RegistryObject<EntityType<PrimordialShotEntity>> PRIMORDIAL_SHOT = registerProjectile("primordial_shot", PrimordialShotEntity::new);
	public static final RegistryObject<EntityType<ProtonShotEntity>> PROTON_SHOT = registerProjectile("proton_shot", ProtonShotEntity::new);
	public static final RegistryObject<EntityType<RainbowShotEntity>> RAINBOW_SHOT = registerProjectile("rainbow_shot", RainbowShotEntity::new);
	public static final RegistryObject<EntityType<RedBulletEntity>> RED_BULLET = registerProjectile("red_bullet", RedBulletEntity::new);
	public static final RegistryObject<EntityType<RedGuardianShotEntity>> RED_GUARDIAN_SHOT = registerProjectile("red_guardian_shot", RedGuardianShotEntity::new);
	public static final RegistryObject<EntityType<ReeferShotEntity>> REEFER_SHOT = registerProjectile("reefer_shot", ReeferShotEntity::new);
	public static final RegistryObject<EntityType<RevolutionShotEntity>> REVOLUTION_SHOT = registerProjectile("revolution_shot", RevolutionShotEntity::new);
	public static final RegistryObject<EntityType<RockFragmentEntity>> ROCK_FRAGMENT = registerProjectile("rock_fragment", RockFragmentEntity::new);
	public static final RegistryObject<EntityType<RosidianShotEntity>> ROSIDIAN_SHOT = registerProjectile("rosidian_shot", RosidianShotEntity::new);
	public static final RegistryObject<EntityType<RPGEntity>> RPG = registerProjectile("rpg", RPGEntity::new);
	public static final RegistryObject<EntityType<RunicBombEntity>> RUNIC_BOMB = registerProjectile("runic_bomb", RunicBombEntity::new);
	public static final RegistryObject<EntityType<RunicGuardianShotEntity>> RUNIC_GUARDIAN_SHOT = registerProjectile("runic_guardian_shot", RunicGuardianShotEntity::new);
	public static final RegistryObject<EntityType<SeaocronEntity>> SEAOCRON_SHOT = registerProjectile("seaocron_shot", SeaocronEntity::new);
	public static final RegistryObject<EntityType<SeedDartEntity>> SEED_DART = registerProjectile("seed_dart", SeedDartEntity::new);
	public static final RegistryObject<EntityType<SelyanSticklerShotEntity>> SELYAN_STICKLER_SHOT = registerProjectile("selyan_stickler_shot", SelyanSticklerShotEntity::new);
	public static final RegistryObject<EntityType<SelyanSticklerStuckEntity>> SELYAN_STICKLER_STUCK = registerProjectile("selyan_stickler_stuck", SelyanSticklerStuckEntity::new);
	//public static final RegistryObject<EntityType<ShadowlordShotEntity>> SHADOWLORD_SHOT = registerProjectile("shadowlord_shot", ShadowlordShotEntity::new);
	public static final RegistryObject<EntityType<ShoeShotEntity>> SHOE_SHOT = registerProjectile("shoe_shot", ShoeShotEntity::new);
	public static final RegistryObject<EntityType<BaseEnergyShot>> SHOWER_SHOT = registerProjectile("shower_shot", ShowerShotEntity::new);
	public static final RegistryObject<EntityType<ShroomBulletEntity>> SHROOM_BULLET = registerProjectile("shroom_bullet", ShroomBulletEntity::new);
	public static final RegistryObject<EntityType<ShyreBeamEntity>> SHYRE_BEAM = registerProjectile("shyre_beam", ShyreBeamEntity::new);
	public static final RegistryObject<EntityType<ShyreShotEntity>> SHYRE_SHOT = registerProjectile("shyre_shot", ShyreShotEntity::new);
	public static final RegistryObject<EntityType<SkulloShotEntity>> SKULLO_SHOT = registerProjectile("skullo_shot", SkulloShotEntity::new);
	public static final RegistryObject<EntityType<SkyShotEntity>> SKY_SHOT = registerProjectile("sky_shot", SkyShotEntity::new);
	public static final RegistryObject<EntityType<SliceStarEntity>> SLICE_STAR = registerProjectile("slice_star", SliceStarEntity::new);
	public static final RegistryObject<EntityType<SmileyCannonballEntity>> SMILEY_CANNONBALL = registerProjectile("smiley_cannonball", SmileyCannonballEntity::new);
	public static final RegistryObject<EntityType<SmileBlasterEntity>> SMILE_BLASTER = registerProjectile("smile_blaster", SmileBlasterEntity::new);
	public static final RegistryObject<EntityType<SniperSlugEntity>> SNIPER_SLUG = registerProjectile("sniper_slug", SniperSlugEntity::new);
	public static final RegistryObject<EntityType<SoulDrainerShotEntity>> SOUL_DRAINER_SHOT = registerProjectile("soul_drainer_shot", SoulDrainerShotEntity::new);
	public static final RegistryObject<EntityType<SoulSparkEntity>> SOUL_SPARK = registerProjectile("soul_spark", SoulSparkEntity::new);
	public static final RegistryObject<EntityType<SoulStormEntity>> SOUL_STORM_SHOT = registerProjectile("soul_storm_shot", SoulStormEntity::new);
	public static final RegistryObject<EntityType<SpectralShotEntity>> SPECTRAL_SHOT = registerProjectile("spectral_shot", SpectralShotEntity::new);
	public static final RegistryObject<EntityType<SpiritualShotEntity>> SPIRITUAL_SHOT = registerProjectile("spiritual_shot", SpiritualShotEntity::new);
	public static final RegistryObject<EntityType<StickyCoolBombEntity>> STICKY_COOL_BOMB = registerProjectile("sticky_cool_bomb", StickyCoolBombEntity::new);
	public static final RegistryObject<EntityType<StickyRedBombEntity>> STICKY_RED_BOMB = registerProjectile("sticky_red_bomb", StickyRedBombEntity::new);
	public static final RegistryObject<EntityType<SunsetBulletEntity>> SUNSET_BULLET = registerProjectile("sunset_bullet", SunsetBulletEntity::new);
	public static final RegistryObject<EntityType<SunShotEntity>> SUN_SHOT = registerProjectile("sun_shot", SunShotEntity::new);
	public static final RegistryObject<EntityType<SuperGreenBallEntity>> SUPER_GREEN_BALL = registerProjectile("super_green_ball", SuperGreenBallEntity::new);
	public static final RegistryObject<EntityType<SwarmShotEntity>> SWARM_SHOT = registerProjectile("swarm_shot", SwarmShotEntity::new);
	public static final RegistryObject<EntityType<TangleFallEntity>> TANGLE_FALL = registerProjectile("tangle_fall", TangleFallEntity::new);
	public static final RegistryObject<EntityType<ConstructTerrorShotEntity>> TERROR_CONSTRUCT_SHOT = registerProjectile("terror_construct_shot", ConstructTerrorShotEntity::new);
	public static final RegistryObject<EntityType<TidalWaveEntity>> TIDAL_WAVE = registerProjectile("tidal_wave", TidalWaveEntity::new);
	public static final RegistryObject<EntityType<ToxicBulletEntity>> TOXIC_BULLET = registerProjectile("toxic_bullet", ToxicBulletEntity::new);
	public static final RegistryObject<EntityType<ToxicShotEntity>> TOXIC_SHOT = registerProjectile("toxic_shot", ToxicShotEntity::new);
	public static final RegistryObject<EntityType<TriDischargeShotEntity>> TRI_DISCHARGE_SHOT = registerProjectile("tri_discharge_shot", TriDischargeShotEntity::new);
	public static final RegistryObject<EntityType<UltimatumShotEntity>> ULTIMATUM_SHOT = registerProjectile("ultimatum_shot", UltimatumShotEntity::new);
	public static final RegistryObject<EntityType<UltraGreenBallEntity>> ULTRA_GREEN_BALL = registerProjectile("ultra_green_ball", UltraGreenBallEntity::new);
	public static final RegistryObject<EntityType<ValkyrieShotEntity>> VALKYRIE_SHOT = registerProjectile("valkyrie_shot", ValkyrieShotEntity::new);
	public static final RegistryObject<EntityType<VineWizardShotEntity>> VINE_WIZARD_SHOT = registerProjectile("vine_wizard_shot", VineWizardShotEntity::new);
	public static final RegistryObject<EntityType<VolatileCannonballEntity>> VOLATILE_CANNONBALL = registerProjectile("volatile_cannonball", VolatileCannonballEntity::new);
	public static final RegistryObject<EntityType<VortexBlastEntity>> VORTEX_BLAST = registerProjectile("vortex_blast", VortexBlastEntity::new);
	//public static final RegistryObject<EntityType<VoxxulonMeteorEntity>> VOXXULON_METEOR = registerProjectile("voxxulon_meteor", VoxxulonMeteorEntity::new);
	public static final RegistryObject<EntityType<VoxCannonEntity>> VOX_CANNON = registerProjectile("vox_cannon", VoxCannonEntity::new);
	public static final RegistryObject<EntityType<VulkramEntity>> VULKRAM = registerProjectile("vulkram", VulkramEntity::new);
	public static final RegistryObject<EntityType<WartDartEntity>> WART_DART = registerProjectile("wart_dart", WartDartEntity::new);
	public static final RegistryObject<EntityType<WaterBalloonBombEntity>> WATER_BALLOON_BOMB = registerProjectile("water_balloon_bomb", WaterBalloonBombEntity::new);
	public static final RegistryObject<EntityType<WaterShotEntity>> WATER_SHOT = registerProjectile("water_shot", WaterShotEntity::new);
	public static final RegistryObject<EntityType<BaseEnergyShot>> WEIGHTED_SHOWER_SHOT = registerProjectile("weighted_shower_shot", WeightedShowerShotEntity::new);
	public static final RegistryObject<EntityType<WhiteBallEntity>> WHITE_BALL = registerProjectile("white_ball", WhiteBallEntity::new);
	public static final RegistryObject<EntityType<WinderShotEntity>> WINDER_SHOT = registerProjectile("winder_shot", WinderShotEntity::new);
	public static final RegistryObject<EntityType<WitherBallEntity>> WITHER_BALL = registerProjectile("wither_ball", WitherBallEntity::new);
	public static final RegistryObject<EntityType<WitherShotEntity>> WITHER_SHOT = registerProjectile("wither_shot", WitherShotEntity::new);
	public static final RegistryObject<EntityType<WrathShotEntity>> WRATH_SHOT = registerProjectile("wrath_shot", WrathShotEntity::new);
	public static final RegistryObject<EntityType<YellowBulletEntity>> YELLOW_BULLET = registerProjectile("yellow_bullet", YellowBulletEntity::new);
	public static final RegistryObject<EntityType<YellowGuardianShotEntity>> YELLOW_GUARDIAN_SHOT = registerProjectile("yellow_guardian_shot", YellowGuardianShotEntity::new);

	public static final RegistryObject<EntityType<StoneGiantRockEntity>> STONE_GIANT_ROCK = registerProjectile("stone_giant_rock", StoneGiantRockEntity::new, 0.5f, 0.5f);
	public static final RegistryObject<EntityType<TreeSpiritSpriteEntity>> TREE_SPIRIT_SPRITE = registerProjectile("tree_spirit_sprite", TreeSpiritSpriteEntity::new, 0.375f, 0.375f, 1);
	public static final RegistryObject<EntityType<FireballEntity>> FIREBALL = registerProjectile("fireball", FireballEntity::new, 0.25f, 0.25f);

	private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory) {
		return registerProjectile(registryName, factory, 0.25f, 0.25f);
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory, float width, float height) {
		return registerProjectile(registryName, factory, width, height, 3);
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerProjectile(String registryName, EntityType.EntityFactory<T> factory, float width, float height, int updateInterval) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).clientTrackingRange(8).setTrackingRange(120).setUpdateInterval(updateInterval);

		return AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});
	}
}
