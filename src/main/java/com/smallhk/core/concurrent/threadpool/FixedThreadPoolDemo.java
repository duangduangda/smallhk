package com.smallhk.core.concurrent.threadpool;

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
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0;i < 20;i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}
