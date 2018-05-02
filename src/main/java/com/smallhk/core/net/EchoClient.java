package com.smallhk.core.net;

import java.io.*;
import java.net.Socket;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/3/29
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class EchoClient{
    private int port = 8000;
    private String host = "127.0.0.1";
    private Socket socket;

    public EchoClient() throws IOException {
        socket = new Socket(host,port);
        System.out.println("cient start");
    }


    public static void main(String[] args) throws IOException {
        new EchoClient().talk();
    }

    private void talk() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            BufferedReader localBufferReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localBufferReader.readLine()) != null){
                printWriter.println(msg);
                System.out.println(bufferedReader.readLine());
                if ("bye".equals(msg)){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
