package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.aoa3.util.StringUtil;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class PotionDurationReducer extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(MobEffectEvent.Added.class, MobEffectEvent.Added::getEntity, serverOnly(this::handleAppliedMobEffect)));

	private final float modifier;
	private final boolean useAddition;

	private final MobEffectCategory matchType;
	private final HashSet<ResourceLocation> matchEffects;

	private final Consumer<MobEffectInstance> modifierConsumer;

	public PotionDurationReducer(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = GsonHelper.getAsFloat(data, "modifier");
		this.useAddition = GsonHelper.getAsBoolean(data, "use_addition", false);

		if (data.has("match_effect_type")) {
			matchType = MobEffectCategory.valueOf(data.get("match_effect_type").getAsString());
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<>();
			JsonElement effects = data.get("match_effects");

			if (effects.isJsonPrimitive()) {
				matchEffects.add(ResourceLocation.read(effects.getAsString()).getOrThrow());
			}
			else if (effects.isJsonArray()) {
				for (JsonElement element : effects.getAsJsonArray()) {
					matchEffects.add(ResourceLocation.read(element.getAsString()).getOrThrow());
				}
			}

			matchType = null;
		}

		this.modifierConsumer = effect -> {
			if (useAddition) {
				effect.duration += (int)modifier;
			}
			else {
				effect.duration *= modifier;
			}
		};
	}

	public PotionDurationReducer(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.POTION_DURATION_REDUCER.get(), skill, data);

		this.modifier = data.getFloat("modifier");
		this.useAddition = data.getBoolean("use_addition");

		if (data.contains("match_effect_type")) {
			matchType = MobEffectCategory.valueOf(data.getString("match_effect_type"));
			matchEffects = null;
		}
		else {
			matchEffects = new HashSet<>();
			Tag effects = data.get("match_effects");

			if (effects.getType() == StringTag.TYPE) {
				matchEffects.add(ResourceLocation.read(effects.getAsString()).getOrThrow());
			}
			else if (effects.getType() == ListTag.TYPE) {
				for (Tag element : ((ListTag)effects)) {
					matchEffects.add(ResourceLocation.read(element.getAsString()).getOrThrow());
				}
			}

			matchType = null;
		}

		this.modifierConsumer = null;
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		String operationSuffix = useAddition ? ".addition" : ".multiply";
		String value = useAddition ? NumberUtil.roundToNthDecimalPlace(modifier, 2) : NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2);

		if (matchType != null) {
			super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + operationSuffix + ".type", StringUtil.toTitleCase(matchType.toString()), value));
		}
		else {
			super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + operationSuffix + ".list", value));
		}
	}

	private void handleAppliedMobEffect(final MobEffectEvent.Added ev) {
		MobEffectInstance effect = ev.getEffectInstance();

		if (!effectMatches(effect))
			return;

		reduceEffectDuration(effect, this.modifierConsumer);
	}

	private boolean effectMatches(MobEffectInstance effect) {
		if (matchType != null)
			return effect.getEffect().value().getCategory() == matchType;

		return matchEffects.contains(effect.getEffect().unwrapKey().map(ResourceKey::location).orElseGet(() -> RegistryUtil.getId(effect.getEffect().value())));
	}

	private void reduceEffectDuration(MobEffectInstance effect, Consumer<MobEffectInstance> modifier) {
		modifier.accept(effect);

		if (effect.hiddenEffect != null)
			reduceEffectDuration(effect.hiddenEffect, modifierConsumer);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("modifier", this.modifier);
			data.putBoolean("use_addition", this.useAddition);

			if (this.matchType != null) {
				data.putString("match_effect_type", this.matchType.toString());
			}
			else {
				ListTag effects = new ListTag();

				for (ResourceLocation id : this.matchEffects) {
					effects.add(StringTag.valueOf(id.toString()));
				}

				data.put("match_effects", effects);
			}
		}

		return data;
	}
}
