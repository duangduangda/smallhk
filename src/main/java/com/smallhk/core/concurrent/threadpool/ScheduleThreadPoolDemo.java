package com.smallhk.core.concurrent.threadpool;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
            }
        },2000, 30000,TimeUnit.MILLISECONDS);
        service.shutdown();
    }
}
