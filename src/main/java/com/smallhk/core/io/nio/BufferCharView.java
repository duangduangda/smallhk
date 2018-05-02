package com.smallhk.core.io.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Title. <br>
 * Description. 创建一个ByteBuffer视图
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
public class BufferCharView {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        byteBuffer.put(0,(byte)0);
        byteBuffer.put(1,(byte)'H');
        byteBuffer.put(2,(byte)0);
        byteBuffer.put(3,(byte)'i');
        byteBuffer.put(4,(byte)0);
        byteBuffer.put(5,(byte)'!');
        byteBuffer.put(6,(byte)0);

        println(byteBuffer);
        println(charBuffer);
    }

    private static void println(Buffer buffer) {
        System.out.println("pos = " + buffer.position() + ",limit = " + buffer.limit() + ",capacity = " + buffer.capacity() + ":'" + buffer.toString() + "'");
    }
}
