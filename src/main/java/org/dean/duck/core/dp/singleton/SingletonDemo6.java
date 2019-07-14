package org.dean.duck.core.dp.singleton;

/**
 * Title. <br>
 * Description. 双重检查锁定，使用volatile变量来禁止指令重排序
 * <p>
 * Copyright: Copyright (c) 2018/5/3
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SingletonDemo6 {

    private volatile static SingletonDemo6 instance = null;

    private SingletonDemo6(){

    }

    public static SingletonDemo6 getInstance(){
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (null == instance){
            //同步块，线程安全的创建实例
            synchronized (SingletonDemo6.class){
                //再次检查实例是否存在，如果不存在才真的创建实例
                if (null == instance){
                    instance = new SingletonDemo6();
                }
            }
        }
        return instance;
    }

}
