package net.tslat.aoa3.player.resource;

import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.AoAPlayerEventListener;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class AoAResource {
	private final Supplier<MutableComponent> name;
	private final BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory;
	private final Function<CompoundTag, Instance> clientFactory;

	public AoAResource(BiFunction<ServerPlayerDataManager, JsonObject, Instance> jsonFactory, Function<CompoundTag, Instance> clientFactory) {
		this.name = Suppliers.memoize(() -> Component.translatable(Util.makeDescriptionId("resource", AoARegistries.AOA_RESOURCES.getKey(this))));
		this.jsonFactory = jsonFactory;
		this.clientFactory = clientFactory;
	}

	public MutableComponent getName() {
		return this.name.get();
	}

	public Instance buildDefaultInstance(ServerPlayerDataManager plData, JsonObject resourceData) {
		return jsonFactory.apply(plData, resourceData);
	}

	public Instance buildClientInstance(CompoundTag resourceData) {
		return clientFactory.apply(resourceData);
	}

	public static abstract class Instance implements AoAPlayerEventListener {
		private final AoAResource resource;

		protected ServerPlayerDataManager playerDataManager;
		public boolean needsSync = true;

		protected Instance(AoAResource resource, ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
			this.resource = resource;
		}

		@Override
		public Player getPlayer() {
			return this.playerDataManager.getPlayer();
		}

		public void changePlayerInstance(ServerPlayerDataManager plData) {
			this.playerDataManager = plData;
		}

		public abstract float getCurrentValue();
		public abstract void setValue(float amount);
		public abstract float getMaxValue();

		public float getPerTickRegen() {
			return 0;
		}

		public AoAResource type() {
			return this.resource;
		}

		public MutableComponent getName() {
			return type().getName();
		}

		public ServerPlayerDataManager getPlayerDataManager() {
			return this.playerDataManager;
		}

		@Override
		public boolean isStillValid() {
			return this.playerDataManager != null && this.playerDataManager.isStillValid();
		}

		public boolean hasAmount(float amount) {
			return getCurrentValue() >= amount;
		}

		public void addValue(float amount) {
			setValue(getCurrentValue() + amount);
		}

		public boolean consume(float amount, boolean consumeIfInsufficient) {
			if (!PlayerUtil.shouldPlayerBeAffected(playerDataManager.getPlayer()))
				return true;

			float current = getCurrentValue();

			if (current < amount && !consumeIfInsufficient) {
				PlayerUtil.notifyPlayerOfInsufficientResources(playerDataManager.getPlayer(), type(), amount);

				return false;
			}

			setValue(Math.max(0, current - amount));

			needsSync = true;

			return !consumeIfInsufficient || current >= amount;
		}

		@NotNull
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}

		public void loadFromNbt(CompoundTag resourceDataNbt) {}

		public CompoundTag getSyncData(boolean forClientSetup) {
			return new CompoundTag();
		}

		public void receiveSyncData(CompoundTag data) {}
	}
}
