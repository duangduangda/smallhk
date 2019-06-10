package com.smallhk.guava.eventbus;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description: 事件监听线程
 * @author: dean
 * @create: 2019/06/09 12:38
 */
public class EventListenerThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(EventListenerThread.class);

    private Socket connection;

    private EventBus channel;

    private BufferedReader in;

    private PrintWriter out;

    public EventListenerThread(Socket connection, EventBus channel) {
        this.connection = connection;
        this.channel = channel;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charsets.UTF_8));
            out = new PrintWriter(connection.getOutputStream(), true);
        } catch (IOException e) {
            Throwables.propagate(e);
            System.exit(-1);
        }
    }

    @Subscribe
    public void listen(String message) {
        if (null != this.out) {
            this.out.println(message);
            logger.info("Received message:{}", message);
        }
    }

    @Override
    public void run() {
        String input;
        try {
            while ((input = in.readLine()) != null) {
                this.channel.post(input);
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        } finally {
            this.channel.unregister(this);
        }

        try {
            this.connection.close();
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        this.in = null;
        this.out = null;
    }


}
