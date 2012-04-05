package mmo.SkillTree.Events;

import mmo.SkillTree.Weapons.WeaponSet;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AttackEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Player player;
	private WeaponSet weaponSet;

	public AttackEvent(Player p, WeaponSet weaponSet) {
		player = p;
		this.weaponSet = weaponSet;
	}

	public Player getPlayer() {
		return player;
	}

	public WeaponSet getWeapon() {
		return weaponSet;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean bln) {
		this.cancelled = bln;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}