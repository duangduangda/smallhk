package com.smallhk.core.concurrent.produceconsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
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
public class Producer implements Runnable {

    private BlockingQueue<TData> queue;
    private volatile boolean isRunning = true;
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue queue){
        this.queue = queue;
    }

    public void stop(){
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(1000);
                TData tData = new TData(count.incrementAndGet());
                System.out.println(Thread.currentThread().getName() + "put data:" + tData);
                if (!queue.offer(tData,2, TimeUnit.SECONDS)){
                    System.err.println("failed to put data:" + tData);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
