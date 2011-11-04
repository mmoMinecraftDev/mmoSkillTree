package mmo.skills.mmoSkillTree;

import mmo.skills.mmoSkillTree.GUI.SkillsGui;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillsKeyboardListener extends InputListener {
	
	private static SkillTree plugin;
	private static SkillsGui gui;
	
	public SkillsKeyboardListener(){
		plugin = SkillTree.plugin;
		gui = new SkillsGui(plugin);
	}
	
    @Override
    public void onKeyReleasedEvent(KeyReleasedEvent event) {
    	if( event.getKey() == Keyboard.KEY_K ){
    		SpoutPlayer p = event.getPlayer();
    		Screen screen = p.getMainScreen();
    		if( p.getActiveScreen() == ScreenType.GAME_SCREEN ){
	    		if( gui.skillTreeIsOpen(screen) )
	    			gui.closeSkillTree();
	    		else
	    			gui.openSkillTree(p);
    		}
    	}
    }
}