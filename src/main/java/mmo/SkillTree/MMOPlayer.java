package mmo.SkillTree;

import java.util.EnumMap;
import java.util.Map;

import mmo.SkillTree.GUI.MMOXpBar;
import mmo.SkillTree.GUI.SkillBar;
import mmo.SkillTree.Skills.Skill;
import mmo.SkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MMOPlayer{

	private int mana = 0;
	private Player player;
	private MMOXpBar xpBar;
	private SkillBar skillBar;
	private Map<SkillSet, Set> sets;
	private boolean hasSpout = false;

	public MMOPlayer( MMOSkillTree plugin, Player player ){
		System.out.println("MMOPlayer constructor");
		this.player = player;
		xpBar = new MMOXpBar( plugin, player );
		skillBar = new SkillBar( plugin, player );
		sets = new EnumMap<SkillSet, Set>(SkillSet.class);
		hasSpout = ( (SpoutPlayer) player ).isSpoutCraftEnabled();
	}

	public Set getSet( SkillSet skillSet ){
		Set set = sets.get( skillSet );
		if( set != null ){
			return set;
		}
		// If it gets to this stage then it's null, so create it.
		addSet( skillSet );
		set = sets.get( skillSet );
		return set;
	}

	public void addSet( SkillSet skillSet ){
		sets.put( skillSet, new Set( skillSet, this ) );
	}

	public Player getPlayer(){
		return this.player;
	}

	public int getMana(){
		return mana;
	}

	public void increaseMana( int mana ){
		this.mana = this.mana + mana;
	}

	public void addXp( int xpAmount, SkillSet skillSet ){
		Set set = getSet( skillSet );
		set.addXp( xpAmount );

		float percent = ( (float) set.getCurLvlXp() / set.getNextLvlXp() );
		xpBar.draw( percent, skillSet );
	}
	
	public void setSkill( int pos, Skill skill ){
		skillBar.setSkill(pos, skill);
	}

	public boolean hasSpout(){
		return hasSpout;
	}
}