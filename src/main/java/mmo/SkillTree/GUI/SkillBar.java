package mmo.SkillTree.GUI;

import java.lang.reflect.Array;
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
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SkillBar {

	@SuppressWarnings("unused")
	private Player player;
	private MMOPlugin plugin;
	private GenericContainer skillBar;
        private GenericRectange GenericRectange[] = new GenericRectange[10];

	public SkillBar(MMOPlugin plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;

		skillBar = (GenericContainer) new GenericContainer()
				.setAnchor(WidgetAnchor.CENTER_RIGHT)
				.setLayout(ContainerType.VERTICAL)
                                .setPriority(RenderPriority.High)
				.setWidth(20)
				.setHeight(180)
                                .setX(-20)
                                .setY(-90);
		sPlayer.getMainScreen().attachWidget(plugin, skillBar);
                GenericRectange bg = new GenericRectange(new Color(0.5F, 0.5F, 0.5F));
				bg.setHeight(182)
				.setWidth(22)
                                .setX(-22)
                                .setY(-91)
                                .setAnchor( WidgetAnchor.CENTER_RIGHT )
				.setPriority(RenderPriority.Highest);
                sPlayer.getMainScreen().attachWidget(plugin, bg);
                
                GenericRectange[0] = new GenericRectange(new Color(1.0F, 0.5F, 0.5F));
                GenericRectange[0].setHeight(16)
                                    .setWidth(16)
                                    .setMargin(1)
                                    .setFixed(true);
                skillBar.addChild( GenericRectange[0] );
	}

}