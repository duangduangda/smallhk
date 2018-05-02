package com.smallhk.core.io.bio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/11
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class MyRandomAccessFile {
    public static void main(String[] args) throws IOException {
//        write2File();
        readFrmFile();
    }

    private static void readFrmFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\2.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("file position:" + fileChannel.position());
        System.out.println(randomAccessFile.length());
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        while (fileChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();
        }
        fileChannel.close();
        randomAccessFile.close();
    }

    private static void write2File() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\2.txt","rw");
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put("123213213".getBytes());
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.write(byteBuffer);
        byteBuffer.flip();
        fileChannel.close();
        randomAccessFile.close();
    }
}
