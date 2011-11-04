package mmo.skills.mmoSkillTree.GUI;

import mmo.skills.mmoSkillTree.SkillTree;
import mmo.skills.mmoSkillTree.Skills.SkillSet;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericRectange;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MMOXpBar {
	
	@SuppressWarnings("unused")
	private Player player;
	private SkillTree plugin;
	private GenericContainer xpBox;
	private GenericRectange xpBar;
	private GenericTexture xpIcon;
	public MMOXpBar(Player player){
		plugin = SkillTree.plugin;
		this.player = player;
		SpoutPlayer sPlayer = (SpoutPlayer) player;
		
		xpBox = new GenericContainer();
		xpBox.setAnchor(WidgetAnchor.TOP_CENTER).setLayout(ContainerType.OVERLAY).setPriority(RenderPriority.Normal);
		xpBox.setWidth(182).setHeight(8).setX(-91).setY(20);
		sPlayer.getMainScreen().attachWidget(plugin, xpBox);
		
		GenericTexture xpBarBg = new GenericTexture("_xpBarBg.png");
		xpBarBg.setHeight(5).setWidth(182).setMargin(1,1,2,1);
		xpBarBg.setPriority(RenderPriority.Highest);
		xpBox.addChild(xpBarBg);
		GenericTexture xpBarTexture = new GenericTexture("_xpBar.png");
		xpBarTexture.setHeight(5).setWidth(182).setMargin(1,1,2,1);
		xpBarTexture.setPriority(RenderPriority.Low);
		xpBox.addChild(xpBarTexture);
		
	}
	
	public void draw(float percent, SkillSet skillSet){
		if( xpBar == null){
			xpBar = new GenericRectange(new Color(0.35F, 0.75F, 0.3F));
			xpBar.setHeight(3).setMargin(2,1,3,1).setFixed(true);
			xpBar.setPriority(RenderPriority.High);
			xpBox.addChild(xpBar);
		}
		percent = (float) (percent*100*1.8);// the 1.8 will get it to fit to the 180px wide bar. Simple.
		xpBar.setWidth((int)percent).setDirty(true); 
		if( xpIcon == null ){
			xpIcon = new GenericTexture(skillSet.toString() + ".png");
			xpIcon.setWidth(8).setHeight(8).setFixed(true);
			xpIcon.setPriority(RenderPriority.Lowest);
			xpBox.addChild(xpIcon);
		}
		else{
			if( xpIcon.getUrl() != (skillSet.toString() + ".png") ){
				xpIcon.setUrl(skillSet.toString() + ".png").setDirty(true);
			}
		}
	}
	
}
