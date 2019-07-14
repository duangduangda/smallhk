package org.dean.duck.core.rpc.oio.client.stub;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

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
public class RpcProxyOIOClient<T>{
    
    private String host;
    
    private int port;
    
    public RpcProxyOIOClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public T proxyClient(Class clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try{
                    Socket socket = new Socket(host,port);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeUTF(method.getName());
                    oos.writeObject(args);
                    oos.flush();

                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    return objectInputStream.readObject();
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        });
    }
}
