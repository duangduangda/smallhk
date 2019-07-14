package org.dean.duck.core.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/10/10
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        logger.info("server running.....");
        HelloService helloService = new HelloServiceImpl();
        logger.info("binding server implement to registry..");
        // rmi默认的端口是1099
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://localhost:1099/helloService",helloService);
        logger.info("waiting for invocation from client..");
    }

}
