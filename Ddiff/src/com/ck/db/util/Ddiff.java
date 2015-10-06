package com.ck.db.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Ddiff {
    private String db1;
    private String db2;

    public Ddiff(String input, String input2) {
        this.db1 = input;
        this.db2 = input2;
    }
    
    public boolean findDiff() {
    	boolean result = false;
    	List<DBSchema> schemaOfdb1 = getSchemas(this.db1);
    	List<DBSchema> schemaOfdb2 = getSchemas(this.db2);

    	Collection<DBSchema> diffDB = SetOperator.diffCompared(schemaOfdb1, schemaOfdb2);
    	for(DBSchema db : diffDB) {
    		System.out.println("Diff in table > name: "+db.getName());
    		result = true;
    	}
    	
    	for(DBSchema db : SetOperator.getIntersection(schemaOfdb1, schemaOfdb2)) {
    		int index1 = schemaOfdb1.indexOf(db);
    		int index2 = schemaOfdb2.indexOf(db);
    		for ( ColInfo col : SetOperator.diffCompared(schemaOfdb1.get(index1).getCols(), schemaOfdb2.get(index2).getCols())) {
    			System.out.println("Diff in Col > table: "+db.getName()+", column name: "+col.getName()+" , column type: "+col.getType());
    			result = true;
    		}
    		for ( String fk : SetOperator.diffCompared(schemaOfdb1.get(index1).getFKList(), schemaOfdb2.get(index2).getFKList())) {
    			System.out.println("Diff in FK > table: "+db.getName()+", FK name: "+fk);
    		}
    	}
    	return result;
    }
    
    private List<DBSchema> getSchemas(String input) {
    	List<DBSchema> all = new ArrayList<>();
        try {
        	DBSchema schema = null;
        	boolean canRead = true;
        	boolean canReadFK = false;
        	for(String line : Files.readAllLines(new File(input).toPath(), Charset.defaultCharset())) {
        		line = line.trim();
        		if (line.startsWith("Table")) {
        			if (schema != null) {
        				all.add(schema);
        			}
        			line = line.replace("Table \"public.", "").replace("\"", "");
        			schema = new DBSchema(line);
        			canRead = true;
        			canReadFK = false;
        			continue;
        		} else if (line.startsWith("Sequence") || line.startsWith("Index") || line.trim().startsWith("Referenced by:")) {
        			canRead = false;
        			canReadFK = false;
        			continue;
        		} else if(canRead) {
        			String[] cols = line.split("\\|");
        			if (!cols[0].trim().equals("Column")) {
        				if (cols.length == 3) {
        					schema.addCol(cols[0].trim(), cols[1].trim(), cols[2].trim());
        				} else if (cols.length == 2) {
        					schema.addCol(cols[0].trim(), cols[1].trim(), "");
        				}
        			}
        		} else if(canReadFK && !line.trim().equals("")) {
        			schema.addFK(line.trim());
        		} else if (line.startsWith("Foreign-key constraints:")) {
        			canReadFK = true;
        			continue;
        		}
        	}
        	
			if (schema != null) {
				all.add(schema);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all;
    }
}
