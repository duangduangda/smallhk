package com.smallhk.core.io.nio;

import javafx.scene.control.RadioButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Title. <br>
 * Description. 通道之间的数据传输。
 * 在Java NIO中，如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。
 * <p>
 * Copyright: Copyright (c) 2018/2/28
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class FileChannelExample {
    public static void main(String[] args) {
//        file_channel_tranfer_from();
//        file_channel_tranfer_to();
//          file_channel_truncate();
          file_channel_force();
    }

    private static void file_channel_force() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\file_channel_force.txt","rw")){
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            byteBuffer.put("abc".getBytes());
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileChannel.force(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void file_channel_truncate() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\channel2File.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
        ){
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            fileChannel.truncate(10);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }
            System.out.println();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void file_channel_tranfer_to() {
        try (
                RandomAccessFile toFile = new RandomAccessFile("E:\\channel2File.txt", "rw");
                RandomAccessFile fromFile = new RandomAccessFile("E:\\nio_buffer.txt", "rw");
                FileChannel toChannel = toFile.getChannel();
                FileChannel fromChannel = fromFile.getChannel();
        ) {
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void file_channel_tranfer_from() {
        try (
                RandomAccessFile fromFile = new RandomAccessFile("E:\\nio_buffer.txt", "rw");
                RandomAccessFile toFile = new RandomAccessFile("E:\\channel_gather_write.txt", "rw");
                FileChannel fromChannel = fromFile.getChannel();
                FileChannel toChannel = toFile.getChannel();
        ) {
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, 0, count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
