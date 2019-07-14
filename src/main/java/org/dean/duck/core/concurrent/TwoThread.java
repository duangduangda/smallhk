package org.dean.duck.core.concurrent;

/**
 * @description: 两个线程交替打印
 * @author: dean
 * @create: 2019/07/08 20:29
 */
public class TwoThread {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!flag) {
                        synchronized (this) {
                            System.out.println(Thread.currentThread().getName());
                            flag = true;
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        synchronized (this) {
                            System.out.println(Thread.currentThread().getName());
                            flag = false;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
