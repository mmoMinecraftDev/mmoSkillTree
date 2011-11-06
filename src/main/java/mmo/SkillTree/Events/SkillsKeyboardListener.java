package mmo.SkillTree.Events;

import mmo.SkillTree.GUI.SkillsGui;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillsKeyboardListener extends InputListener {
	
	private static SkillsGui gui;
	
	public SkillsKeyboardListener(){
		gui = new SkillsGui();
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