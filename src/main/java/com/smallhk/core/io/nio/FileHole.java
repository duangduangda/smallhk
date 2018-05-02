package com.smallhk.core.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
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
public class FileHole {
    public static void main(String[] args) throws IOException {
        //crete a temp file,open for writing,and get a filechannel
        File temp = File.createTempFile("holly",null);
        RandomAccessFile randomAccessFile = new RandomAccessFile(temp,"rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // create a working buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        putData(0,byteBuffer,fileChannel);
        putData(5000000,byteBuffer,fileChannel);
        putData(5000,byteBuffer,fileChannel);
        System.out.println("Wrote temp file " + temp.getParent() + "',size = "  + fileChannel.size());
        fileChannel.close();
        randomAccessFile.close();
    }

    /**
     *
     * @param position
     * @param byteBuffer
     * @param fileChannel
     */
    private static void putData(int position, ByteBuffer byteBuffer, FileChannel fileChannel) throws IOException {
        String string = "*<--- location " + position;
        byteBuffer.clear();
        byteBuffer.put(string.getBytes("US-ASCII"));
        byteBuffer.flip();
        fileChannel.position(position);
        fileChannel.write(byteBuffer);
    }
}
