package mmo.SkillTree.Skills;

import mmo.SkillTree.Events.SkillUseEvent;

import org.bukkit.entity.Player;

public class OnAttackSkill extends Skill {
	protected int uses = 3;
	protected int usesLeft = 0;

	public OnAttackSkill(Player player) {
		super(player);
	}

	@Override
	public void activate() {
		addListener("ATTACK");
		usesLeft = uses;
	}

	@Override
	public void onSkillUse(SkillUseEvent event) {
		usesLeft = usesLeft - 1;
		event.getPlayer().sendMessage(usesLeft + " skill uses left.");
		if (usesLeft == 0) {
			removeListener("ATTACK");
		}
	}
}