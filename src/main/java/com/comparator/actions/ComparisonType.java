/**
 * 
 */
package com.comparator.actions;

/**
 * @author misirghi
 *
 */
public enum ComparisonType {
	byTemplate("template"),
	byLines("lines"),
	byPages("pages"),
	genTemplate("generate_template");			

	private String text;

	ComparisonType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static ComparisonType fromString(String text) {			
		if (text != null) 
		{
			for (ComparisonType b : ComparisonType.values()) 
			{
				if (text.equalsIgnoreCase(b.text)) 
				{
					return b;
				}
			}
		}
		return null;
	}
}
