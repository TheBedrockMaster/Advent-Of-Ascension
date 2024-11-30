package net.tslat.aoa3.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.tslat.aoa3.library.constant.BossDropsScheme;

public final class ServerConfig {
	public final ModConfigSpec.IntValue portalSearchRadius;
	public final ModConfigSpec.BooleanValue easyCorruptedTravellers;
	public final ModConfigSpec.BooleanValue allowNonPlayerPortalTravel;
	public final ModConfigSpec.DoubleValue globalXpModifier;
	public final ModConfigSpec.BooleanValue saveLootFromExplosions;
	public final ModConfigSpec.BooleanValue disableSkills;
	public final ModConfigSpec.EnumValue<BossDropsScheme> bossDropsScheme;

	public final ModConfigSpec.BooleanValue skillsLeaderboardEnabled;
	public final ModConfigSpec.IntValue maxLeaderboardThreads;
	public final ModConfigSpec.BooleanValue dontCacheDatabase;
	public final ModConfigSpec.ConfigValue<String> databaseUsername;
	public final ModConfigSpec.ConfigValue<String> databasePassword;

	public ServerConfig(ModConfigSpec.Builder configBuilder) {
		configBuilder.comment("AoA server-side configuration options").push("general_settings");

		portalSearchRadius = configBuilder
				.comment("Adjust this value to modify how far to look for safe/existing portal locations.",
						"The lower the value, the faster it runs")
				.translation("config.aoa3.server.portalSearchRadius")
				.defineInRange("portalSearchRadius", 24, 1, 128);

		easyCorruptedTravellers = configBuilder
				.comment("Set this to true to make Corrupted Travellers easier to find, causing them to glow through blocks when nearby.")
				.translation("config.aoa3.server.easyCorruptedTravellers")
				.define("easyCorruptedTravellers", false);

		allowNonPlayerPortalTravel = configBuilder
				.comment("Set to false to stop non-player entities from using the AoA portal system.")
				.translation("config.aoa3.server.allowNonPlayerPortalTravel")
				.define("allowNonPlayerPortalTravel", true);

		globalXpModifier = configBuilder
				.comment("Modifier for global xp gain for all players for AoA skills.",
						"Higher numbers means more xp gained")
				.translation("config.aoa3.server.globalXpModifier")
				.defineInRange("globalXpModifier", 1d, 0d, 1000d);

		saveLootFromExplosions = configBuilder
				.comment("Set to false to stop AoA saving loot-drops from explosions.")
				.translation("config.aoa3.server.saveLootFromExplosions")
				.define("saveLootFromExplosions", true);

		disableSkills = configBuilder
				.comment("Set to false to disable all AoA Skills for the server.", "This may give a slight performance improvement.", "NOTE: This will cause any player who logs in to lose any levels & xp they may have previously stored. Only turn this on if you intend for the server to run without skills.")
				.translation("config.aoa3.server.disableSkills")
				.define("disableSkills", false);

		bossDropsScheme = configBuilder
				.comment("Select the method in which Nowhere boss loot should be distributed on kill.")
				.translation("config.aoa3.server.bossDropsScheme")
				.defineEnum("bossDropsScheme", BossDropsScheme.SPLIT_BETWEEN_PLAYERS);

		configBuilder.pop();
		configBuilder.comment("AoA Leaderboard configuration options").push("leaderboard_settings");

		skillsLeaderboardEnabled = configBuilder
				.comment("Set to false to disable the skills leaderboard entirely.", "NOTE: Disabling the leaderboard will prevent it from updating its data, and game data changes while the leaderboard is disabled will not be tracked if re-enabled.")
				.translation("config.aoa3.server.skillsLeaderboardEnabled")
				.define("skillsLeaderboardEnabled", false);

		maxLeaderboardThreads = configBuilder
				.comment("The amount of threads & connections to the skills database AoA will try to make. Less threads may produce a negligible memory usage improvement, and more threads may improve database performance on larger servers.", "You shouldn't need to change this unless you know what you're doing.")
				.translation("config.aoa3.server.maxLeaderboardThreads")
				.defineInRange("maxLeaderboardThreads", 4, 1, 100);

		dontCacheDatabase = configBuilder
				.comment("Set this to false to disable in-memory databases for leaderboards. This can save on RAM usage, but may reduce performance of the leaderboard's functionality and increase disk usage.")
				.translation("config.aoa3.server.dontCacheDatabase")
				.define("dontCacheDatabase", false);

		databaseUsername = configBuilder
				.comment("The username to use for leaderboard database connection. You shouldn't need to change this.")
				.translation("config.aoa3.server.databaseUsername")
				.define("databaseUsername", "User");

		databasePassword = configBuilder
				.comment("The password to use along with the databaseUsername for leaderboard database connection. You shouldn't need to change this.", "NOTE: If setting your own password, be aware this will be stored in easily accessible plaintext. Use a throwaway password.")
				.translation("config.aoa3.server.databasePassword")
				.define("databasePassword", "Password");

		configBuilder.pop();
	}
}
