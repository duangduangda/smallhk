package com.smallhk.core.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

import jdk.nashorn.internal.ir.annotations.Immutable;

public class CASNumberRange {
	@Immutable
	private static class IntPair {
		final int lower;
		final int upper;

		public IntPair(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}

	private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));

	public int getLower() {
		return values.get().lower;
	}

	public int getUpper() {
		return values.get().upper;
	}

	public void setLower(int newLower) {
		while (true) {
			IntPair oldv = values.get();
			if (newLower > oldv.upper) {
				throw new IllegalArgumentException("can not set lower to " + newLower + "> upper");
			}
			IntPair newv = new IntPair(newLower, oldv.upper);
			if (values.compareAndSet(oldv, newv)) {
				return;
			}
		}
	}
	
	public void setUpper(int newUpper){
		while(true){
			IntPair oldv = values.get();
			if(newUpper < oldv.lower){
				throw new IllegalArgumentException("can not set upper to " + newUpper + " < lower");
			}
			
			IntPair newv = new IntPair(oldv.lower, newUpper);
			if(values.compareAndSet(oldv, newv)){
				return;
			}
		}
	}
}
