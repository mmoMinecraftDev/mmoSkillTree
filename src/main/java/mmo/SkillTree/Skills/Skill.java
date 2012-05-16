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
package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.AttackEvent;
import mmo.SkillTree.Events.SkillUseEvent;
import mmo.SkillTree.MMOSkillTree;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Skill {
	static public MMOSkillTree plugin;
	protected Player player;
	protected String name;
	protected String desc;
	protected int lvl = 0;
	protected int healthCost = 0;
	protected int hungerCost = 0;
	protected int manaCost = 0;
	protected int cooldown = 0;

	public Skill(Player player) {
		this.player = player;
		addListener("SKILL_USE");
	}

	public void onAttack(AttackEvent event) {
	}

	public void onSkillUse(SkillUseEvent event) {
	}

	public void addListener(String eventName) {
		plugin.addSkillListener(eventName, this);
	}

	public void removeListener(String eventName) {
		plugin.removeSkillListener(eventName, this);
	}

	public void activate() {
		player.sendMessage("No skill set.");
	}

	/*
		 * Calls the skill use event for this skill.
		 */
	public void skillUseEvent() {
		SkillUseEvent skillUseEvent = new SkillUseEvent(player);
		Bukkit.getServer().getPluginManager().callEvent(skillUseEvent);
	}
}