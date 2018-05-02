package com.smallhk.core.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/28
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class DatagramChannelExample {
    public static void main(String[] args) throws IOException {
        channel_to_received();
        channel_to_send();
    }

    private static void channel_to_send() {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.socket().bind(new  InetSocketAddress(80));
            String newData = "12321321";
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            byteBuffer.clear();
            byteBuffer.put(newData.getBytes());
            byteBuffer.flip();
            int bytesSent = datagramChannel.send(byteBuffer,new  InetSocketAddress("http://jenkov.com", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void channel_to_received() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress("http://jenkov.com", 80));
        //接收数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.clear();
        // 将接收到的数据包复制到指定的buffer中
        datagramChannel.receive(byteBuffer);
        datagramChannel.close();
    }
}
