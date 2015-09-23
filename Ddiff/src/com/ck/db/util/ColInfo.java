package com.ck.db.util;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class ColInfo {
    private String name;
    private String type;
    private String modifier;

    public ColInfo (String name, String type, String modifier) {
    	this.name = name;
    	this.type = type;
    	this.modifier = modifier;
    }

    public boolean equals(Object obj) {
    	ColInfo tar = (ColInfo)obj;
    	if (this.name.equals(tar.getName()) && this.type.equals(tar.getType()) && this.modifier.equals(tar.getModifier())) {
    		return true;
    	} else {
    		return false;
    	}
    }
   
    public int hashCode() {
    	return new HashCodeBuilder(1, 1).append(name).append(type).append(modifier).toHashCode();
    }
   
    public String getName() {
    	return name;
    }

    public String getType() {
    	return type;
    }

    public String getModifier() {
    	return modifier;
    }
}
