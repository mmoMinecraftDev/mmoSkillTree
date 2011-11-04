package mmo.skills.mmoSkillTree;

import java.util.HashMap;
import java.util.Map;

import mmo.skills.mmoSkillTree.GUI.MMOXpBar;
import mmo.skills.mmoSkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;

public class MMOPlayer {
	private int mana = 0;
	private Player player;
	private MMOXpBar xpBar;
	private Map<SkillSet, Set> sets;
	
	public MMOPlayer(Player player) {
		this.player = player;
		xpBar = new MMOXpBar(player);
		sets = new HashMap<SkillSet, Set>();
	}
	
	public Set getSet(SkillSet skillSet){
		Set set = sets.get(skillSet);
		if( set != null ) {
			return set;
		}
		// If it gets to this stage then it's null, so create it.
		addSet(skillSet);
		set = sets.get(skillSet);
		return set;
	}
	public void addSet(SkillSet skillSet){
		sets.put(skillSet, new Set(skillSet));
	}

	public Player getPlayer(){
		return this.player;
	}

	public int getMana() {
		return mana;
	}
	public void increaseMana(int mana) {
		this.mana = this.mana + mana;
	}
	
	public void addXp(int xpAmount, SkillSet skillSet) {
		Set set = getSet(skillSet);
		set.addXp(xpAmount);
		
		float percent = ((float)set.getCurLvlXp() / set.getNextLvlXp());
		xpBar.draw(percent, skillSet);
	}
	
}
