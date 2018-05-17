package com.smallhk.core.concurrent.produceconsume;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

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
public class Consumer implements Runnable {
    private BlockingQueue<TData> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " put data");
        Random random = new Random();
        try {
            while (true) {
                TData tData = queue.take();
                if (null != tData) {
                    int ret = tData.getData() * tData.getData();
                    System.out.println(MessageFormat.format("{0} * {1} = {2}", tData.getData(), tData.getData(), ret));
                    Thread.sleep(random.nextInt(1000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }
    }

}
