package org.dean.duck.core.io.bio;

import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

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
public class ReaderWriterExample {
    public static void main(String[] args) {
//        file_writer();
//        file_reader();
//        file_writer_outputstream();
//        file_read_inputstream();
        file_writer_writer();
        file_reader_reader();
        
    }

    private static void file_writer_writer() {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("E:\\1.txt",true));
            writer.append("beijing");
            writer.write(new String("北京".getBytes("UTF-8"),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void file_reader_reader() {
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader("E:\\1.txt"));
            int data = reader.read();
            while (data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void file_writer_outputstream() {
        Writer writer = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\1.txt",true);//设置操作模式为追加
            writer = new OutputStreamWriter(outputStream,"UTF-8");
            writer.append("zhongg");
            writer.append("中国人");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void file_read_inputstream() {
        Reader reader = null;
        InputStream inputStream =  null;
        try {
            inputStream = new FileInputStream("E:\\1.txt");
            reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            if (null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    private static void file_writer() {
        Writer writer = null;
        try {
            writer = new FileWriter("E:\\1.txt",true);
            writer.append("798");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void file_reader() {
        Reader reader = null;
        try {
             reader = new FileReader("E:\\1.txt");
             int data = reader.read();
             while (data != -1){
                 System.out.print((char)data);
                 data = reader.read();
             }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
