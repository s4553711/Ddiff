package com.ck.db.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ddiff {
    private String schema1;
    private String schema2;

    public Ddiff(String input, String input2) {
        this.schema1 = input;
        this.schema2 = input2;
    }
    
    public void findDiff() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.schema1));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(">>>> "+line);
                if (!line.startsWith("<") && !line.startsWith(">") && !line.startsWith("---")) {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
