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

public class SkillBar{

	private Player player;
	private MMOSkillTree plugin;
	private GenericContainer skillBar;
	private GenericTexture skillIcons[] = new GenericTexture[9];

	public SkillBar( MMOSkillTree plugin, Player player ){
		this.plugin = plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;

		skillBar = (GenericContainer) new GenericContainer().setAnchor( WidgetAnchor.CENTER_RIGHT ).setLayout( ContainerType.VERTICAL ).setPriority( RenderPriority.High ).setWidth( 20 ).setHeight( 180 ).setX( -20 ).setY( -90 );
		sPlayer.getMainScreen().attachWidget( plugin, skillBar );
		GenericTexture bg = new GenericTexture( "_skillBar.png" );
		bg.setHeight( 182 ).setWidth( 22 ).setX( -22 ).setY( -91 )
			.setAnchor( WidgetAnchor.CENTER_RIGHT ).setPriority( RenderPriority.Highest );
		sPlayer.getMainScreen().attachWidget( plugin, bg );

		for( int i = 0; i < skillIcons.length; i++ ){
			skillIcons[i] = new GenericTexture();
			skillIcons[i].setHeight( 16 ).setWidth( 16 ).setMargin( 2, 1 ).setFixed( true );
		}
		skillBar.addChildren( skillIcons );
	}

	public void setSkill( int pos, Skill skill ){
		skillIcons[pos].setUrl("Magic_Arrow.png").setDirty(true);
	}
}