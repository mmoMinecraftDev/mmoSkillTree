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

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import mmo.SkillTree.GUI.MMOXpBar;
import mmo.SkillTree.GUI.SkillBar;
import mmo.SkillTree.Skills.Skill;
import mmo.SkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;

import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillsPlayer {
	private int mana = 0;
	private Player player;
	private MMOXpBar xpBar;
	private SkillBar skillBar;
	private Map<SkillSet, Set> sets = new EnumMap<SkillSet, Set>(SkillSet.class);
	private ArrayList skills = new ArrayList();
	private boolean hasSpout = false;

	public SkillsPlayer(MMOSkillTree plugin, Player player) {
		System.out.println("MMOPlayer constructor");
		this.player = player;
		xpBar = new MMOXpBar(plugin, player);
		skillBar = new SkillBar(plugin, player);
		//sets = new EnumMap<SkillSet, Set>(SkillSet.class);
		//skills = new ArrayList();
		hasSpout = ((SpoutPlayer) player).isSpoutCraftEnabled();
	}

	public Set getSet(SkillSet skillSet) {
		Set set = sets.get(skillSet);
		if (set != null) {
			return set;
		}
		// If it gets to this stage then it's null, so create it.
		addSet(skillSet);
		set = sets.get(skillSet);
		return set;
	}

	public void addSet(SkillSet skillSet) {
		sets.put(skillSet, new Set(skillSet, this));
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
	}

	public void removeSkill(Skill skill) {
		skills.remove(skill);
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getMana() {
		return mana;
	}

	public void increaseMana(int amount) {
		mana = mana + amount;
	}

	public void decreaseMana(int amount) {
		mana = mana - amount;
		if (mana < 0) {
			mana = 0;
		}
	}

	public void addXp(int xpAmount, SkillSet skillSet) {
		Set set = getSet(skillSet);
		set.addXp(xpAmount);

		float percent = ((float) set.getCurLvlXp() / set.getNextLvlXp());
		xpBar.draw(percent, skillSet);
	}

	public void setSkill(int pos, Skill skill) {
		skillBar.setSkill(pos, skill);
	}

	public void activateSkill(int pos) {
		Skill skillAtPos = skillBar.getSkill(pos);
		skillAtPos.activate();
	}

	public boolean hasSpout() {
		return hasSpout;
	}
}