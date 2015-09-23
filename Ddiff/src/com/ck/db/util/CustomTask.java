package com.ck.db.util;

import org.apache.tools.ant.BuildException;

public class CustomTask {
	
	private String schemaA;
	private String schemaB;
	private boolean quiet = false;

	public void setSchemaA(String name) {
		this.schemaA = name;
	}
	
	public void setSchemaB(String name) {
		this.schemaB = name;
	}
	
	public void setQuiet(boolean bool) {
		this.quiet  = bool;
	}
	
	public void execute() {
		System.out.println("Hello Custom");
		Ddiff compare = new Ddiff(this.schemaA, this.schemaB);
        boolean findDiff= compare.findDiff();
        if (!quiet && findDiff) {
        	throw new BuildException("Find difference of these two databases.");
        }
	}

}
