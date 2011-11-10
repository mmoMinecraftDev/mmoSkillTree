package mmo.SkillTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import mmo.Core.MMOPlugin;
import mmo.Core.util.EnumBitSet;
import mmo.SkillTree.Events.BlockXpListener;
import mmo.SkillTree.Events.CombatXpListener;
import mmo.SkillTree.Events.SkillListener;
import mmo.SkillTree.Events.SkillPlayerListener;
import mmo.SkillTree.GUI.SkillsGui;
import mmo.SkillTree.GUI.TreeTabButton;
import mmo.SkillTree.Skills.Skill;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MMOSkillTree extends MMOPlugin{

	public static MMOPlayerManager mmoPlayerManager;
	public HashMap<String, HashSet<Skill>> listeners;
	
	@Override
	public EnumBitSet mmoSupport( EnumBitSet support ){
		support.set( Support.MMO_AUTO_EXTRACT );
		return support;
	}

	@Override
	public void onEnable(){
		super.onEnable();

		SkillsGui.plugin = this;
		Skill.plugin = this;

		listeners = new HashMap<String, HashSet<Skill>>();

		mmoSkillTreePlayerListener playerListener = new mmoSkillTreePlayerListener();
		pm.registerEvent( Type.PLAYER_CHAT, playerListener, Priority.Normal, this );
		pm.registerEvent( Type.PLAYER_JOIN, playerListener, Priority.Normal, this );

		pm.registerEvent( Type.CUSTOM_EVENT, new mmoSkillTreeInputListener(), Priority.Normal, this );
		pm.registerEvent( Type.CUSTOM_EVENT, new mmoSkillTreeScreenListener(), Priority.Normal, this );
		pm.registerEvent( Type.BLOCK_BREAK, new BlockXpListener( this ), Priority.Monitor, this );
		pm.registerEvent( Type.CUSTOM_EVENT, new CombatXpListener( this ), Priority.Normal, this );
		pm.registerEvent( Type.CUSTOM_EVENT, new SkillListener( this ), Priority.Normal, this );
		pm.registerEvent( Type.PLAYER_INTERACT, new SkillPlayerListener(), Priority.Normal, this );

		mmoPlayerManager = new MMOPlayerManager( this );
	}

	public class mmoSkillTreePlayerListener extends PlayerListener{

		private SkillsGui gui;

		public mmoSkillTreePlayerListener(){
			gui = new SkillsGui();
		}

		@Override
		public void onPlayerJoin( PlayerJoinEvent event ){
			Player p = event.getPlayer();
			gui.onJoin( p );
			p.sendMessage( ChatColor.RED + "[rph] " + ChatColor.WHITE + "The RPG mod I'm making doesn't do anything yet, "
				+ "but you can see the progress."
				+ "\nDonate if you can too, please. <3" );
		}
	}

	public class mmoSkillTreeInputListener extends InputListener{

		private SkillsGui gui;
		private ArrayList holdingCtrl = new ArrayList(); //checks if someone is holding ctrl when they press another key.
		HashMap<SpoutPlayer, ItemStack> lastWep = new HashMap<SpoutPlayer, ItemStack>(); //Stores the weapon they were hitting when holding ctrl, to be reverted back when they hit a num key for the obvious reason.
		
		public mmoSkillTreeInputListener(){
			gui = new SkillsGui();
		}

		@Override
		public void onKeyReleasedEvent( KeyReleasedEvent event ){
			Keyboard key = event.getKey();
			if( key == Keyboard.KEY_K ){
				SpoutPlayer p = event.getPlayer();
				Screen screen = p.getMainScreen();
				if( p.getActiveScreen() == ScreenType.GAME_SCREEN ){
					if( gui.skillTreeIsOpen( screen ) ){
						gui.closeSkillTree();
					} else {
						gui.openSkillTree( p );
					}
				}
			}
			if( key == Keyboard.KEY_LCONTROL ){
				SpoutPlayer p = event.getPlayer();
				holdingCtrl.remove( p );
				lastWep.remove( p );
			}
		}
		
		@Override
		public void onKeyPressedEvent( KeyPressedEvent event){
			Keyboard key = event.getKey();
			switch(key){
				case KEY_1:
				case KEY_2:
				case KEY_3:
				case KEY_4:
				case KEY_5:
				case KEY_6:
				case KEY_7:
				case KEY_8:
				case KEY_9:
					SpoutPlayer p = event.getPlayer();
					if( holdingCtrl.contains( p ) ){
						int numKey = (int)key.toString().charAt(4) - 49;
						p.sendMessage(numKey+" pressed");
						//p.setItemInHand( lastWep.get( p ) ); // This is wrong. This puts the pressed keys item in the current hand. It doesn't change the #slot that's active..
						SkillsPlayer mmoPlayer = MMOSkillTree.mmoPlayerManager.get(p);
						mmoPlayer.activateSkill(numKey);

					}
				break;
			}
			if( key == Keyboard.KEY_LCONTROL ){
				SpoutPlayer p = event.getPlayer();
				holdingCtrl.add( p );
				lastWep.put( p, p.getItemInHand() );
			}
		}
	}

	public class mmoSkillTreeScreenListener extends ScreenListener{

		private SkillsGui gui;

		public mmoSkillTreeScreenListener(){
			gui = new SkillsGui();
		}

		@Override
		public void onButtonClick( ButtonClickEvent event ){
			TreeTabButton button = (TreeTabButton) event.getButton();
			SpoutPlayer sPlayer = event.getPlayer();
			sPlayer.sendMessage( "Button " + button.getSkillSet().toString() + " clicked" );
			gui.switchSkillTreeTab( button, sPlayer );
		}

		/*public void onScreenClose(ScreenCloseEvent event){
		
		}*/
	}

	public void addSkillListener( String eventName, Skill skill ){
		System.out.println( "Adding skill listener for " + eventName + ", skill:" + skill );
		HashSet<Skill> skills = listeners.get( eventName );
		if( skills == null ){
			skills = new HashSet<Skill>();
			listeners.put( eventName, skills );
		}
		skills.add( skill );
	}

	public void removeSkillListener( String eventName, Skill skill ){
		HashSet<Skill> skills = listeners.get( eventName );
		if( skills != null ){
			skills.remove( skill );
			if( skills.isEmpty() ){
				listeners.remove( eventName );
			}
		}
	}

	public HashSet getListenerSet( String eventName ){
		System.out.println( "getListenerSet string:" + eventName );
		return listeners.get( eventName );
	}
}