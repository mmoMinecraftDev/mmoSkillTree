package mmo.SkillTree.Events;

import mmo.SkillTree.GUI.SkillsGui;
import mmo.SkillTree.GUI.TreeTabButton;

import mmo.SkillTree.MMOSkillTree;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillsGuiButtonEvents extends ScreenListener{
	
	private static SkillsGui gui;
	
	public SkillsGuiButtonEvents(){
		gui = new SkillsGui();
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		TreeTabButton button = (TreeTabButton) event.getButton();
		SpoutPlayer sPlayer = event.getPlayer();
		gui.switchSkillTreeTab(button, sPlayer);
	}
	
	/*public void onScreenClose(ScreenCloseEvent event){
		
	}*/
}
