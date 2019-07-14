package org.dean.duck.core.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/11
 * <p>
 * Company:
 * <p>
 *
 * @Author:
 * <p>
 * Version: 1.0
 * <p>
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private int value;

    public int handleReadLock(Lock lock) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWriteLock(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        Runnable readRunnale = new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLockDemo.handleReadLock(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int value = new Random(100).nextInt();
                    readWriteLockDemo.handleWriteLock(writeLock,value );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        for (int i = 18;i < 20;i++ ){
            new Thread(writeRunnable).start();
        }

        for (int i = 0;i < 18;i++){
            new Thread(readRunnale).start();
        }
    }

}
