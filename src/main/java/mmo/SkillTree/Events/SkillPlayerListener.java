/*
 * This file is part of mmoSkillTree <http://github.com/mmoMinecraftDev/mmoSkillTree>.
 *
 * mmoSkillTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mmo.SkillTree.Events;

import mmo.SkillTree.Weapons.WeaponSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SkillPlayerListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player p = event.getPlayer();
		ItemStack wielding = p.getItemInHand();
		WeaponSet weaponSet = itemSet(wielding);
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (weaponSet.equals(WeaponSet.Bow)) {
				AttackEvent attackEvent = new AttackEvent(p, weaponSet);
				Bukkit.getServer().getPluginManager().callEvent(attackEvent);
			}
		} else if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
			if (!weaponSet.equals(WeaponSet.Bow)) {
				AttackEvent attackEvent = new AttackEvent(p, weaponSet);
				Bukkit.getServer().getPluginManager().callEvent(attackEvent);
			}
		} else if (action.equals(Action.PHYSICAL)) {
			p.sendMessage("Physical. Wtf is this. using: " + wielding);
		}
	}

	/*
	 * TODO: Extend item set to return this. :( Need to know what weaponSet an item is all over.
	 */
	private WeaponSet itemSet(ItemStack wielding) {
		int id = wielding.getTypeId();
		if (id == 278/*diamond*/ || id == 257/*iron*/ || id == 285/*gold*/ || id == 274/*stone*/ || id == 270/*wood*/) {
			return WeaponSet.Pickaxe;
		} else if (id == 277/*diamond*/ || id == 256/*iron*/ || id == 285/*gold*/ || id == 273/*stone*/ || id == 269/*wood*/) {
			return WeaponSet.Shovel;
		} else if (id == 279/*diamond*/ || id == 258/*iron*/ || id == 286/*gold*/ || id == 275/*stone*/ || id == 271/*wood*/) {
			return WeaponSet.Axe;
		} else if (id == 290 || id == 291 || id == 292 || id == 293 || id == 294) {
			return WeaponSet.Hoe;
		} else if (id == 268/*wood*/ || id == 272/*stone*/ || id == 267/*iron*/ || id == 283/*gold*/ || id == 276/*diamond*/) {
			return WeaponSet.Sword;
		} else if (id == 261) {
			return WeaponSet.Bow;
		} else {
			return WeaponSet.Unarmed; //This is actually going to make anything but the weapons count as tools, pretty much. So you can hit with blocks to gain tool or unarmed combat xp
		}
	}
}