package org.dean.duck.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 多重事件监听器
 * @author: dean
 * @create: 2019/06/09 11:21
 */
public class MultipleEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MultipleEventListener.class);


    /**
     * 监听订阅simple event
     *
     * @param event
     */
    @Subscribe
    public void listen(SimpleEvent event) {
        logger.info("Received simple event message:{}", event.getMessage());
    }

    /**
     * 监听订阅multiple event
     *
     * @param event
     */
    @Subscribe
    public void listen(MultipleEvent event) {
        logger.info("Received multiple event message:{}", event.getMessage());
    }
}
