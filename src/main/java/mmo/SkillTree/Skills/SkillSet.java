package mmo.SkillTree.Skills;

import java.util.ArrayList;
import java.util.List;

public enum SkillSet
{
	Bashing,
	Bow,
	Empathy,
	Magic,
	Slashing,
	Smithing,
	Piercing,
	Tool,
	Unarmed;
	
	static final List<String> list = new ArrayList<String>();

	/*public static boolean contains(String name) {
    	return list.contains(name);
	}*/

	public static SkillSet getFromString(String name) {
		if(name != null)
			for(SkillSet t : values())
				if(t.name().equals(name))
					return t;

		return null;
	}

	static {
		for(SkillSet t : values())
			list.add(t.name());
	}
}