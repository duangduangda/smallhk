package org.dean.duck.core.dp.observer;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company: eric
 * <p>
 *
 * @Author:
 * <p>
 * Version: 1.0
 * <p>
 */
public class ConcreteObserver implements Observer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject){
        // 拉模式
        System.out.println(name + "收到报纸，阅读它，内容是=============" + ((ConcreteSubject)subject).getContent());
    }

    // 推模式
    @Override
    public void update(String content){
        System.out.println(name + "收到报纸，阅读它，内容是=============" + content);
    }
}
