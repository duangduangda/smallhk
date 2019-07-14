package org.dean.duck.guava.eventbus;

import com.google.common.base.MoreObjects;

/**
 * @description: Guava事件总线EventBus：简单测试事件
 * @author: dean
 * @create: 2019/06/09 10:06
 */
public class SimpleEvent {

    private final String message;

    public SimpleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", message)
                .toString();
    }
}
