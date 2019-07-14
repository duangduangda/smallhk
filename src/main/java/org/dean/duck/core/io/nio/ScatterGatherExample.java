package org.dean.duck.core.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/27
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ScatterGatherExample {
    public static void main(String[] args) {
        channel_scatter_read();
//        channel_gather_write();
    }

    private static void channel_gather_write() {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\channel_gather_write.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel()
        ){
            ByteBuffer header = ByteBuffer.allocate(10);
            header.put("123".getBytes());
            ByteBuffer body = ByteBuffer.allocate(10);
            body.put("456".getBytes());
            ByteBuffer[] byteBuffers = {header,body};
            fileChannel.write(byteBuffers);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void channel_scatter_read() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\nio_buffer.txt","rw")) {
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
            ByteBuffer[] byteBuffers = {byteBuffer1,byteBuffer2};
            fileChannel.read(byteBuffers);
            byteBuffer1.flip();
            System.out.println("开始读取buffer1的数据");
            while (byteBuffer1.hasRemaining()){
                System.out.print((char)byteBuffer1.get());
            }
            byteBuffer1.clear();
            System.out.println();
            byteBuffer2.flip();
            System.out.println("开始读取buffer2的数据");
            while (byteBuffer2.hasRemaining()){
                System.out.print((char)byteBuffer2.get());
            }
            byteBuffer2.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
