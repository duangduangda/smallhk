package com.smallhk.core.dp.observer;

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
public class Client {
    public static void main(String[] args) {
//      diyCaller();
        jdkCaller();
    }

    private static void jdkCaller() {
        NewsPaper newsPaper = new NewsPaper();
        Reader reader1 = new Reader("eric");
        Reader reader2 = new Reader("duang");
        newsPaper.addObserver(reader1);
        newsPaper.addObserver(reader2);
        newsPaper.setContent("This is jdk caller ");
    }

    private static void diyCaller() {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver observer1 = new ConcreteObserver();
        observer1.setName("eric");

        ConcreteObserver observer2 = new ConcreteObserver();
        observer2.setName("duang");

        //订阅
        subject.attach(observer1);
        subject.attach(observer2);

        subject.setContent("this is observer pattern");
    }
}
