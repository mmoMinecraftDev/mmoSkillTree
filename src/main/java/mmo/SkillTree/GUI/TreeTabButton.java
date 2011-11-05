package mmo.SkillTree.GUI;

import mmo.SkillTree.Skills.SkillSet;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.RenderPriority;

public class TreeTabButton extends GenericButton {
	SkillSet skillSet;
	
	public TreeTabButton(SkillSet skillSet){
		setSkillSet(skillSet);
		this.setWidth(55).setHeight(21).setFixed(true);
		this.setPriority(RenderPriority.High);
	}
	
	public void setSkillSet(SkillSet skillSet){
		this.skillSet = skillSet;
	}
	
	public SkillSet getSkillSet(){
		return skillSet;
	}
}
