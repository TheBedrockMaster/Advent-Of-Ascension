package net.tslat.aoa3.data.server;

import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AoASkillReqReloadListener extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String folder = "player/skill_reqs";

	private static final HashMap<ResourceLocation, SkillReqHandler> REQUIREMENTS_MAP = new HashMap<>();
	private static Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> requirementsData = new HashMap<>();

	public AoASkillReqReloadListener() {
		super(GSON, folder);
	}

	public static boolean canEquip(PlayerDataManager plData, Item item, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(RegistryUtil.getId(item));

		if (handler != null && !handler.canEquip(plData)) {
			if (notifyPlayer && plData.getPlayer() instanceof ServerPlayer pl)
				handler.notifyPlayerCantEquip(pl);

			return false;
		}

		return true;
	}

	public static boolean canPlaceBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(RegistryUtil.getId(block));

		if (handler != null && !handler.canPlaceBlock(plData)) {
			if (notifyPlayer && plData.getPlayer() instanceof ServerPlayer pl)
				handler.notifyPlayerCantPlaceBlock(pl);

			return false;
		}

		return true;
	}

	public static boolean canBreakBlock(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(RegistryUtil.getId(block));

		if (handler != null && !handler.canBreakBlock(plData)) {
			if (notifyPlayer && plData.getPlayer() instanceof ServerPlayer pl)
				handler.notifyPlayerCantBreakBlock(pl);

			return false;
		}

		return true;
	}

	public static boolean canInteractWith(PlayerDataManager plData, Block block, boolean notifyPlayer) {
		SkillReqHandler handler = getRequirements(RegistryUtil.getId(block));

		if (handler != null && !handler.canInteractWith(plData)) {
			if (notifyPlayer && plData.getPlayer() instanceof ServerPlayer pl)
				handler.notifyPlayerCantInteract(pl);

			return false;
		}

		return true;
	}

	@Nullable
	public static SkillReqHandler getRequirements(ResourceLocation id) {
		return REQUIREMENTS_MAP.get(id);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
		REQUIREMENTS_MAP.clear();

		parseAll(prepData(jsonMap));
	}

	public static void parseAll(Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> restrictions) {
		requirementsData = new Object2ObjectOpenHashMap<>(restrictions);

		for (Map.Entry<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> entry : requirementsData.entrySet()) {
			SkillReqHandler handler = parse(entry.getValue());

			if (handler.isValid()) {
				REQUIREMENTS_MAP.put(entry.getKey(), handler);
			}
			else {
				REQUIREMENTS_MAP.remove(entry.getKey());
			}
		}
	}

	public static SkillReqHandler parse(Map<String, List<ObjectIntPair<ResourceLocation>>> reqData) {
		SkillReqHandler handler = new SkillReqHandler();

		if (reqData.containsKey("equip"))
			handler.forEquipping(parseRequirements(reqData.get("equip")));

		if (reqData.containsKey("place_block"))
			handler.forPlacingBlocks(parseRequirements(reqData.get("place_block")));

		if (reqData.containsKey("break_block"))
			handler.forBreakingBlocks(parseRequirements(reqData.get("break_block")));

		if (reqData.containsKey("interact_with"))
			handler.forInteracting(parseRequirements(reqData.get("interact_with")));

		return handler;
	}

	private static Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> parseRequirements(List<ObjectIntPair<ResourceLocation>> reqs) {
		Predicate<PlayerDataManager> predicate = plData -> true;
		Consumer<ServerPlayer> notificationHandler = plData -> {};

		for (ObjectIntPair<ResourceLocation> pair : reqs) {
			AoASkill skill = AoASkills.getSkill(pair.first());
			int level = pair.valueInt();

			if (level <= 0 || level > 1000)
				throw new IllegalArgumentException("Invalid skill level requirement: " + level + ", must be 1-1000");

			if (skill == null)
				throw new IllegalArgumentException("Unknown skill: '" + pair.first() + "' for item skill entry.");

			predicate = predicate.and(plData -> plData.getSkill(skill).hasLevel(level));
			notificationHandler = notificationHandler.andThen(player -> PlayerUtil.notifyPlayerOfInsufficientLevel(player, skill, level));
		}

		return Pair.of(predicate, notificationHandler);
	}

	private Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> prepData(Map<ResourceLocation, JsonElement> jsonData) {
		HashMap<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> idToReqsMap = new HashMap<>(jsonData.size());

		for (Map.Entry<ResourceLocation, JsonElement> jsonFile : jsonData.entrySet()) {
			List<ResourceLocation> targetIds;
			JsonObject json = jsonFile.getValue().getAsJsonObject();
			JsonElement target = json.get("target");

			if (target.isJsonPrimitive()) {
				targetIds = List.of(ResourceLocation.read(target.getAsString()).getOrThrow());
			}
			else if (target.isJsonArray()) {
				JsonArray array = target.getAsJsonArray();
				targetIds = new ArrayList<>();

				for (JsonElement element : array) {
					targetIds.add(ResourceLocation.read(element.getAsString()).getOrThrow());
				}
			}
			else {
				throw new IllegalArgumentException("Unknown entry type for 'target' in AoA Skill Req json: " + jsonFile.getKey());
			}

			Map<String, List<ObjectIntPair<ResourceLocation>>> reqMap = new HashMap<>();

			for (Map.Entry<String, JsonElement> reqEntry : json.entrySet()) {
				if (reqEntry.getKey().equals("target"))
					continue;

				List<ObjectIntPair<ResourceLocation>> reqList = new ArrayList<>();
				JsonElement element = reqEntry.getValue();

				if (element.isJsonObject()) {
					JsonObject reqEntryObj = element.getAsJsonObject();

					reqList.add(ObjectIntPair.of(ResourceLocation.read(GsonHelper.getAsString(reqEntryObj, "skill")).getOrThrow(), GsonHelper.getAsInt(reqEntryObj, "level")));
				}
				else if (element.isJsonArray() && !element.getAsJsonArray().isEmpty()) {
					for (JsonElement ele2 : element.getAsJsonArray()) {
						JsonObject reqEntryObj = ele2.getAsJsonObject();

						reqList.add(ObjectIntPair.of(ResourceLocation.read(GsonHelper.getAsString(reqEntryObj, "skill")).getOrThrow(), GsonHelper.getAsInt(reqEntryObj, "level")));
					}
				}

				reqMap.put(reqEntry.getKey(), reqList);
			}

			for (ResourceLocation id : targetIds) {
				mergeOrAddEntry(id, reqMap, idToReqsMap);
			}
		}

		return idToReqsMap;
	}

	private static void mergeOrAddEntry(ResourceLocation id, Map<String, List<ObjectIntPair<ResourceLocation>>> newReqs, Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> reqMap) {
		Map<String, List<ObjectIntPair<ResourceLocation>>> existingReqsMap = reqMap.putIfAbsent(id, newReqs);

		if (existingReqsMap == null)
			return;

		for (Map.Entry<String, List<ObjectIntPair<ResourceLocation>>> newReqEntry : newReqs.entrySet()) {
			String reqType = newReqEntry.getKey();
			List<ObjectIntPair<ResourceLocation>> newReqsList = newReqEntry.getValue();
			List<ObjectIntPair<ResourceLocation>> existingReqsList = existingReqsMap.putIfAbsent(reqType, newReqsList);

			if (existingReqsList != null) {
				for (ObjectIntPair<ResourceLocation> newReq : newReqsList) {
					int index = -1;

					for (int i = 0; i < existingReqsList.size(); i++) {
						ObjectIntPair<ResourceLocation> oldReq = existingReqsList.get(i);

						if (oldReq.first().equals(newReq.first())) {
							if (oldReq.valueInt() < newReq.valueInt())
								index = i;

							break;
						}
					}

					if (index >= 0)
						existingReqsList.set(index, newReq);
				}
			}
		}
	}

	public static void setRequirements(ResourceLocation id, SkillReqHandler handler) {
		REQUIREMENTS_MAP.put(id, handler);
	}

	public static void addRequirements(ResourceLocation id, Map<String, List<ObjectIntPair<ResourceLocation>>> data) {
		mergeOrAddEntry(id, data, requirementsData);
		setRequirements(id, parse(getParsedReqDataFor(id)));
	}

	public static Map<ResourceLocation, Map<String, List<ObjectIntPair<ResourceLocation>>>> getParsedReqData() {
		return requirementsData;
	}

	@NotNull
	public static Map<String, List<ObjectIntPair<ResourceLocation>>> getParsedReqDataFor(ResourceLocation itemId) {
		if (!requirementsData.containsKey(itemId))
			return new HashMap<>(0);

		return requirementsData.get(itemId);
	}

	public static class SkillReqHandler {
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> equipPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockPlacePredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockBreakPredicate;
		@Nullable
		private Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> interactionPredicate;

		private void forEquipping(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> equipPredicate) {
			this.equipPredicate = equipPredicate;
		}

		private void forPlacingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockPlacePredicate) {
			this.blockPlacePredicate = blockPlacePredicate;
		}

		private void forBreakingBlocks(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> blockBreakPredicate) {
			this.blockBreakPredicate = blockBreakPredicate;
		}

		private void forInteracting(Pair<Predicate<PlayerDataManager>, Consumer<ServerPlayer>> interactionPredicate) {
			this.interactionPredicate = interactionPredicate;
		}

		public boolean handlingEquip() {
			return equipPredicate != null;
		}

		public boolean handlingBlockPlacement() {
			return blockPlacePredicate != null;
		}

		public boolean handlingBlockBreak() {
			return blockBreakPredicate != null;
		}

		public boolean handlingInteraction() {
			return interactionPredicate != null;
		}

		public boolean canEquip(PlayerDataManager plData) {
			return !handlingEquip() || equipPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantEquip(ServerPlayer player) {
			if (handlingEquip())
				equipPredicate.getSecond().accept(player);
		}

		public boolean canPlaceBlock(PlayerDataManager plData) {
			return !handlingBlockPlacement() || blockPlacePredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantPlaceBlock(ServerPlayer player) {
			if (handlingBlockPlacement())
				blockPlacePredicate.getSecond().accept(player);
		}

		public boolean canBreakBlock(PlayerDataManager plData) {
			return !handlingBlockBreak() || blockBreakPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantBreakBlock(ServerPlayer player) {
			if (handlingBlockBreak())
				blockBreakPredicate.getSecond().accept(player);
		}

		public boolean canInteractWith(PlayerDataManager plData) {
			return !handlingInteraction() || interactionPredicate.getFirst().test(plData);
		}

		public void notifyPlayerCantInteract(ServerPlayer player) {
			if (handlingInteraction())
				interactionPredicate.getSecond().accept(player);
		}

		public boolean isValid() {
			return handlingEquip() || handlingBlockPlacement() || handlingBlockBreak() || handlingInteraction();
		}
	}
}
