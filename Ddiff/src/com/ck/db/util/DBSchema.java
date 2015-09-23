package com.ck.db.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class DBSchema {
	private String name;
    private List<ColInfo> list = new ArrayList<>();
    private List<String> FKList = new ArrayList<>();
   
    public DBSchema (String name) {
    	this.name = name;
    }

    public void addCol(String name, String type, String modifier) {
            list.add(new ColInfo(name, type, modifier));           
    }
   
    public void addFK(String line) {
            FKList.add(line.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
		return this.getName().equals( ((DBSchema)o).getName() );
    }
    
    public int hashCode() {
        return new HashCodeBuilder(1, 1).append(this.name).toHashCode();
}
    
    public List<ColInfo> getCols() {
            return list;
    }

    public List<String> getFKList() {
            return FKList;
    }

	public String getName() {
		return name;
	}
}
