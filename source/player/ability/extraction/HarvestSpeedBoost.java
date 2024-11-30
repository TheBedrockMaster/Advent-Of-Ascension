package net.tslat.aoa3.player.ability.extraction;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.client.player.AoAPlayerKeybindListener;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;

import java.util.List;
import java.util.function.Consumer;

public class HarvestSpeedBoost extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerEvent.BreakSpeed.class, serverOnly(this::handleHarvestSpeedCheck)),
			listener(PlayerTickEvent.Pre.class, serverOnly(this::handlePlayerTick)));

	private final float energyDrainPerTick;
	private final float costReductionPerLevel;
	private final float speedBoostMod;

	private boolean active = false;

	public HarvestSpeedBoost(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.HARVEST_SPEED_BOOST.get(), skill, data);

		this.energyDrainPerTick = GsonHelper.getAsFloat(data, "energy_drain_per_tick");
		this.costReductionPerLevel = GsonHelper.getAsFloat(data, "cost_reduction_per_level", 0);
		this.speedBoostMod = GsonHelper.getAsFloat(data, "speed_boost", 2);
	}

	public HarvestSpeedBoost(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.HARVEST_SPEED_BOOST.get(), skill, data);

		this.energyDrainPerTick = data.getFloat("energy_drain_per_tick");
		this.costReductionPerLevel = data.getFloat("cost_reduction_per_level");
		this.speedBoostMod = data.getFloat("speed_boost");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + (costReductionPerLevel != 0 ? ".scaling" : ""),
				NumberUtil.roundToNthDecimalPlace(this.energyDrainPerTick * 20, 2),
				NumberUtil.roundToNthDecimalPlace(this.costReductionPerLevel * 20, 2)));
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("energy_drain_per_tick", this.energyDrainPerTick);
			data.putFloat("cost_reduction_per_level", this.costReductionPerLevel);
			data.putFloat("speed_boost", this.speedBoostMod);
		}

		data.putBoolean("active", this.active);

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		super.receiveSyncData(data);

		this.active = data.getBoolean("active");
	}

	private void handleHarvestSpeedCheck(PlayerEvent.BreakSpeed ev) {
		if (active)
			ev.setNewSpeed(ev.getNewSpeed() * speedBoostMod);
	}

	@Override
	public void createKeybindListener(Consumer<AoAPlayerKeybindListener> consumer) {
		consumer.accept(new AoAPlayerKeybindListener() {
			@Override
			public AoAPlayerEventListener getEventListener() {
				return HarvestSpeedBoost.this;
			}

			@Override
			public int getKeycode() {
				return AoAKeybinds.ABILITY_ACTION.getKey().getValue();
			}

			@Override
			public boolean shouldSendKeyPress() {
				if (getPlayer().getAbilities().instabuild)
					return false;

				return Minecraft.getInstance().gameMode.isDestroying() || HarvestSpeedBoost.this.active;
			}
		});
	}

	@Override
	public void handleKeyInput() {
		if (getPlayer() instanceof ServerPlayer player) {
			if (active) {
				active = false;
			}
			else {
				if (player.isCreative() || !player.gameMode.isDestroyingBlock)
					return;

				active = true;
			}

			activatedActionKey(player);
			markForClientSync();
		}
	}

	private void handlePlayerTick(final PlayerTickEvent.Pre ev) {
		if (!active)
			return;

		AoAResource.Instance energy = skill.getPlayerDataManager().getResource(AoAResources.ENERGY.get());
		Player player = getPlayer();

		if (!player.getAbilities().instabuild && !player.isSpectator() && energy.consume(energy.getPerTickRegen() + energyDrainPerTick - costReductionPerLevel * skill.getLevel(true), true)) {
			if (player.level().getGameTime() % 10 == 0)
				activatedActionKey((ServerPlayer)player);
		}
		else {
			active = false;

			markForClientSync();
		}
	}
}
