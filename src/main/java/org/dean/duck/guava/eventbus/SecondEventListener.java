package org.dean.duck.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 事件监听器2
 * @author: dean
 * @create: 2019/06/09 11:00
 */
public class SecondEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SecondEventListener.class);

    private String message;

    @Subscribe
    public void listen(SimpleEvent event) {
        this.message = event.getMessage();
        logger.info("SecondEventListener get message:{}", event.getMessage());
    }
}
