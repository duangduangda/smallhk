package com.smallhk.concurrent.container;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memozier3<A, V> implements Computable<A, V> {

	private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
	
	private final Computable<A, V> c;

	public Memozier3(Computable<A, V> c) {
		this.c = c;
	}

	public V compute(final A args) throws InterruptedException {
		Future<V> result = cache.get(args);
		if(result == null){
			Callable<V> eval = new Callable<V>() {
				public V call() throws InterruptedException{
					return c.compute(args);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(eval);
			result = ft;
			cache.put(args,ft);
			ft.run();
		}
		try {
			return result.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
