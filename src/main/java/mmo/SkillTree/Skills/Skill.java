package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.AttackEvent;
import mmo.SkillTree.Events.SkillUseEvent;
import mmo.SkillTree.MMOSkillTree;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Skill{

	static public MMOSkillTree plugin;
	protected Player player;
	protected String name;
	protected String desc;
	protected int lvl = 0;
	protected int healthCost = 0;
	protected int hungerCost = 0;
	protected int manaCost = 0;
	protected int cooldown = 0;

	public Skill( Player player ){
		this.player = player;
		addListener( "SKILL_USE" );
	}

	public void onAttack( AttackEvent event ){
	}

	public void onSkillUse( SkillUseEvent event ){
	}

	public void addListener( String eventName ){
		plugin.addSkillListener( eventName, this );
	}

	public void removeListener( String eventName ){
		plugin.removeSkillListener( eventName, this );
	}

	public void activate(){
		player.sendMessage("No skill set.");
	}
	/*
	 * Calls the skill use event for this skill.
	 */
	public void skillUseEvent(){
		SkillUseEvent skillUseEvent = new SkillUseEvent( player );
		Bukkit.getServer().getPluginManager().callEvent( skillUseEvent );
	}
}