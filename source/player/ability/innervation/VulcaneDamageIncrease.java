package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.NumberUtil;

import java.util.List;

public class VulcaneDamageIncrease extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			whenAttacking(serverOnly(this::handleOutgoingAttack)));

	private final float minRage;
	private final float modifier;

	public VulcaneDamageIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.VULCANE_DAMAGE_INCREASE.get(), skill, data);

		this.minRage = GsonHelper.getAsFloat(data, "min_rage", 0);
		this.modifier = GsonHelper.getAsFloat(data, "modifier");
	}

	public VulcaneDamageIncrease(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.VULCANE_DAMAGE_INCREASE.get(), skill, data);

		this.minRage = data.getFloat("min_rage");
		this.modifier = data.getFloat("modifier");
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + (minRage > 0 ? ".minRage" : ""), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2), NumberUtil.roundToNthDecimalPlace(minRage, 2)));
	}

	private void handleOutgoingAttack(final LivingIncomingDamageEvent ev) {
		if (DamageUtil.isVulcaneDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * this.modifier);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("min_rage", this.minRage);
			data.putFloat("modifier", this.modifier);
		}

		return data;
	}
}
