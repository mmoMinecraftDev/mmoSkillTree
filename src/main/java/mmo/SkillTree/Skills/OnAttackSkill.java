package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.SkillUseEvent;

public class OnAttackSkill extends Skill{
    int uses = 5;
    public OnAttackSkill(){
        addListener("ATTACK");
    }
    
    @Override
    public void onSkillUse(SkillUseEvent event){
        uses = uses-1;
        event.getPlayer().sendMessage(uses+" skill uses left.");
        if( uses == 0){
            removeListener("ATTACK");
        }
    }
}