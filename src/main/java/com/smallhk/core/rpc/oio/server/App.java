package com.smallhk.core.rpc.oio.server;

import com.smallhk.core.rpc.oio.server.skeleton.RpcProxyOIOServer;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/9/3
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class App {
    public static void main(String[] args) {
        RpcProxyOIOServer proxyOIOServer = new RpcProxyOIOServer();
        proxyOIOServer.publish(1234);
    }
}
