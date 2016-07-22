package com.smallhk.core.concurrent.container;

import java.util.HashMap;
import java.util.Map;

public class Memozier1<A, V> implements Computable<A, V> {

	@GuardedBy("this")
	private final Map<A, V> cache = new HashMap<A, V>();

	private final Computable<A, V> c;

	public Memozier1(Computable<A, V> c) {
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
