/*
 * This file is part of mmoSkillTree <http://github.com/mmoMinecraftDev/mmoSkillTree>.
 *
 * mmoSkillTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mmo.SkillTree.GUI;

import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.Skill;

import org.bukkit.entity.Player;

import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillBar {
	private Player player;
	private MMOSkillTree plugin;
	private GenericContainer skillBar;
	private GenericTexture skillIcons[] = new GenericTexture[9];
	private Skill[] skills = new Skill[9];

	public SkillBar(MMOSkillTree plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;

		skillBar = (GenericContainer) new GenericContainer().setAnchor(WidgetAnchor.CENTER_RIGHT).setLayout(ContainerType.VERTICAL).setPriority(RenderPriority.High).setWidth(20).setHeight(180).setX(-20).setY(-90);
		sPlayer.getMainScreen().attachWidget(plugin, skillBar);
		GenericTexture bg = new GenericTexture("res/_skillBar.png");
		bg.setHeight(182).setWidth(22).setX(-22).setY(-91)
				.setAnchor(WidgetAnchor.CENTER_RIGHT).setPriority(RenderPriority.Highest);
		sPlayer.getMainScreen().attachWidget(plugin, bg);

		for (int i = 0; i < 9; i++) {
			skillIcons[i] = new GenericTexture("");
			skillIcons[i].setHeight(16).setWidth(16).setMargin(2, 1).setFixed(true);
			skills[i] = new Skill(player);
		}
		skillBar.addChildren(skillIcons);
	}

	public void setSkill(int pos, Skill skill) {
		skillIcons[pos].setUrl("res/Magic_Arrow.png").setDirty(true);
		skills[pos] = skill;
	}

	public Skill getSkill(int pos) {
		return skills[pos];
	}
}