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
package mmo.SkillTree;

import mmo.SkillTree.Skills.SkillSet;

import org.bukkit.ChatColor;

public class Set {
	public int xp = 0;
	public int lvl = 0;
	public int skillPoints = 0;
	public int spentSkillPoints = 0;
	public String string;
	@SuppressWarnings("unused")
	private SkillSet skillSet;
	private SkillsPlayer mmoPlayer;

	Set(SkillSet skillSet, SkillsPlayer mmoPlayer) {
		this.skillSet = skillSet;
		this.string = skillSet.toString();
		this.mmoPlayer = mmoPlayer;
	}

	public void addXp(int xpAmount) {
		this.xp = this.xp + xpAmount;

		if (getCurLvlXp() > getNextLvlXp()) {
			this.lvl = this.lvl + 1;
			this.skillPoints = this.skillPoints + 1;
			if (!mmoPlayer.hasSpout()) {
				mmoPlayer.getPlayer().sendMessage(ChatColor.GOLD + "[Warning] " + ChatColor.WHITE + "You've gained a level, but your client does not allow gui features to assign skills!\n"
						+ "Get Spoutcraft at http://spout.in/exe");
			}
		}
	}

	public int calcXp(int lvl) {
		return 100 * lvl * lvl / 2;
	}

	public int getNextLvlXp() {
		return calcXp(this.lvl + 1);
	}

	public int getCurLvlXp() {
		return this.xp - calcXp(this.lvl);
	}
}