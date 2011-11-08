package mmo.SkillTree.Skills;

import java.util.ArrayList;

public class TreeSkill {
	
	public String name;
	public int row;
	public int col;
	public SkillType type;
	public ArrayList<String> parents = new ArrayList<String>();
	public ArrayList<String> children = new ArrayList<String>();
	
	public TreeSkill(){
		
	}
        
        public void onEvent(){
            
        }
        
	public TreeSkill setName(String newName){
		name = newName;
		return this;
	}
	public String getName(){
		return name;
	}
	public TreeSkill setRow(int newRow){
		row = newRow;
		return this;
	}
	public int getRow(){
		return row;
	}
	public TreeSkill setCol(int newCol){
		col = newCol;
		return this;
	}
	public int getCol(){
		return col;
	}
	public TreeSkill addParent(String newParent){
		parents.add(newParent);
		return this;
	}
	public String[] getParents(){
		return (String[]) parents.toArray();
	}
	public TreeSkill addChild(String newChild){
		children.add(newChild);
		return this;
	}
	public String[] getChildren(){
		return children.toArray(new String[children.size()]);
	}
	public TreeSkill setType(SkillType newType){
		type = newType;
		return this;
	}
	public SkillType getType(){
		return type;
	}
}
