package com.smallhk.core.concurrent.forkjoin;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.junit.Test;

public class CalculateTaskTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool(10);
		Future<Integer> result = pool.submit(new CalculateTask(1, 100));
		assertEquals(5050, result.get().intValue());
	}

}
