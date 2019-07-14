package org.dean.duck.core.rpc.netty;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/9/6
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class RpcClient {
    public static void main(String[] args) throws InterruptedException {
        RpcFrameWork.refer("127.0.0.1",8080);
    }
}
