package mmo.SkillTree.GUI;

import mmo.Core.MMOPlugin;
import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericRectange;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillBar {

	@SuppressWarnings("unused")
	private Player player;
	private MMOSkillTree plugin;
	private GenericContainer xpBox;
	private GenericRectange xpBar;

	public SkillBar(MMOSkillTree plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;

		xpBox = (GenericContainer) new GenericContainer(
				new GenericTexture("_skillBar.png")
					.setHeight(184)
					.setWidth(23)
					.setMargin(1, 0, 1, 1)
					.setPriority(RenderPriority.Highest)
				)
				.setAnchor(WidgetAnchor.TOP_CENTER)
				.setLayout(ContainerType.OVERLAY)
				.setWidth(182)
				.setHeight(8)
				.setX(-91)
				.setY(20);
		sPlayer.getMainScreen().attachWidget(this.plugin, xpBox);
	}

    SkillBar( MMOPlugin plugin, Player player ){
        throw new UnsupportedOperationException( "Not yet implemented" );
    }

	public void draw(float percent, SkillSet skillSet) {
		if (xpBar == null) {
			xpBar = new GenericRectange(new Color(0.35F, 0.75F, 0.3F));
			xpBar.setHeight(3).setMargin(2, 1, 3, 1).setFixed(true);
			xpBar.setPriority(RenderPriority.Normal);
			xpBox.addChild(xpBar);
		}
	}
}