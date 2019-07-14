/**
 * 
 */
package org.dean.duck.core.rpc.oio.server.api;


/**
 * @author Administrator
 *
 */
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.smallhk.rpc.api.HelloService#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String info) {
		return "hello," + info;
	}

}
