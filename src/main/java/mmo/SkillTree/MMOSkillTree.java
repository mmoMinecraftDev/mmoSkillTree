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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
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

		SpoutManager.getKeyBindingManager().registerBinding("MMO Skill tree", Keyboard.KEY_9, "", new mmoSkillTreeInputListener(), this);

		pm.registerEvents(new mmoSkillTreePlayerListener(), this);

		pm.registerEvents(new mmoSkillTreeScreenListener(), this);
		pm.registerEvents(new BlockXpListener(this), this);
		pm.registerEvents(new CombatXpListener(this), this);
		pm.registerEvents(new SkillListener(this), this);
		pm.registerEvents(new SkillPlayerListener(), this);

		mmoPlayerManager = new MMOPlayerManager(this);
	}

	public class mmoSkillTreePlayerListener implements Listener {
		private SkillsGui gui;

		public mmoSkillTreePlayerListener() {
			gui = new SkillsGui();
		}

		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
			Player p = event.getPlayer();
			gui.onJoin(p);
			p.sendMessage(ChatColor.RED + "[rph] " + ChatColor.WHITE + "The RPG mod I'm making doesn't do anything yet, "
					+ "but you can see the progress."
					+ "\nDonate if you can too, please. <3");
		}
	}

	public class mmoSkillTreeInputListener implements BindingExecutionDelegate {
		private SkillsGui gui;
		private ArrayList holdingCtrl = new ArrayList(); //checks if someone is holding ctrl when they press another key.
		HashMap<SpoutPlayer, ItemStack> lastWep = new HashMap<SpoutPlayer, ItemStack>(); //Stores the weapon they were hitting when holding ctrl, to be reverted back when they hit a num key for the obvious reason.

		public mmoSkillTreeInputListener() {
			gui = new SkillsGui();
		}

		@Override
		public void keyPressed(KeyBindingEvent keyBindingEvent) {
			Keyboard key = keyBindingEvent.getBinding().getDefaultKey();
			switch (key) {
				case KEY_1:
				case KEY_2:
				case KEY_3:
				case KEY_4:
				case KEY_5:
				case KEY_6:
				case KEY_7:
				case KEY_8:
				case KEY_9:
					SpoutPlayer p = keyBindingEvent.getPlayer();
					if (holdingCtrl.contains(p)) {
						int numKey = (int) key.toString().charAt(4) - 49;
						p.sendMessage(numKey + " pressed");
						//p.setItemInHand( lastWep.get( p ) ); // This is wrong. This puts the pressed keys item in the current hand. It doesn't change the #slot that's active..
						SkillsPlayer mmoPlayer = MMOSkillTree.mmoPlayerManager.get(p);
						mmoPlayer.activateSkill(numKey);
					}
					break;
			}
			if (key == Keyboard.KEY_LCONTROL) {
				SpoutPlayer p = keyBindingEvent.getPlayer();
				holdingCtrl.add(p);
				lastWep.put(p, p.getItemInHand());
			}
		}

		@Override
		public void keyReleased(KeyBindingEvent keyBindingEvent) {
			Keyboard key = keyBindingEvent.getBinding().getDefaultKey();
			if (key == Keyboard.KEY_K) {
				SpoutPlayer p = keyBindingEvent.getPlayer();
				Screen screen = p.getMainScreen();
				if (p.getActiveScreen() == ScreenType.GAME_SCREEN) {
					if (gui.skillTreeIsOpen(screen)) {
						gui.closeSkillTree();
					} else {
						gui.openSkillTree(p);
					}
				}
			}
			if (key == Keyboard.KEY_LCONTROL) {
				SpoutPlayer p = keyBindingEvent.getPlayer();
				holdingCtrl.remove(p);
				lastWep.remove(p);
			}
		}
	}

	public class mmoSkillTreeScreenListener implements Listener {
		private SkillsGui gui;

		public mmoSkillTreeScreenListener() {
			gui = new SkillsGui();
		}

		@EventHandler
		public void onButtonClick(ButtonClickEvent event) {
			TreeTabButton button = (TreeTabButton) event.getButton();
			SpoutPlayer sPlayer = event.getPlayer();
			sPlayer.sendMessage("Button " + button.getSkillSet().toString() + " clicked");
			gui.switchSkillTreeTab(button, sPlayer);
		}
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