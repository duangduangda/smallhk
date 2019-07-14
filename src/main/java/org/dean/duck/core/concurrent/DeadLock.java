package org.dean.duck.core.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/10
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class DeadLock {

    public static void main(String[] args) {
        InLock lock1 = new InLock(1);
        InLock lock2 = new InLock(2);
        Thread thread1 = new Thread(lock1);
        Thread thread2 = new Thread(lock2);
        thread1.start();
        thread2.start();
    }

}
class InLock implements Runnable{
    private ReentrantLock reentrantLock1 = new ReentrantLock();
    private ReentrantLock reentrantLock2 = new ReentrantLock();
    private int lock;

    public InLock(int lock){
        this.lock = lock;
    }

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
        if (lock == 1){
            while (true){
                if (reentrantLock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        if (reentrantLock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName() + "--done");
                            }finally {
                                if (reentrantLock2.isHeldByCurrentThread()){
                                    reentrantLock2.unlock();
                                }
                            }
                        }
                    }finally {
                        if (reentrantLock1.isHeldByCurrentThread()){
                            reentrantLock1.unlock();
                        }
                    }

                }
            }
        }else{
            while (true){
                if (reentrantLock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        if (reentrantLock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getName() + "--done");
                            }finally {
                                if (reentrantLock1.isHeldByCurrentThread()){
                                    reentrantLock1.unlock();
                                }
                            }
                        }
                    }finally {
                        if (reentrantLock2.isHeldByCurrentThread()){
                            reentrantLock2.unlock();
                        }
                    }

                }
            }
        }
    }
}
