package com.smallhk.concurrent.container;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memozier<A,V> implements Computable<A, V> {
	
	private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
	
	private final Computable<A,V> c;
	
	
	public Memozier(Computable<A,V> c){
		this.c = c;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public V compute(final A args) throws InterruptedException {
		while(true){
			
			Future<V> result = cache.get(args);
			
			if(null == result){
				Callable eval = new Callable<V>() {
					public V call() throws InterruptedException{
						return c.compute(args);
					}
				};
				
				FutureTask ft = new FutureTask<V>(eval);
				result = cache.putIfAbsent(args, ft);
				if(null == result){
					result = ft; 
					ft.run();
				}
			}
			
			try {
				return result.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
	}

}
