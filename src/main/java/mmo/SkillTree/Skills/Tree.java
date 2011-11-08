package mmo.SkillTree.Skills;

import java.util.Map.Entry;

import mmo.Core.MMOPlugin;
import mmo.SkillTree.MMOPlayer;
import mmo.SkillTree.Set;
import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.GUI.SkillSlot;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Tree {

	public Container box;
	public GenericLabel label;
	public GenericContainer infoBox;
	public GenericPopup skillTreePopup;
	public Sword swordSkills;
	static final int numRows = 5;
	static final int numCols = 4;
	public MMOPlugin plugin;
	public TreeSkill[][] skills = new TreeSkill[numRows][numCols];
	public SkillSlot[][] skillSlots = new SkillSlot[numRows][numCols];

	public Tree(MMOPlugin plugin, GenericPopup popup, SpoutPlayer sPlayer, SkillSet skillSet) {
		this.plugin = plugin;
		skillTreePopup = popup;
		Player p = (Player) sPlayer;
		//System.out.println("tree skillSet:"+skillSet);
		MMOPlayer mmoPlayer = MMOSkillTree.mmoPlayerManager.get(p);
		Set set = mmoPlayer.getSet(skillSet);

		label = new GenericLabel("You probably wish this did something, but it doesn't yet.");
		label.setAnchor(WidgetAnchor.TOP_CENTER);
		label.setWidth(168).setHeight(10).setX(-90).setY(10);
		label.setScale(0.7F);
		skillTreePopup.attachWidget(plugin, label);

		infoBox = new GenericContainer();
		infoBox.setLayout(ContainerType.VERTICAL);
		infoBox.setWidth(50).setHeight(230);
		infoBox.setAnchor(WidgetAnchor.TOP_CENTER);
		infoBox.setAlign(WidgetAnchor.TOP_CENTER);
		infoBox.setX(-150).setY(11);
		skillTreePopup.attachWidget(plugin, infoBox);
		GenericLabel infoLabel = new GenericLabel(set.string + " Skills\n\n"
				+ "Exp:" + set.getCurLvlXp() + "/" + set.getNextLvlXp() + "\n\n"
				+ "Skill Points:" + set.skillPoints);
		infoLabel.setScale(0.5F);
		infoLabel.setMargin(5);
		infoBox.addChild(infoLabel);

		box = new GenericContainer();
		box.setLayout(ContainerType.VERTICAL);
		box.setAnchor(WidgetAnchor.TOP_CENTER);
		box.setWidth(168).setHeight(210); //This is taller than it should be so the top skills align right to the top, and bottom ones to the bottom, instead of having a big gap
		box.setX(-84).setY(30);
		box.setPriority(RenderPriority.High);
		box.setAuto(true);

		swordSkills = new Sword();

		for (Entry<String, TreeSkill> entry : swordSkills.map.entrySet()) {
			//String key = entry.getKey();
			TreeSkill skill = entry.getValue();
			int row = skill.getRow();
			int col = skill.getCol();
			skills[row][col] = skill;
		}
		for (int row = 0; row < numRows; row++) {
			Container rowBox = new GenericContainer();
			rowBox.setLayout(ContainerType.HORIZONTAL);
			rowBox.setWidth(300).setHeight(38);
			for (int col = 0; col < numCols; col++) {
				if (skills[row][col] == null) {
					skills[row][col] = new TreeSkill();
				}
				TreeSkill skill = skills[row][col];
				skillSlots[row][col] = new SkillSlot(rowBox).setName(skill.getName()).setType(skill.getType());
			}
			box.addChild(rowBox);
		}
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				SkillSlot skillSlot = skillSlots[row][col];
				TreeSkill skill = skills[row][col];
				String[] children = skill.getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeSkill childSkill = swordSkills.getSkill(children[i]);
					//System.out.println(skill.getName() + " has child " + childSkill.getName());
					int childRow = childSkill.getRow();
					int childCol = childSkill.getCol();
					//System.out.println( "childRow:"+childRow + " row:"+row );
					//System.out.println( "childCol:"+childCol + " col:"+col );
					if (childCol == col && childRow == row + 1) {
						skillSlot.arrowToB();
					}
					if (childCol == col + 1 && childRow == row) {
						skillSlot.arrowToR();
					}
					if (childCol == col - 1 && childRow == row) {
						//System.out.println(skill.getName() +" to left to "+ childSkill.getName());
						skillSlot.arrowToL();
					}
					if (childCol == col + 1 && childRow == row + 1) {
						skillSlot.arrowToBR();
					}
					if (childCol == col - 1 && childRow == row + 1) {
						skillSlot.arrowToBL();
					}
					int botIterations = childRow - row;
					int rightIterations = childCol - col;
					if (botIterations > 1) {
						for (int ii = 0; ii <= botIterations; ii++) {
							//System.out.println("longL botIterations:"+botIterations + " ii:"+ii);
							SkillSlot lowerSkillSlot = skillSlots[ii][col];
							if (ii < botIterations) {
								lowerSkillSlot.arrowToLongB();
							} else {
								lowerSkillSlot.arrowToB();
							}
						}
					}
					if (rightIterations > 1) {
						for (int ii = 0; ii <= botIterations; ii++) {
							SkillSlot lowerSkillSlot = skillSlots[ii][col];
							if (ii < rightIterations) {
								lowerSkillSlot.arrowToLongR();
							} else {
								lowerSkillSlot.arrowToR();
							}
						}
					}
					if (rightIterations > -1) {
						for (int ii = 0; ii >= botIterations; ii--) {
							SkillSlot lowerSkillSlot = skillSlots[ii][col];
							if (ii > rightIterations) {
								lowerSkillSlot.arrowToLongL();
							} else {
								lowerSkillSlot.arrowToL();
							}
						}
					}

				}
			}
		}

		skillTreePopup.attachWidget(plugin, box);
	}

	public void close() {
		skillTreePopup.removeWidget(box);
		skillTreePopup.removeWidget(label);
		skillTreePopup.removeWidget(infoBox);
		skillTreePopup.setDirty(true);
	}
}
