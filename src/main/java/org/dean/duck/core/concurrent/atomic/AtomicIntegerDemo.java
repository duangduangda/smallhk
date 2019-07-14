package org.dean.duck.core.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

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
public class AtomicIntegerDemo {
    static AtomicInteger atomicInteger = new AtomicInteger();
    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0;i < 10000;i++){
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0;i < 10;i++){
            threads[i] = new Thread(new AddThread());
        }

        for (int i = 0;i < 10;i++){
            threads[i].start();
        }

        for (int i = 0;i < 10;i++){
            threads[i].join();
        }

        System.out.println(atomicInteger);
    }
}
