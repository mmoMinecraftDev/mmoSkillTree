package mmo.SkillTree;

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
import mmo.SkillTree.Skills.FinalSkills.MagicArrowSkill;
import mmo.SkillTree.Skills.Skill;
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
	public HashMap<String, HashSet<Skill>> listeners;

	@Override
	public EnumBitSet mmoSupport(EnumBitSet support) {
		support.set(Support.MMO_AUTO_EXTRACT);
		return support;
	}

	@Override
	public void onEnable() {
		super.onEnable();

		SkillsGui.plugin = this;
		Skill.plugin = this;

		listeners = new HashMap<String, HashSet<Skill>>();

		mmoSkillTreePlayerListener playerListener = new mmoSkillTreePlayerListener();
		pm.registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Normal, this);
		pm.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);

		pm.registerEvent(Type.CUSTOM_EVENT, new mmoSkillTreeInputListener(), Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new mmoSkillTreeScreenListener(), Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_BREAK, new BlockXpListener(this), Priority.Monitor, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new CombatXpListener(this), Priority.Normal, this);
		pm.registerEvent(Type.CUSTOM_EVENT, new SkillListener(this), Priority.Normal, this);
		pm.registerEvent(Type.PLAYER_INTERACT, new SkillPlayerListener(), Priority.Normal, this);

		mmoPlayerManager = new MMOPlayerManager(this);

		new MagicArrowSkill();
	}

	public class mmoSkillTreePlayerListener extends PlayerListener {

		private SkillsGui gui;

		public mmoSkillTreePlayerListener() {
			gui = new SkillsGui();
		}

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

	public void addSkillListener(String eventName, Skill skill) {
		System.out.println("Adding skill listener for " + eventName + ", skill:" + skill);
		HashSet<Skill> skills = listeners.get(eventName);
		if (skills == null) {
			skills = new HashSet<Skill>();
			listeners.put(eventName, skills);
		}
		skills.add(skill);
	}

	public void removeSkillListener(String eventName, Skill skill) {
		HashSet<Skill> skills = listeners.get(eventName);
		if (skills != null) {
			skills.remove(skill);
			if (skills.isEmpty()) {
				listeners.remove(eventName);
			}
		}
	}

	public HashSet getListenerSet(String eventName) {
		System.out.println("getListenerSet string:" + eventName);
		return listeners.get(eventName);
	}
}