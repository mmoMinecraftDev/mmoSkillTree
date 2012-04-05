package mmo.SkillTree.Weapons;

import java.util.ArrayList;
import java.util.List;

import mmo.SkillTree.Skills.SkillSet;

public enum WeaponSet {
	Axe(SkillSet.Slashing, SkillSet.Tool),
	Bow(SkillSet.Bow),
	Hoe(SkillSet.Slashing, SkillSet.Tool),
	Pickaxe(SkillSet.Piercing, SkillSet.Tool),
	Shovel(SkillSet.Bashing, SkillSet.Tool),
	Sword(SkillSet.Slashing),
	Mace(SkillSet.Bashing),
	Spear(SkillSet.Piercing),
	Polearm(SkillSet.Slashing),
	Unarmed(SkillSet.Unarmed, SkillSet.Tool);
	private ArrayList<SkillSet> skillSets = new ArrayList<SkillSet>();
	static final List<String> list = new ArrayList<String>();

	private WeaponSet(final SkillSet... skillSets) {
		for (SkillSet s : skillSets) {
			this.skillSets.add(s);
		}
	}

	public boolean is(SkillSet skillSet) {
		if (skillSets.contains(skillSet)) {
			return true;
		}
		return false;
	}

	/*
		 * FUTURE ISSUE: Weapons could have more than one combat skillset, or more extra skillsets than just tools..
		 */
	public SkillSet getCombatSkillSet() {
		ArrayList<SkillSet> skillSetsMinusTools = new ArrayList<SkillSet>();
		skillSetsMinusTools = skillSets;
		skillSetsMinusTools.remove(SkillSet.Tool);
		SkillSet combatSet = skillSetsMinusTools.get(0);
		return combatSet;
	}

	public static WeaponSet getFromString(String name) {
		if (name != null) {
			for (WeaponSet t : values()) {
				if (t.name().equals(name)) {
					return t;
				}
			}
		}

		return null;
	}

	static {
		for (WeaponSet t : values()) {
			list.add(t.name());
		}
	}
}