package mmo.SkillTree.Events;

import java.util.HashSet;

import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.Skill;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkillListener implements Listener {
	MMOSkillTree skillTreePlugin;

	public SkillListener(MMOSkillTree subPlugin) {
		skillTreePlugin = subPlugin;
	}

	@EventHandler
	public void onAttack(AttackEvent event) {
		HashSet<Skill> skills = skillTreePlugin.listeners.get(event.getEventName());
		if (skills != null) {
			for (Skill skill : skills) {
				skill.onAttack(event);
			}
		}
	}

	@EventHandler
	private void onSkillUse(SkillUseEvent event) {
		HashSet<Skill> skills = skillTreePlugin.listeners.get(event.getEventName());
		if (skills != null) {
			for (Skill skill : skills) {
				skill.onSkillUse(event);
			}
		}
	}
}