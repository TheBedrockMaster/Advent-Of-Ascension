package net.tslat.aoa3.player.ability.dexterity;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.client.player.AoAPlayerKeybindListener;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.function.Consumer;

public class DoubleJump extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(LivingFallEvent.class, LivingFallEvent::getEntity, serverOnly(this::handlePlayerFall)));
	private final float energyConsumption;

	private boolean canJump = true;

	public DoubleJump(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DOUBLE_JUMP.get(), skill, data);

		this.energyConsumption = Math.max(0, GsonHelper.getAsFloat(data, "energy_consumption"));
	}

	public DoubleJump(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DOUBLE_JUMP.get(), skill, data);

		this.energyConsumption = data.getFloat("energy_consumption");
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), NumberUtil.roundToNthDecimalPlace(this.energyConsumption, 2)));
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	@Override
	public void createKeybindListener(Consumer<AoAPlayerKeybindListener> consumer) {
		consumer.accept(new AoAPlayerKeybindListener() {
			@Override
			public AoAPlayerEventListener getEventListener() {
				return DoubleJump.this;
			}

			@Override
			public int getKeycode() {
				return Minecraft.getInstance().options.keyJump.getKey().getValue();
			}

			@Override
			public boolean shouldSendKeyPress() {
				Player player = getPlayer();

				if (player.onGround() || player.jumpTriggerTime > 0)
					return false;

				if (player.getItemBySlot(EquipmentSlot.CHEST).canElytraFly(player))
					return false;

				if (!player.getAbilities().mayfly)
					player.jumpTriggerTime = 7;

				return true;
			}
		});
	}

	@Override
	public void handleKeyInput() {
		if (getPlayer() instanceof ServerPlayer player) {
			if (canJump || player.isCreative()) {
				if (player.onGround())
					return;

				if (consumeResource(AoAResources.ENERGY.get(), energyConsumption, true)) {
					canJump = false;

					player.jumpFromGround();
					// TODO look at whether this is needed now that keybinds are both sides
					AoANetworking.sendToPlayer((ServerPlayer)player, new UpdateClientMovementPacket(UpdateClientMovementPacket.Operation.SET, player.getDeltaMovement().y()));

					if (getSkill().canGainXp(true))
						PlayerUtil.giveTimeBasedXpToPlayer((ServerPlayer)player, getSkill().type(), 16, false);
				}
			}
		}
	}

	private void handlePlayerFall(LivingFallEvent ev) {
		if (!canJump)
			ev.setDistance(ev.getDistance() - ev.getEntity().getJumpPower() * 10f);

		canJump = true;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag syncData = super.getSyncData(forClientSetup);

		if (forClientSetup)
			syncData.putFloat("energy_consumption", energyConsumption);

		return syncData;
	}
}
