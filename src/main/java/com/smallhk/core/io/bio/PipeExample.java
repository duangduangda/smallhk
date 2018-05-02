package com.smallhk.core.io.bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/2/8
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pipedOutputStream.write("Hello,world".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = pipedInputStream.read();
                    while (data != -1 && pipedInputStream != null){
                        System.out.print((char)data);
                        data = pipedInputStream.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread1.start();
        thread2.start();
    }
}
