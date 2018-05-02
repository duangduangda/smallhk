package com.smallhk.core.io.bio;

import java.io.*;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/29
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class FileHandler {
    public static void main(String[] args) {
//        getFileLength();
//         checkFileExist();
//        readFile();
//        writeFile();
//        readFile1();
//        writeFile1();
//        renameFile();
        readFileList();

    }

    private static void readFileList() {
        File file = new File("E:\\");
        if (file.isDirectory()){
            String[]fileNames = file.list();
            for (String fileName:fileNames){
                System.out.println(fileName);
            }
        }else{
            System.out.println(file.getName());
        }
    }

    private static void renameFile() {
        File file = new File("E:\\1.txt");
        if (file.exists()){
            System.out.println("文件存在可以重命名");
            file.renameTo(new File("E:\\rename_1.txt"));
        }else{
            System.out.println("文件不存在无法重命名");
        }
    }

    /**
     * 检测文件是否存在
     */
    private static void checkFileExist() {
        File file = new File("E:\\io_file_write.txt");
        if (file.exists()){
            System.out.println("file exists...");
        }else{
            System.out.println("file not exist");
        }
    }

    private static void readFile1() {
        try (FileInputStream fileInputStream = new FileInputStream("E:\\io_file_write.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8")
        ) {
            StringBuffer stringBuffer = new StringBuffer();
            while (inputStreamReader.ready()){
                stringBuffer.append((char) inputStreamReader.read());
            }
            System.out.println(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeFile1() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\io_file_write.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8")

        ) {
            outputStreamWriter.append("中文输入");
            outputStreamWriter.append("\r\n");
            outputStreamWriter.append("English");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\io_file_write.txt")) {
            byte[] bytes = {11,21,3,40,5};
            for (int i = 0;i < bytes.length;i++){
                fileOutputStream.write(bytes[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try (InputStream fileInputStream = new FileInputStream("E:\\nio_buffer.txt")) {
            int size = fileInputStream.available();
            for (int i = 0;i < size;i++){
                System.out.print((char)fileInputStream.read());
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFileLength() {
        File file = new File("E:\\io_file_write.txt");
        long length = file.length();
        System.out.println("length = " + length);
    }
}
