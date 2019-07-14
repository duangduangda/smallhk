package org.dean.duck.core.io.bio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/23
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class EncodeAndDecoder {
    public static void main(String[] args) throws IOException {
        IOEncodeAndDecoder();
        storageEncodeAndDecoder();
    }

    private static void storageEncodeAndDecoder() {
        String string = "这是测试的中文字符";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(string);
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer.toString());

    }

    private static void IOEncodeAndDecoder()throws IOException  {
        String file = "E:/stream.txt";
        String charset = "UTF-8";

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, charset);
        try  {
            outputStreamWriter.write("这是要保存的中文字符");
        }finally {
            outputStreamWriter.close();
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
        StringBuffer stringBuffer = new StringBuffer();
        char[] buff = new char[64];
        int count = 0;
        try{
            while((count = inputStreamReader.read(buff)) != -1){
                stringBuffer.append(buff,0,count);
            }
        }finally {
            inputStreamReader.close();
        }
        System.out.println(stringBuffer.toString());
    }
}
