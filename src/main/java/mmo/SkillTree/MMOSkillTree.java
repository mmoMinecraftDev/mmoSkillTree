package mmo.SkillTree;

import mmo.Core.MMOPlugin;
import mmo.Core.util.EnumBitSet;
import mmo.SkillTree.Events.BlockXpListener;
import mmo.SkillTree.Events.CombatXpListener;
import mmo.SkillTree.GUI.SkillsGui;
import mmo.SkillTree.GUI.TreeTabButton;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MMOSkillTree extends MMOPlugin {

	public static MMOPlayerManager mmoPlayerManager;
        public static MMOPlugin plugin;

	@Override
	public EnumBitSet mmoSupport(EnumBitSet support) {
		support.set(Support.MMO_AUTO_EXTRACT);
		return support;
	}

	@Override
	public void onEnable() {
		super.onEnable();
                this.plugin = plugin;
                
		mmoSkillTreePlayerListener playerListener = new mmoSkillTreePlayerListener();
		pm.registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Normal, this);
		pm.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new mmoSkillTreeInputListener(), Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new mmoSkillTreeScreenListener(), Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_BREAK, new BlockXpListener(this), Priority.Monitor, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new CombatXpListener(this), Priority.Normal, this);

		mmoPlayerManager = new MMOPlayerManager(this);
	}

	public class mmoSkillTreePlayerListener extends PlayerListener {

		private SkillsGui gui;

		public mmoSkillTreePlayerListener() {
			gui = new SkillsGui();
		}

		/*public void onPlayerChat(PlayerChatEvent event){
			Player p = event.getPlayer();
			String msg = event.getMessage().toLowerCase();
			if( msg.contains("hi") && msg.contains("server") ){
				p.sendMessage(ChatColor.RED + "[Server] " + ChatColor.WHITE + "Hi!");
			}
		}*/

		@Override
		public void onPlayerJoin(PlayerJoinEvent event) {
			Player p = event.getPlayer();
			gui.skillHint(p);
			p.sendMessage(ChatColor.RED + "[rph] " + ChatColor.WHITE + "The RPG mod I'm making doesn't do anything yet, "
					+ "but you can see the progress."
					+ " I also removed mcmmo because it's pretty boring. I added Heroes and some other things."
					+ "\nDonate if you can too, please. <3");
		}
	}

	public class mmoSkillTreeInputListener extends InputListener {

		private SkillsGui gui;

		public mmoSkillTreeInputListener() {
			gui = new SkillsGui();
		}

		@Override
		public void onKeyReleasedEvent(KeyReleasedEvent event) {
			if (event.getKey() == Keyboard.KEY_K) {
				SpoutPlayer p = event.getPlayer();
				Screen screen = p.getMainScreen();
				if (p.getActiveScreen() == ScreenType.GAME_SCREEN) {
					if (gui.skillTreeIsOpen(screen)) {
						gui.closeSkillTree();
					} else {
						gui.openSkillTree(p);
					}
				}
			}
		}
	}

	public class mmoSkillTreeScreenListener extends ScreenListener {

		private SkillsGui gui;

		public mmoSkillTreeScreenListener() {
			gui = new SkillsGui();
		}

		@Override
		public void onButtonClick(ButtonClickEvent event) {
			TreeTabButton button = (TreeTabButton) event.getButton();
			SpoutPlayer sPlayer = event.getPlayer();
			sPlayer.sendMessage("Button " + button.getSkillSet().toString() + " clicked");
			gui.switchSkillTreeTab(button, sPlayer);
		}

		/*public void onScreenClose(ScreenCloseEvent event){

		}*/
	}

}