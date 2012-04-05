package mmo.SkillTree.Skills;

import java.util.ArrayList;
import java.util.List;

public enum SkillType {
	BLANK,
	ACTIVE,
	PASSIVE;
	static final List<String> list = new ArrayList<String>();

	public static boolean contains(String name) {
		return list.contains(name);
	}

	public static SkillType getFromString(String name) {
		if (name != null) {
			for (SkillType t : values()) {
				if (t.name().equals(name)) {
					return t;
				}
			}
		}

		return null;
	}

	static {
		for (SkillType t : values()) {
			list.add(t.name());
		}
	}
}