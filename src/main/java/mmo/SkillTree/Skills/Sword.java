package mmo.SkillTree.Skills;

import java.util.HashMap;

public class Sword {
	public HashMap<String, Skill> map = new HashMap<String, Skill>();
	public Sword(){
		Skill blank3		= new Skill().setName("Blank3").setType(SkillType.PASSIVE).setRow(4).setCol(3);
		Skill hilt_bash		= new Skill().setName("Hilt Bash").setType(SkillType.ACTIVE).setRow(4).setCol(0);
		Skill blank1		= new Skill().setName("Blank1").setType(SkillType.PASSIVE).setRow(3).setCol(3).addChild("Blank3");
		Skill triforce		= new Skill().setName("Triforce").setType(SkillType.PASSIVE).setRow(3).setCol(2);
		Skill spirit_of_fire = new Skill().setName("Spirit of Storm").setType(SkillType.PASSIVE).setRow(3).setCol(1).addChild("Triforce");
		Skill spirit_of_storm = new Skill().setName("Spirit of Storm").setType(SkillType.PASSIVE).setRow(2).setCol(2).addChild("Triforce");
		Skill spirit_of_ice = new Skill().setName("Spirit of Ice").setType(SkillType.PASSIVE).setRow(2).setCol(1).addChild("Triforce");
		Skill fencing		= new Skill().setName("Fencing").setType(SkillType.PASSIVE).setRow(1).setCol(3).addChild("Blank1");
		Skill rend			= new Skill().setName("Rend").setType(SkillType.PASSIVE).setRow(1).setCol(1);
		Skill flame_slash	= new Skill().setName("Flame Slash").setType(SkillType.ACTIVE).setRow(0).setCol(1);
		Skill impale		= new Skill().setName("Impale").setType(SkillType.ACTIVE).setRow(0).setCol(0).addChild("Rend").addChild("Hilt Bash");
		Skill basic_swordsmanship = new Skill().setName("Basic Swordsmanship").setType(SkillType.PASSIVE).setRow(0).setCol(3);
		map.put("Impale", impale );
		map.put("Flame Slash", flame_slash);
		map.put("Basic Swordsmanship", basic_swordsmanship );
		map.put("Rend", rend );
		map.put("Fencing", fencing);
		map.put("Spirit of Ice", spirit_of_ice);
		map.put("Spirit of Storm", spirit_of_storm);
		map.put("Spirit of Fire", spirit_of_fire);
		map.put("Triforce", triforce);
		map.put("Blank1", blank1);
		map.put("Hilt Bash", hilt_bash);
		map.put("Blank3", blank3);
	}
	public Skill getSkill(String skillName) {
		return map.get(skillName);
	}
}