package org.dean.duck.core.rmi;

import java.rmi.Remote;
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
public interface HelloService extends Remote{

    String sayHello(String name) throws RemoteException;
}
