package com.smallhk.core.exception;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/2
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NonFailSafeException {
    public static void main(String[] args) throws Exception {
//        wrong_close_handler();
        correct_close_but_not_perfact_handler();
    }

    /**
     * 假设可以找到文件，不会排除FileNotFoundException
     * 但是，如果在finally里执行close时，发生错误，抛出的异常一样会覆盖之前的异常，
     * 这种情况下，只有第二次抛出的异常被接受并处理
     */
    private static void correct_close_but_not_perfact_handler() throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E:\\21321.txt");
            int data = inputStream.read();
            while (data != -1){
                System.out.print((char)data);
                data = inputStream.read();
            }
        }catch (IOException e){
            throw new Exception(e);
        }finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 异常返回为nullpointerException。
     * 异常提示指向的是finally块中的close,但是实际引发的并不是在该处，而是在try块中触发
     */
    private static void wrong_close_handler() throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E:\\21321.txt");
            int data = inputStream.read();
            while (data != -1){
                System.out.print((char)data);
                data = inputStream.read();
            }
        }catch (IOException e){
            throw new Exception(e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new Exception(e);
            }
        }
    }
}
