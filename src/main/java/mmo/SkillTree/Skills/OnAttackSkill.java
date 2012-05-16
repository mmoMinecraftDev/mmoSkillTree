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

import mmo.SkillTree.Events.SkillUseEvent;

import org.bukkit.entity.Player;

public class OnAttackSkill extends Skill {
	protected int uses = 3;
	protected int usesLeft = 0;

	public OnAttackSkill(Player player) {
		super(player);
	}

	@Override
	public void activate() {
		addListener("ATTACK");
		usesLeft = uses;
	}

	@Override
	public void onSkillUse(SkillUseEvent event) {
		usesLeft = usesLeft - 1;
		event.getPlayer().sendMessage(usesLeft + " skill uses left.");
		if (usesLeft == 0) {
			removeListener("ATTACK");
		}
	}
}