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

public class TreeSkill {
	public String name;
	public int row;
	public int col;
	public SkillType type;
	public ArrayList<String> parents = new ArrayList<String>();
	public ArrayList<String> children = new ArrayList<String>();

	public TreeSkill() {

	}

	public void onEvent() {

	}

	public TreeSkill setName(String newName) {
		name = newName;
		return this;
	}

	public String getName() {
		return name;
	}

	public TreeSkill setRow(int newRow) {
		row = newRow;
		return this;
	}

	public int getRow() {
		return row;
	}

	public TreeSkill setCol(int newCol) {
		col = newCol;
		return this;
	}

	public int getCol() {
		return col;
	}

	public TreeSkill addParent(String newParent) {
		parents.add(newParent);
		return this;
	}

	public String[] getParents() {
		return (String[]) parents.toArray();
	}

	public TreeSkill addChild(String newChild) {
		children.add(newChild);
		return this;
	}

	public String[] getChildren() {
		return children.toArray(new String[children.size()]);
	}

	public TreeSkill setType(SkillType newType) {
		type = newType;
		return this;
	}

	public SkillType getType() {
		return type;
	}
}
