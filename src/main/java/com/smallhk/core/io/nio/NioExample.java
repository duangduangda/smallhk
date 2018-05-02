package com.smallhk.core.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/2/28
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class NioExample {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\pypy\\file2write1.txt", "rw")) {
            FileChannel fileChannel = randomAccessFile.getChannel();
            //create buffer with capacity of 48 bytes
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            // from channel read data into buffer.
            int bytesRead = fileChannel.read(byteBuffer);
            while (bytesRead != -1){
                System.out.println("Read:" + bytesRead);
                //make buffer ready for read
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    // read 1 byte at a time
                    System.out.print((char) byteBuffer.get());
                }
                //make buffer ready for writing。
                // clear()方法会清空整个缓冲区，position将被设回0，limit被设置成 capacity的值.Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
                // 如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法,compact()方法只会清除已经读过的数据
//                byteBuffer.clear();
                byteBuffer.compact();
                bytesRead = fileChannel.read(byteBuffer);
            }
        }
    }
}
