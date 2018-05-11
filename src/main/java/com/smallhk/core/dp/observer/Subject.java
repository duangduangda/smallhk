package com.smallhk.core.dp.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>(5);

    /**
     * 注册观察者
     * @param observer
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * 取消注册
     * @param observer
     */
    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * 通知所有的.拉模式
     */
    protected void notifyObservers(){
        for (Observer reader:observers){
            reader.update(this);
        }
    }
    //推模式
    protected void notifyObservers(String content){
        for (Observer reader:observers){
            reader.update(content);
        }
    }




}
