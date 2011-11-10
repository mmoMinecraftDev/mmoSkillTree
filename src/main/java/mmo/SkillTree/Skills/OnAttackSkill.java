package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.SkillUseEvent;

public class OnAttackSkill extends Skill{

	int uses = 3;
	int usesLeft = 0;
	
	public OnAttackSkill(){
	}
	
	@Override
	public void activate(){
		addListener( "ATTACK" );
		usesLeft = uses;
	}

	@Override
	public void onSkillUse( SkillUseEvent event ){
		usesLeft = usesLeft - 1;
		event.getPlayer().sendMessage( usesLeft + " skill uses left." );
		if( usesLeft == 0 ){
			removeListener( "ATTACK" );
		}
	}
}