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