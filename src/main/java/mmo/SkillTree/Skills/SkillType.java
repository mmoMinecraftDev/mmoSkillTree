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

import java.util.ArrayList;
import java.util.List;

public enum SkillType {
	BLANK,
	ACTIVE,
	PASSIVE;
	static final List<String> list = new ArrayList<String>();

	public static boolean contains(String name) {
		return list.contains(name);
	}

	public static SkillType getFromString(String name) {
		if (name != null) {
			for (SkillType t : values()) {
				if (t.name().equals(name)) {
					return t;
				}
			}
		}

		return null;
	}

	static {
		for (SkillType t : values()) {
			list.add(t.name());
		}
	}
}