package com.smallhk.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: Guava事件总线：事件监听器
 * @author: dean
 * @create: 2019/06/09 10:08
 */
public class FirstEventListener {

    private static final Logger logger = LoggerFactory.getLogger(FirstEventListener.class);

    private String message;

    @Subscribe
    public void listen(SimpleEvent event) {
        this.message = event.getMessage();
        logger.info("First event listener get message:{}", event.getMessage());
    }

    public String getMessage() {
        return message;
    }
}
