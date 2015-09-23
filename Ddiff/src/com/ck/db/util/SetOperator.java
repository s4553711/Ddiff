package com.ck.db.util;

import java.util.Collection;
import java.util.HashSet;

public class SetOperator {
	
    public SetOperator() {}

	public static <T> Collection<T> getIntersection(Collection<T> setA, Collection<T> setB) {
		Collection<T> similar = new HashSet<T>(setA);
		similar.retainAll(setB);
		return similar;
	}
    
    public static <T> Collection<T> diffCompared(Collection<T> setA, Collection<T> setB) {
		Collection<T> diff = new HashSet<T>();
		diff.addAll(setA);
		diff.addAll(setB);
		//System.out.println("original diff size "+diff.size());
		//System.out.println("intersection size "+getIntersection(setA, setB).size());
		diff.removeAll(getIntersection(setA, setB));
		//System.out.println("diff size "+diff.size());
		return diff;    	
    }
}
