package com.smallhk.core.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/11
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch countDownLatch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 10;i++){
            executorService.submit(demo);
        }
         countDownLatch.await();
        System.out.println("Fire!");
        executorService.shutdown();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try{
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getName() + " check complete");
            countDownLatch.countDown();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
