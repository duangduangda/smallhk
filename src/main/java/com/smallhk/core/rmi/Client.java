package com.smallhk.core.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        logger.info("RMI registry binding...");
        String url = "rmi://localhost:1099/helloService";
        HelloService helloService = (HelloService) Naming.lookup(url);
        logger.info(helloService.sayHello("eric"));
    }
}
