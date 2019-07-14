package org.dean.duck.core.concurrent;

import java.util.concurrent.locks.ReentrantLock;

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
public class FairLockDemo implements Runnable{
    public static ReentrantLock fairLock = new ReentrantLock(true);


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
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " get the lock");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLockDemo fairLockDemo = new FairLockDemo();
        Thread thread1 = new Thread(fairLockDemo,"Thread_1");
        Thread thread2 = new Thread(fairLockDemo,"Thread_2");
        thread1.start();
        thread2.start();

    }
}
