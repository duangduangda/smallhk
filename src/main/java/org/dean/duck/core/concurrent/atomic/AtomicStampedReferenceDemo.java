package org.dean.duck.core.concurrent.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

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
public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19,0);

    public static void main(String[] args) {
        for (int i = 0;i < 3;i++){
            final int timestamp = money.getStamp();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Integer m = money.getReference();
                        if (m < 20){
                            if (money.compareAndSet(m,m + 20,timestamp, timestamp + 1)){
                                System.out.println("小于20，充值成功，余额为：" + money.getReference() + "元");
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
                for(int i = 0;i < 10;i++){
                    int timestamp = money.getStamp();
                    while (true){
                        Integer m = money.getReference();
                        if (m > 10){
                            if (money.compareAndSet(m, m - 10,timestamp, timestamp + 1)){
                                System.out.println("成功消费10元，余额为：" + money.getReference() + "元");
                                break;
                            }
                        }else{
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
