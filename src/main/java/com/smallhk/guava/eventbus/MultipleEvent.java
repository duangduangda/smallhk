package com.smallhk.guava.eventbus;

import com.google.common.base.MoreObjects;

/**
 * @description: 多重事件
 * @author: dean
 * @create: 2019/06/09 11:05
 */
public class MultipleEvent {
    private String message;

    public MultipleEvent(String message) {
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
