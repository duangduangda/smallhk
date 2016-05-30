package com.smallhk.concurrent.container;

public interface Computable<A, V> {
	V compute(A args) throws InterruptedException;
}
