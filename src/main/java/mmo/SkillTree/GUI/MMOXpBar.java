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
import mmo.SkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericGradient;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MMOXpBar {
	private Player player;
	private MMOSkillTree plugin;
	private GenericContainer xpBox;
	private GenericGradient xpBar;
	private GenericTexture xpIcon;

	public MMOXpBar(MMOSkillTree plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;

		xpBox = (GenericContainer) new GenericContainer(
				new GenericTexture("_xpBarBg.png")
						.setHeight(5)
						.setWidth(182)
						.setMargin(1, 1, 2, 1)
						.setPriority(RenderPriority.Highest),
				new GenericTexture("_xpBar.png")
						.setHeight(5)
						.setWidth(182)
						.setMargin(1, 1, 2, 1)
						.setPriority(RenderPriority.Low)
		)
				.setAnchor(WidgetAnchor.TOP_CENTER)
				.setLayout(ContainerType.OVERLAY)
				.setWidth(182)
				.setHeight(8)
				.setX(-91)
				.setY(20);
		sPlayer.getMainScreen().attachWidget(plugin, xpBox);
	}

	public void draw(float percent, SkillSet skillSet) {
		if (xpBar == null) {
			xpBar = new GenericGradient(new Color(0.35F, 0.75F, 0.3F));
			xpBar.setHeight(3).setMargin(2, 1, 3, 1).setFixed(true);
			xpBar.setPriority(RenderPriority.Normal);
			xpBox.addChild(xpBar);
		}
		percent = (float) (percent * 100 * 1.8);// the 1.8 will get it to fit to the 180px wide bar. Simple.
		xpBar.setWidth((int) percent).setDirty(true);
		if (xpIcon == null) {
			xpIcon = new GenericTexture(skillSet.toString() + ".png");
			xpIcon.setWidth(8).setHeight(8).setFixed(true);
			xpIcon.setPriority(RenderPriority.Lowest);
			xpBox.addChild(xpIcon);
		} else {
			if (!xpIcon.getUrl().equals(skillSet.toString() + ".png")) {
				xpIcon.setUrl(skillSet.toString() + ".png").setDirty(true);
			}
		}
	}
}