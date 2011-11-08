package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.AttackEvent;
import mmo.SkillTree.Events.SkillUseEvent;
import mmo.SkillTree.MMOSkillTree;

public class Skill{
    MMOSkillTree skillTreePlugin;
    private String name;
    private String desc;
    private int lvl = 0;
    private int healthCost = 0;
    private int hungerCost = 0;
    private int manaCost = 0;
    private int cooldown = 0;
    
    public Skill(){
        skillTreePlugin = MMOSkillTree.skillTreePlugin;
        addListener("SKILL_USE");
    }
    
    public void onAttack(AttackEvent event){}
    public void onSkillUse(SkillUseEvent event){}
    
    public void addListener(String eventName){
        skillTreePlugin.addSkillListener(eventName, this);
    }
    public void removeListener(String eventName){
        skillTreePlugin.removeSkillListener(eventName, this);
    }
}