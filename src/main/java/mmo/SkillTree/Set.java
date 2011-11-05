package mmo.SkillTree;

import mmo.SkillTree.Skills.SkillSet;

public class Set {

	public int xp = 0;
	public int lvl = 0;
	public int skillPoints = 0;
	public int spentSkillPoints = 0;
	public String string;
	@SuppressWarnings("unused")
	private SkillSet skillSet;

	Set(SkillSet skillSet) {
		this.skillSet = skillSet;
		this.string = skillSet.toString();
	}

	public void addXp(int xpAmount) {
		this.xp = this.xp + xpAmount;

		if (getCurLvlXp() > getNextLvlXp()) {
			this.lvl = this.lvl + 1;
			this.skillPoints = this.skillPoints + 1;
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
