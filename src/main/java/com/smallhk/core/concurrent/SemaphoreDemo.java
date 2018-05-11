package com.smallhk.core.concurrent;

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
public class SemaphoreDemo implements Runnable{

    private static Semaphore semaphore = new Semaphore(5);
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
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " done!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(20, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0;i < 20;i++){
            service.submit(demo);
        }
    }
}
