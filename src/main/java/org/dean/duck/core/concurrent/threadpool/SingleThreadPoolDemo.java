package org.dean.duck.core.concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
public class SingleThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("SingleThreadPool-%d")
                .setDaemon(false)
                .build();
        ExecutorService service = new ThreadPoolExecutor(1,1,0,TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1), threadFactory,new ThreadPoolExecutor.AbortPolicy());
        for (int ii = 0;ii < 10;ii++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
                }
            });
        }
    }
}
