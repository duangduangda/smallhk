package org.dean.duck.core.io.bio;

import java.io.*;

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
public class DataInAndOutExample {
    public static void main(String[] args) {
        data_write();
        data_read();
    }

    private static void data_read() {
        InputStream dataInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream("E:\\1.txt"));
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String count;
            while ((count = bufferedReader.readLine()) != null){
                System.out.println(count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != dataInputStream){
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void data_write() {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("E:\\1.txt",false));
            dataOutputStream.write("美国人".getBytes());
            dataOutputStream.writeBytes("LA,US");
            dataOutputStream.writeBytes(String.valueOf(34));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != dataOutputStream){
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
