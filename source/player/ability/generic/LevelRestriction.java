package net.tslat.aoa3.player.ability.generic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LevelRestriction extends AoAAbility.Instance {
	private final ResourceLocation restrictedId;

	public LevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = generateRestrictionHandlers(data);
	}

	public LevelRestriction(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = ResourceLocation.read(data.getString("restriction_id")).getOrThrow();
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return Collections.emptyList();
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		Map<String, List<ObjectIntPair<ResourceLocation>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(this.restrictedId);

		if (restrictions.isEmpty()) {
			super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), "??"));

			return;
		}

		Component targetName;

		if (isForBlock(restrictions)) {
			targetName = AoARegistries.BLOCKS.getEntry(restrictedId).getName();
		}
		else {
			Item item = AoARegistries.ITEMS.getEntry(restrictedId);
			targetName = item.getName(item.getDefaultInstance());
		}

		MutableComponent description = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), targetName);
		boolean comma = false;

		for (Map.Entry<String, List<ObjectIntPair<ResourceLocation>>> restriction : restrictions.entrySet()) {
			if (comma)
				description.append(", ");

			description.append(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + "." + restriction.getKey()));

			comma = true;
		}

		super.updateDescription(description);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("restriction_id", this.restrictedId.toString());

		return data;
	}

	protected ResourceLocation generateRestrictionHandlers(JsonObject json) {
		ResourceLocation restrictedId = ResourceLocation.read(GsonHelper.getAsString(json, "restricted_id")).getOrThrow();
		JsonArray reqs = GsonHelper.getAsJsonArray(json, "restrictions");

		if (reqs.size() == 0)
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		Map<String, List<ObjectIntPair<ResourceLocation>>> reqData = new Object2ObjectOpenHashMap<>();

		for (JsonElement entry : reqs) {
			reqData.put(entry.getAsJsonPrimitive().getAsString(), Collections.singletonList(ObjectIntPair.of(AoARegistries.AOA_SKILLS.getKey(skill.type()), getLevelReq())));
		}

		AoASkillReqReloadListener.addRequirements(restrictedId, reqData);

		return restrictedId;
	}

	private boolean isForBlock(Map<String, List<ObjectIntPair<ResourceLocation>>> restrictions) {
		for (String type : restrictions.keySet()) {
			switch (type) {
				case "break_block":
				case "place_block":
				case "interact_with":
					return true;
				default:
					break;
			}
		}

		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
