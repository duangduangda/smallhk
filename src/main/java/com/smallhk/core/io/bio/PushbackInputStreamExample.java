package com.smallhk.core.io.bio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/3
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class PushbackInputStreamExample {
    public static void main(String[] args) throws IOException {
        PushbackInputStream pushbackInputStream = null;
        try {
            pushbackInputStream = new PushbackInputStream(new FileInputStream("E:\\1.txt"));
            int data = pushbackInputStream.read();
            System.out.println((char)data);
            pushbackInputStream.unread(data);
            data = pushbackInputStream.read();
            System.out.println((char)data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
