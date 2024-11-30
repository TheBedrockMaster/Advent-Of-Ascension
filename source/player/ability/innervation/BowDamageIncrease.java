package net.tslat.aoa3.player.ability.innervation;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

import java.util.List;

public class BowDamageIncrease extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			whenAttacking(serverOnly(this::handleOutgoingAttack)));

	private final boolean requireFullyCharged;
	private final float modifier;

	public BowDamageIncrease(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BOW_DAMAGE_INCREASE.get(), skill, data);

		this.requireFullyCharged = GsonHelper.getAsBoolean(data, "require_full_charge", true);
		this.modifier = GsonHelper.getAsFloat(data, "modifier");
	}

	public BowDamageIncrease(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BOW_DAMAGE_INCREASE.get(), skill, data);

		this.requireFullyCharged = data.getBoolean("require_full_charge");
		this.modifier = data.getFloat("modifier");
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + (requireFullyCharged ? ".fullCharge" : ""), NumberUtil.roundToNthDecimalPlace((modifier - 1) * 100, 2)));
	}

	private void handleOutgoingAttack(LivingIncomingDamageEvent ev) {
		DamageSource source = ev.getSource();

		if (source.is(DamageTypeTags.IS_PROJECTILE) && source.getDirectEntity() instanceof AbstractArrow arrow && (!this.requireFullyCharged || arrow.isCritArrow()))
			ev.setAmount(ev.getAmount() * modifier);
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putBoolean("require_full_charge", this.requireFullyCharged);
			data.putFloat("modifier", this.modifier);
		}

		return data;
	}
}
