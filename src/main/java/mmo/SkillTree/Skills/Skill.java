package mmo.SkillTree.Skills;

import java.util.ArrayList;

public class Skill {
	
	public String name;
	public int row;
	public int col;
	public SkillType type;
	public ArrayList<String> parents = new ArrayList<String>();
	public ArrayList<String> children = new ArrayList<String>();
	
	public Skill(){
		
	}
	public Skill setName(String newName){
		name = newName;
		return this;
	}
	public String getName(){
		return name;
	}
	public Skill setRow(int newRow){
		row = newRow;
		return this;
	}
	public int getRow(){
		return row;
	}
	public Skill setCol(int newCol){
		col = newCol;
		return this;
	}
	public int getCol(){
		return col;
	}
	public Skill addParent(String newParent){
		parents.add(newParent);
		return this;
	}
	public String[] getParents(){
		return (String[]) parents.toArray();
	}
	public Skill addChild(String newChild){
		children.add(newChild);
		return this;
	}
	public String[] getChildren(){
		return children.toArray(new String[children.size()]);
	}
	public Skill setType(SkillType newType){
		type = newType;
		return this;
	}
	public SkillType getType(){
		return type;
	}
}
