package net.tslat.aoa3.event.custom.events;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;

/**
 * PlayerLevelChangeEvent is fired when a player levels up from any method.<br>
 * Level changes also applies to commands and other third party methods.<br>
 * This event is only fired on the logical server side.<br>
 * <br>
 * This event is fired via on the {@link NeoForge#EVENT_BUS} event bus.<br>
 * <br>
 * {@link #getOldLevel()} will return the player's original level prior to levelling up.<br>
 * {@link #getSkill()} will return the relevant {@link AoASkill.Instance} skill that the level up is for.<br>
 * {@link #wasNaturalLevelChange()} will return true if the level change is from naturally levelling up, or false if changed via a command or third-party.<br>
 * <br>
 * This event is not {@link net.neoforged.bus.api.ICancellableEvent Cancellable}
 */
public class PlayerLevelChangeEvent extends PlayerEvent {
	private final int oldLevel;
	private final AoASkill.Instance skill;
	private final boolean wasNaturallyChanged;

	public PlayerLevelChangeEvent(ServerPlayerDataManager playerDataManager, AoASkill.Instance skill, int oldLevel, boolean wasNaturallyChanged) {
		super(playerDataManager.getPlayer());

		this.oldLevel = oldLevel;
		this.skill = skill;
		this.wasNaturallyChanged = wasNaturallyChanged;
	}

	public int getOldLevel() {
		return oldLevel;
	}

	public int getNewLevel() {
		return skill.getLevel(true);
	}

	public AoASkill.Instance getSkill() {
		return skill;
	}

	public boolean wasNaturalLevelChange() {
		return wasNaturallyChanged;
	}
}
