package com.smallhk.core.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

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
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        final AtomicReference<Integer> atomicReference = new AtomicReference<Integer>();
            atomicReference.set(19);
        // producer
        for (int i = 0;i < 3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Integer m = atomicReference.get();
                        if (m < 20){
                            if (atomicReference.compareAndSet(m,m + 20)){
                                System.out.println("小于20，充值成功，余额为：" + atomicReference.get() + "元");
                            }else{
                                break;
                            }
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 100;i++){
                    while (true){
                        Integer m = atomicReference.get();
                        if (m > 10){
                            System.out.println("larger than 10");
                            if (atomicReference.compareAndSet(m, m - 10)){
                                System.out.println("成功消费10元，余额为：" + atomicReference.get() + "元");
                                break;
                            }
                        }else {
                            System.out.println("没有足够的余额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
