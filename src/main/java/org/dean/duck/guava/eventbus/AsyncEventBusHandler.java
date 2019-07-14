package org.dean.duck.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

/**
 * @description: 异步事件总线, 消费事件队列采取线程池的方式
 * @author: dean
 * @create: 2019/06/09 20:51
 */
public class AsyncEventBusHandler {
    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        eventBus.register(new MultipleEventListener());
        eventBus.post(new SimpleEvent("This is a simple event"));
        eventBus.post(new MultipleEvent("This is a multiple event"));
        eventBus.post(new SubMultipleEvent("This is a sub multiple event"));
    }
}
