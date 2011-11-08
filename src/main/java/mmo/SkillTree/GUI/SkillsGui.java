package mmo.SkillTree.GUI;

import java.util.logging.Logger;

import mmo.Core.MMOPlugin;
import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.SkillSet;
import mmo.SkillTree.Skills.Tree;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.WidgetAnchor;

public class SkillsGui {

	public static MMOPlugin plugin;
	public static final Logger logger = Logger.getLogger("Minecraft");

	public SkillsGui() {
	}
	static GenericPopup skillTreePopup;
	static Tree curTab;

	public void skillHint(Player player) {
		SpoutPlayer sPlayer = SpoutManager.getPlayer(player);

		GenericLabel label = new GenericLabel("Press \"K\" to open Skills");
		label.setTextColor(new Color(1.0F, 0.3F, 0.15F, 1.0F));
		label.setAlign(WidgetAnchor.BOTTOM_RIGHT).setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		sPlayer.getMainScreen().attachWidget(plugin, label);

		new SkillBar(plugin, player);
	}

	public void openSkillTree(Player player) {
		SpoutPlayer sPlayer = SpoutManager.getPlayer(player);

		GenericPopup popup = new GenericPopup();
		skillTreePopup = popup;

		GenericTexture bg = new GenericTexture();
		bg.setUrl("SkillTree.png");
		bg.setWidth(300).setHeight(230);
		bg.setX(-150).setY(10);
		bg.setPriority(RenderPriority.Highest);
		bg.setAnchor(WidgetAnchor.TOP_CENTER);
		popup.attachWidget(plugin, bg);

		Container tabs = new GenericContainer();
		tabs.setLayout(ContainerType.VERTICAL);
		tabs.setWidth(50).setHeight(230);
		tabs.setAnchor(WidgetAnchor.TOP_CENTER);
		tabs.setX(91).setY(11);

		createTab(tabs, SkillSet.Bashing);
		createTab(tabs, SkillSet.Piercing);
		createTab(tabs, SkillSet.Slashing);
		createTab(tabs, SkillSet.Unarmed);
		createTab(tabs, SkillSet.Bow);
		createTab(tabs, SkillSet.Magic);
		createTab(tabs, SkillSet.Empathy);
		createTab(tabs, SkillSet.Tool);
		createTab(tabs, SkillSet.Smithing);

		popup.attachWidget(plugin, tabs);

		sPlayer.getMainScreen().attachPopupScreen(popup);
	}

	public boolean skillTreeIsOpen(Screen screen) {
		if (skillTreePopup == null) {
			return false;
		}
		if (skillTreePopup == ((InGameHUD) screen).getActivePopup()) {
			return true;
		} else {
			return false;
		}
	}

	public void closeSkillTree() {
		skillTreePopup.close();
	}

	public void switchSkillTreeTab(TreeTabButton button, SpoutPlayer sPlayer) {
		if (curTab != null) {
			curTab.close();
			curTab = null;
		}
		SkillSet skillSet = button.getSkillSet();
		curTab = new Tree(plugin, skillTreePopup, sPlayer, skillSet);
	}

	public void createTab(Container box, SkillSet skillSet) {
		String name = skillSet.toString();

		Container outter = new GenericContainer();
		outter.setAlign(WidgetAnchor.CENTER_CENTER);
		Container buttonStack = new GenericContainer();
		buttonStack.setLayout(ContainerType.OVERLAY);
		buttonStack.setMargin(2, 0, 0, 2);
		buttonStack.shiftXPos(buttonStack.getWidth() / -2);

		TreeTabButton tabButton = new TreeTabButton(skillSet);
		buttonStack.addChild(tabButton);
		/*GenericButton button = new GenericButton("");
		button.setWidth(55).setHeight(21).setFixed(true);
		button.setTooltip(name + " Skills");
		button.setPriority(RenderPriority.High);
		buttonStack.addChild(button);*/

		GenericTexture tex = new GenericTexture();
		tex.setUrl(name + ".png");
		tex.setWidth(16).setHeight(16).setFixed(true);
		tex.setMargin(2, 22, 3, 2);
		tex.setPriority(RenderPriority.Normal);
		buttonStack.addChild(tex);

		GenericLabel label = new GenericLabel();
		label.setText(name + "\nSkills");
		label.setPriority(RenderPriority.Normal);
		label.setWidth(22);
		label.setMargin(3, 2, 3, 19);
		label.setScale(0.8F);
		buttonStack.addChild(label);

		outter.addChild(buttonStack);
		box.addChild(outter);
	}
}