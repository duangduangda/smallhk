package com.smallhk.guava.concurrent;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;

/**
 * @description: 异步调用
 * @author: dean
 * @create: 2019/06/10 19:43
 */
public class ListenableFutureHandler {

    final static ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ListenableFuture<String> stringTask = executorService.submit(() -> "hello");
        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String value) {
                System.out.println("Received value:" + value);
            }

            @Override
            public void onFailure(Throwable throwable) {
            }
        });
        System.out.println("Execute time:" + (System.currentTimeMillis() - start) + "ms");
        executorService.shutdown();
    }
}
