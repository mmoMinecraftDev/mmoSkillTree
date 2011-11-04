package mmo.skills.mmoSkillTree;

import mmo.skills.mmoSkillTree.GUI.SkillsGui;
import mmo.skills.mmoSkillTree.GUI.TreeTabButton;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillsGuiButtonEvents extends ScreenListener{
	
	private static SkillTree plugin;
	private static SkillsGui gui;
	
	public SkillsGuiButtonEvents(){
		plugin = SkillTree.plugin;
		gui = new SkillsGui(plugin);
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		TreeTabButton button = (TreeTabButton) event.getButton();
		SpoutPlayer sPlayer = event.getPlayer(); 
		sPlayer.sendMessage("Button "+button.getSkillSet().toString()+" clicked");
		gui.switchSkillTreeTab(button, sPlayer);
	}
	
	/*public void onScreenClose(ScreenCloseEvent event){
		
	}*/
}
