package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class OneShotDamageLimiter extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(LivingDamageEvent.Pre.class, LivingDamageEvent.Pre::getEntity, serverOnly(this::handlePreDamageApplication)));

	private final float restoreHealthTo;
	private final float minActivationHealth;

	public OneShotDamageLimiter(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ONE_SHOT_DAMAGE_LIMITER.get(), skill, data);

		this.restoreHealthTo = GsonHelper.getAsFloat(data, "health_restore_amount", 1);
		this.minActivationHealth = GsonHelper.getAsFloat(data, "min_activation_health", 0);
	}

	public OneShotDamageLimiter(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ONE_SHOT_DAMAGE_LIMITER.get(), skill, data);

		this.restoreHealthTo = data.getFloat("health_restore_amount");
		this.minActivationHealth = data.getFloat("min_activation_health");
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		if (this.minActivationHealth <= 0) {
			super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".full", NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
		else {
			super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), NumberUtil.roundToNthDecimalPlace(this.minActivationHealth, 2), NumberUtil.roundToNthDecimalPlace(this.restoreHealthTo, 2)));
		}
	}

	private void handlePreDamageApplication(final LivingDamageEvent.Pre ev) {
		if (ev.getContainer().getSource().is(Tags.DamageTypes.IS_TECHNICAL))
			return;

		LivingEntity player = ev.getEntity();

		if (player instanceof ServerPlayer pl && player.getHealth() - ev.getContainer().getNewDamage() <= 0 && player.getHealth() >= (minActivationHealth == 0 ? player.getMaxHealth() : minActivationHealth)) {
			ev.getContainer().setNewDamage(0);
			player.setHealth(restoreHealthTo);
			skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get()).setValue(0);

			if (skill.canGainXp(true))
				PlayerUtil.giveTimeBasedXpToPlayer(pl, this.skill.type(), 500, false);
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("health_restore_amount", this.restoreHealthTo);
			data.putFloat("min_activation_health", this.minActivationHealth);
		}

		return data;
	}
}
