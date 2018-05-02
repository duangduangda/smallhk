package com.smallhk.netty.in.action.future;

import java.util.concurrent.*;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class MyFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am task one");
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Integer(600);
            }
        };

        Future<?> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);
        System.out.println("task1 is completed?" + future1.isDone());
        System.out.println("task2 is completed?" + future2.isDone());
        while (future1.isDone()){
            System.out.println("task1 completed....");
            break;
        }

        while (future2.isDone()){
            System.out.println("return value key task2" + future2.get());
            break;
        }
    }
}
