package com.smallhk.concurrent.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memozier2<A, V> implements Computable<A, V> {

	private final Map<A,V> cache = new ConcurrentHashMap<A,V>();
	
	private final Computable<A, V> c;

	public Memozier2(Computable<A, V> c) {
		this.c = c;
	}

	public V compute(A args) throws InterruptedException {
		V result = cache.get(args);
		if(result == null){
			result = c.compute(args);
			cache.put(args,result);
		}
		return result;
	}
}
