package org.dean.duck.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @description: 简易事件总线测试
 * @author: dean
 * @create: 2019/06/09 10:15
 */
public class SimpleEventBusHandler {
    public static void main(String[] args) {
        // 定义EventBus
        EventBus eventBus = new EventBus("simpleEventBusHandler");

        // 定义监听器
        FirstEventListener firstEventListener = new FirstEventListener();
        SecondEventListener secondEventListener = new SecondEventListener();


        // 注册监听器
        eventBus.register(firstEventListener);
        eventBus.register(secondEventListener);

        // 发布事件
        eventBus.post(new SimpleEvent("This is the first test event"));
        eventBus.post(new SimpleEvent("This is the second test event"));
        eventBus.post(new SimpleEvent("This is the third test event"));
        eventBus.post(new SimpleEvent("This is the fourth test event"));
        eventBus.post(new SimpleEvent("This is the last event"));


    }
}
