package com.smallhk.core.rpc.oio.server.skeleton;

import com.smallhk.core.rpc.oio.server.api.HelloServiceImpl;
import com.smallhk.core.rpc.oio.server.api.HelloService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcProxyOIOServer {

	private HelloService helloService = new HelloServiceImpl();
	
	
	public void publish(int port){
		try{
			ServerSocket serverSokcet = new ServerSocket(port);
			while(true){
				Socket socket = serverSokcet.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				// get method names
				String methodName = ois.readUTF();
				// get method params
				Object[]objects = (Object[])ois.readObject();
				Class<?>[] types = new Class[objects.length];
				for(int i = 0,length = types.length ;i < length;i++){
					types[i] = objects[i].getClass();
				}
				
				Method method = HelloService.class.getMethod(methodName, types);
				Object obj = method.invoke(helloService, objects);
				
				// data back
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(obj);
				oos.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
