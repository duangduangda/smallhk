package com.smallhk.core.io.bio;

import com.sun.org.apache.xerces.internal.impl.Constants;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

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
public class SequenceInputStreamExample {
    public static void main(String[] args) throws IOException {
        twoInputMerge2SequenceInput();
        multiInputMerge2SequenceInput1();
        multiInputMerge2SequenceInput2();
    }

    private static void multiInputMerge2SequenceInput2() throws FileNotFoundException {
        try (
                InputStream inputStream1 = new FileInputStream("E:\\1.txt");
                InputStream inputStream2 = new FileInputStream("E:\\2.txt");
                InputStream inputStream3 = new FileInputStream("E:\\3.txt");
                InputStream inputStream4 = new FileInputStream("E:\\4.txt");
                SequenceInputStream sequenceInputStream1 = new SequenceInputStream(inputStream1,inputStream2);
                SequenceInputStream sequenceInputStream2 = new SequenceInputStream(inputStream3,inputStream4);
                SequenceInputStream sequenceInputStream = new SequenceInputStream(sequenceInputStream1, sequenceInputStream2)) {
            int data = sequenceInputStream.read();
            while (data != -1){
                System.out.print((char)data);
                data = sequenceInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void multiInputMerge2SequenceInput1() throws IOException {
        InputStream inputStream1 = null;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        SequenceInputStream sequenceInputStream = null;
        try {
                inputStream1 = new FileInputStream("E:\\1.txt");
                inputStream2 = new FileInputStream("E:\\2.txt");
                inputStream3 = new FileInputStream("E:\\3.txt");
                Vector<InputStream> inputStreamVector = new Vector<>();
                inputStreamVector.addElement(inputStream1);
                inputStreamVector.addElement(inputStream2);
                inputStreamVector.addElement(inputStream3);
                sequenceInputStream = new SequenceInputStream(inputStreamVector.elements());
                int data = sequenceInputStream.read();
                while (data != -1){
                    System.out.print((char)data);
                    data = sequenceInputStream.read();
                }
                System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != inputStream1){
                inputStream1.close();
            }
            if (null != inputStream2){
                inputStream2.close();
            }
            if (null != inputStream3){
                inputStream3.close();
            }
            if (null != sequenceInputStream){
                sequenceInputStream.close();
            }
        }
    }

    private static void twoInputMerge2SequenceInput() {
        try (
                InputStream inputStream1 = new FileInputStream("E:\\1.txt");
                InputStream inputStream2 = new FileInputStream("E:\\2.txt");
                SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1,inputStream2);
        ) {
            int data = sequenceInputStream.read();
            while (data != -1){
                System.out.print((char) data);
                data = sequenceInputStream.read();
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
