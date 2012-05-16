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

import java.util.HashSet;

import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.Skill;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkillListener implements Listener {
	MMOSkillTree skillTreePlugin;

	public SkillListener(MMOSkillTree subPlugin) {
		skillTreePlugin = subPlugin;
	}

	@EventHandler
	public void onAttack(AttackEvent event) {
		HashSet<Skill> skills = skillTreePlugin.listeners.get(event.getEventName());
		if (skills != null) {
			for (Skill skill : skills) {
				skill.onAttack(event);
			}
		}
	}

	@EventHandler
	private void onSkillUse(SkillUseEvent event) {
		HashSet<Skill> skills = skillTreePlugin.listeners.get(event.getEventName());
		if (skills != null) {
			for (Skill skill : skills) {
				skill.onSkillUse(event);
			}
		}
	}
}