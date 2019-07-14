package org.dean.duck.core.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TransferQueue;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/11
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private CyclicBarrier cyclicBarrier;

        Soldier(String soldier,CyclicBarrier cyclicBarrier){
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void  doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt()) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + " 任务完成");
        }
    }

    public static class BarrierAction implements Runnable{
        boolean flag;
        int N;

        public BarrierAction(boolean flag,int N){
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag){
                System.out.println("soldier " + N + "个完成任务");
            }else {
                System.out.println("soldied " + N + "个集合完毕");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final  int N = 10;
        Thread[] allSoider = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N,new BarrierAction(flag,N));
        System.out.println("集合队伍");
        for (int i = 0;i < N;i++){
            System.out.println("soldier " + i + "报道");
            allSoider[i] = new Thread(new Soldier("soldier" + i,cyclicBarrier));
            allSoider[i].start();
        }
    }

}
