package com.smallhk.core.rpc.oio.client;

import com.smallhk.core.rpc.oio.client.stub.RpcProxyOIOClient;
import com.smallhk.core.rpc.oio.server.api.HelloService;

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
        RpcProxyOIOClient<HelloService>rpcProxyOIOClient = new RpcProxyOIOClient<>("localhost",1234);
        HelloService helloService = rpcProxyOIOClient.proxyClient(HelloService.class);
        System.out.println(helloService.sayHello("eric"));
    }
}
