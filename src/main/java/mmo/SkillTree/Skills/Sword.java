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

import java.util.HashMap;

public class Sword {
	public HashMap<String, TreeSkill> map = new HashMap<String, TreeSkill>();

	public Sword() {
		TreeSkill blank3 = new TreeSkill().setName("Blank3").setType(SkillType.PASSIVE).setRow(4).setCol(3);
		TreeSkill hilt_bash = new TreeSkill().setName("Hilt Bash").setType(SkillType.ACTIVE).setRow(4).setCol(0);
		TreeSkill blank1 = new TreeSkill().setName("Blank1").setType(SkillType.PASSIVE).setRow(3).setCol(3).addChild("Blank3");
		TreeSkill triforce = new TreeSkill().setName("Triforce").setType(SkillType.PASSIVE).setRow(3).setCol(2);
		TreeSkill spirit_of_fire = new TreeSkill().setName("Spirit of Storm").setType(SkillType.PASSIVE).setRow(3).setCol(1).addChild("Triforce");
		TreeSkill spirit_of_storm = new TreeSkill().setName("Spirit of Storm").setType(SkillType.PASSIVE).setRow(2).setCol(2).addChild("Triforce");
		TreeSkill spirit_of_ice = new TreeSkill().setName("Spirit of Ice").setType(SkillType.PASSIVE).setRow(2).setCol(1).addChild("Triforce");
		TreeSkill fencing = new TreeSkill().setName("Fencing").setType(SkillType.PASSIVE).setRow(1).setCol(3).addChild("Blank1");
		TreeSkill rend = new TreeSkill().setName("Rend").setType(SkillType.PASSIVE).setRow(1).setCol(1);
		TreeSkill flame_slash = new TreeSkill().setName("Flame Slash").setType(SkillType.ACTIVE).setRow(0).setCol(1);
		TreeSkill impale = new TreeSkill().setName("Impale").setType(SkillType.ACTIVE).setRow(0).setCol(0).addChild("Rend").addChild("Hilt Bash");
		TreeSkill basic_swordsmanship = new TreeSkill().setName("Basic Swordsmanship").setType(SkillType.PASSIVE).setRow(0).setCol(3);
		map.put("Impale", impale);
		map.put("Flame Slash", flame_slash);
		map.put("Basic Swordsmanship", basic_swordsmanship);
		map.put("Rend", rend);
		map.put("Fencing", fencing);
		map.put("Spirit of Ice", spirit_of_ice);
		map.put("Spirit of Storm", spirit_of_storm);
		map.put("Spirit of Fire", spirit_of_fire);
		map.put("Triforce", triforce);
		map.put("Blank1", blank1);
		map.put("Hilt Bash", hilt_bash);
		map.put("Blank3", blank3);
	}

	public TreeSkill getSkill(String skillName) {
		return map.get(skillName);
	}
}