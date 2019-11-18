/**
 *
 */
package org.dean.duck.core.rpc.oio.server.api;


import com.mysql.jdbc.StringUtils;

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
		if (StringUtils.isNullOrEmpty(info)) {
			throw new RuntimeException("Param 'info' can not be null");
		}
		return "hello," + info;
	}

}
