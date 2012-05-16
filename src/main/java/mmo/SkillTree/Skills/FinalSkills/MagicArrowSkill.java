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
package mmo.SkillTree.Skills.FinalSkills;

import mmo.SkillTree.Events.AttackEvent;
import mmo.SkillTree.Skills.OnAttackSkill;
import mmo.SkillTree.Weapons.WeaponSet;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MagicArrowSkill extends OnAttackSkill {
	public MagicArrowSkill(Player player) {
		super(player);
	}

	@Override
	public void onAttack(AttackEvent event) {
		WeaponSet weaponSet = event.getWeapon();
		Player p = event.getPlayer();
		if (weaponSet.equals(WeaponSet.Bow) && p.equals(this.player)) {
			PlayerInventory inventory = p.getInventory();
			ItemStack[] allItems = inventory.getContents();
			boolean foundStack = false;
			for (ItemStack itemStack : allItems) {
				if (itemStack != null/*Are you serious, bukkit?*/ && itemStack.getType().equals(Material.ARROW) && foundStack == false) {
					if (itemStack.getAmount() != itemStack.getMaxStackSize()) {
						itemStack.setAmount(itemStack.getAmount() + 1);
						foundStack = true;
					}
				}
			}
			if (foundStack == false) {
				inventory.setItem(inventory.firstEmpty(), new ItemStack(Material.ARROW));
			}

			skillUseEvent();
		}
	}
}
