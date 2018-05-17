package com.smallhk.core.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/15
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static class AddTask implements Runnable{

        @Override
        public void run() {
            for (int i = 0;i < 10000;i++){
                atomicIntegerArray.getAndIncrement(i % atomicIntegerArray.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0;i < 10;i++){
            threads[i] = new Thread(new AddTask());
        }

        for (int i = 0;i < 10;i++){
            threads[i].start();
        }

        for (int i = 0;i < 10;i++){
            threads[i].join();
        }

        System.out.println(atomicIntegerArray);
    }
}
