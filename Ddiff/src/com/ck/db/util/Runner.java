package com.ck.db.util;

public class Runner {

    public static void main(String[] args) {
    	if (args.length < 2) {
    		System.out.println("Insufficient arguments !!!");
    		return;
    	}
        Ddiff compare = new Ddiff(args[0], args[1]);
        compare.findDiff();
    }
}
