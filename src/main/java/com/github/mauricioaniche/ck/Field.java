package com.github.mauricioaniche.ck;


import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Field {
	private Map<String, Integer> fieldUsage;

	public void setFieldUsage(Map<String, Integer> fieldUsage) {
		this.fieldUsage = fieldUsage;
	}

	public Map<String, Integer> getFieldUsage() {
		if (this.fieldUsage == null)
			fieldUsage = new HashMap<>();
		return fieldUsage;
	}

	public Set<String> getFieldsAccessed() {
		if (this.fieldUsage == null)
			fieldUsage = new HashMap<>();
		return fieldUsage.keySet();
	}
}