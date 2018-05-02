package com.smallhk.core.net;

import java.io.*;
import java.net.*;

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
public class UDPEchoClient {
    private int port = 70;
    private DatagramSocket datagramSocket;

    public UDPEchoClient() throws IOException {
        datagramSocket = new DatagramSocket();
        System.out.println("cient start");
    }


    public static void main(String[] args) throws IOException {
        new UDPEchoClient().talk();
    }

    private void talk() {
        byte[] buffer = "0123456789".getBytes();
        InetAddress receiverAddress = null;
        try {
            receiverAddress = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(
                    buffer, buffer.length, receiverAddress, port);
            datagramSocket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != datagramSocket){
                datagramSocket.close();
            }
        }

    }
}
