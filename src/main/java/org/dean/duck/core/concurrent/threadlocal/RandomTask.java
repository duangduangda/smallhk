package org.dean.duck.core.concurrent.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

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
public class RandomTask implements Callable<Long> {
    public static final int GEN_COUNT = 10000000;
    public static final int THREAD_COUNT = 4;
    static ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("random-thread-%d").build();
    static ExecutorService service = new ThreadPoolExecutor(
            THREAD_COUNT,THREAD_COUNT,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            threadFactory);
    public static Random random = new Random(123);
    public static ThreadLocal<Random> randomThreadLocal = new ThreadLocal<Random>(){
      @Override
        protected Random initialValue(){
          return new Random(123);
      }
    };

    private  int mode = 0;

    public RandomTask(int mode){
        this.mode = mode;
    }

    public Random getRandom(){
        if (mode == 0){
            return random;
        }else if (mode == 1){
            return randomThreadLocal.get();
        }else{
            return null;
        }

    }

    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0;i < GEN_COUNT;i++){
            getRandom().nextInt();
        }
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " spend " + (end - start) + "ms");
        return end - start;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futures = new Future[THREAD_COUNT];
        for (int i = 0;i < THREAD_COUNT;i++){
            futures[i] = service.submit(new RandomTask(0));
        }
        long totalTime = 0;
        for (int i = 0;i < THREAD_COUNT;i++){
            totalTime += futures[i].get();
        }

        System.out.println("多线程访问一个random实例，耗时：" + totalTime + "ms");


        //ThreadLocal
        for (int i = 0;i < THREAD_COUNT;i++){
            futures[i] = service.submit(new RandomTask(1));
        }
        totalTime = 0;
        for (int i = 0;i < THREAD_COUNT;i++){
            totalTime += futures[i].get();
        }
        System.out.println("使用ThreadLocal包装Random实例" + totalTime + "ms");
    }
}
