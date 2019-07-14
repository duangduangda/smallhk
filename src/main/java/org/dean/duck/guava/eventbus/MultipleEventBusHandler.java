package org.dean.duck.guava.eventbus;

import com.google.common.eventbus.EventBus;


/**
 * @description: 多重事件总线
 * 1. 如果监听器A监听事件1，而事件1有有个子事件2，那么监听器A将同时收到事件1和事件2的消息
 * 2. 可以根据事件的类型不同，实现不一样的订阅方法
 * @author: dean
 * @create: 2019/06/09 11:11
 */
public class MultipleEventBusHandler {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("MultipleEventBus");
        eventBus.register(new MultipleEventListener());

        eventBus.post(new SimpleEvent("This is a simple event"));
        eventBus.post(new MultipleEvent("This is a multiple event"));
        eventBus.post(new SubMultipleEvent("This is a sub multiple event"));


    }
}
