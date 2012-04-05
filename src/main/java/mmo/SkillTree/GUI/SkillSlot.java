package mmo.SkillTree.GUI;

import mmo.SkillTree.Skills.SkillType;

import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;

public class SkillSlot extends GenericContainer {
	public String name;
	//static Container outter;
	public Container buttonStack;

	public SkillSlot(Container nbox) {
		this.setAlign(WidgetAnchor.CENTER_CENTER);
		nbox.addChild(this);

		buttonStack = new GenericContainer();
		buttonStack.setWidth(34).setHeight(34).setFixed(true);
		buttonStack.setLayout(ContainerType.OVERLAY);
		buttonStack.shiftXPos(buttonStack.getWidth() / -2);
		this.addChild(buttonStack);
	}

	public SkillSlot setName(String nName) {
		name = nName;
		return this;
	}

	public void sharedWidgets() {
		//This saves some copy paste. :/
		GenericTexture tex = new GenericTexture();
		tex.setUrl(name + ".png");
		tex.setMargin(9).setWidth(16).setHeight(16).setFixed(true);
		tex.setPriority(RenderPriority.Lowest);
		buttonStack.addChild(tex);

		Container upStack = new GenericContainer();
		upStack.setWidth(5).setHeight(5).setMargin(27, 0, 0, 2).setFixed(true);
		upStack.setLayout(ContainerType.OVERLAY);
		buttonStack.addChild(upStack);
		GenericTexture upButton = new GenericTexture();
		upButton.setWidth(5).setHeight(5).setFixed(true);
		upButton.setUrl("_skillMiniNormal.png");
		upButton.setPriority(RenderPriority.Low);
		upStack.addChild(upButton);
		GenericTexture up = new GenericTexture();
		up.setWidth(3).setHeight(3).setMargin(1).setFixed(true);
		up.setUrl("_skillMiniUp.png");
		up.setPriority(RenderPriority.Lowest);
		upStack.addChild(up);

		Container downStack = new GenericContainer();
		downStack.setWidth(5).setHeight(5).setMargin(27, 0, 0, 8).setFixed(true);
		downStack.setLayout(ContainerType.OVERLAY);
		buttonStack.addChild(downStack);
		GenericTexture downButton = new GenericTexture();
		downButton.setWidth(5).setHeight(5).setFixed(true);
		downButton.setUrl("_skillMiniNormal.png");
		downButton.setPriority(RenderPriority.Low);
		downStack.addChild(downButton);
		GenericTexture down = new GenericTexture();
		down.setWidth(3).setHeight(3).setMargin(1).setFixed(true);
		down.setUrl("_skillMiniDown.png");
		down.setPriority(RenderPriority.Lowest);
		downStack.addChild(down);

		Container lvlOutter = new GenericContainer();
		lvlOutter.setWidth(25).setHeight(27).setFixed(true);
		buttonStack.addChild(lvlOutter);
		GenericLabel lvl = new GenericLabel("10");
		lvl.setPriority(RenderPriority.Lowest);
		lvlOutter.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		lvlOutter.setAlign(WidgetAnchor.BOTTOM_RIGHT);
		lvl.setAnchor(WidgetAnchor.BOTTOM_RIGHT);
		lvl.setScale(0.65F);
		lvl.setFixed(true);
		lvlOutter.addChild(lvl);
	}

	public SkillSlot makePassive() {
		sharedWidgets();

		GenericTexture bgBevel = new GenericTexture();
		bgBevel.setUrl("_skillPassiveBgBevel.png");
		bgBevel.setPriority(RenderPriority.High);
		buttonStack.addChild(bgBevel);

		GenericTexture bg = new GenericTexture();
		bg.setUrl("_skillPassiveBg.png");
		bg.setPriority(RenderPriority.Normal);
		buttonStack.addChild(bg);

		GenericTexture button = new GenericTexture();
		button.setUrl("_skillPassiveNormal.png");
		button.setMargin(8).setWidth(18).setHeight(18).setFixed(true);
		button.setTooltip(name + " (Passive skill)");
		button.setPriority(RenderPriority.Low);
		buttonStack.addChild(button);

		return this;
	}

	public SkillSlot makeActive() {
		sharedWidgets();

		GenericTexture bgBevel = new GenericTexture();
		bgBevel.setUrl("_skillActiveBgBevel.png");
		bgBevel.setPriority(RenderPriority.High);
		buttonStack.addChild(bgBevel);

		GenericTexture bg = new GenericTexture();
		bg.setUrl("_skillActiveBg.png");
		bg.setPriority(RenderPriority.Normal);
		buttonStack.addChild(bg);

		GenericTexture button = new GenericTexture();
		button.setUrl("_skillActiveNormal.png");
		button.setMargin(8).setWidth(18).setHeight(18).setFixed(true);
		button.setTooltip(name + " (Active skill)");
		button.setPriority(RenderPriority.Low);
		buttonStack.addChild(button);

		return this;
	}

	public SkillSlot setType(SkillType type) {
		if (type == SkillType.ACTIVE) {
			this.makeActive();
		} else if (type == SkillType.PASSIVE) {
			this.makePassive();
		}
		return this;
	}

	public GenericTexture arrow() {
		GenericTexture arrow = new GenericTexture();
		arrow.setHeight(66).setWidth(126).setFixed(true);
		arrow.setMargin(5, -46, -37, -46);
		arrow.setPriority(RenderPriority.Normal);
		buttonStack.addChild(arrow);
		return arrow;
	}

	public GenericTexture arrowBevel() {
		GenericTexture arrowBevel = new GenericTexture();
		arrowBevel.setHeight(66).setWidth(126).setFixed(true);
		arrowBevel.setMargin(5, -46, -37, -46);
		arrowBevel.setPriority(RenderPriority.High);
		buttonStack.addChild(arrowBevel);
		return arrowBevel;
	}

	public SkillSlot arrowToR() {
		arrow().setUrl("_toR.png");
		arrowBevel().setUrl("_toRBevel.png");
		return this;
	}

	public SkillSlot arrowToLongR() {
		arrow().setUrl("_toLongR.png");
		arrowBevel().setUrl("_toLongRBevel.png");
		return this;
	}

	public SkillSlot arrowToL() {
		arrow().setUrl("_toL.png");
		arrowBevel().setUrl("_toLBevel.png");
		return this;
	}

	public SkillSlot arrowToLongL() {
		arrow().setUrl("_toLongL.png");
		arrowBevel().setUrl("_toLongLBevel.png");
		return this;
	}

	public SkillSlot arrowToB() {
		arrow().setUrl("_toB.png");
		arrowBevel().setUrl("_toBBevel.png");
		return this;
	}

	public SkillSlot arrowToLongB() {
		arrow().setUrl("_toLongB.png");
		arrowBevel().setUrl("_toLongBBevel.png");
		return this;
	}

	public SkillSlot arrowToBR() {
		arrow().setUrl("_toBR.png");
		arrowBevel().setUrl("_toBRBevel.png");
		return this;
	}

	public SkillSlot arrowToBL() {
		arrow().setUrl("_toBL.png");
		arrowBevel().setUrl("_toBLBevel.png");
		return this;
	}
}
