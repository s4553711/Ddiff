package com.ck.db.util;

public class Runner {

    public static void main(String[] args) {
        Ddiff compare = new Ddiff("fileA", "fileB");
        compare.findDiff();
    }
}
