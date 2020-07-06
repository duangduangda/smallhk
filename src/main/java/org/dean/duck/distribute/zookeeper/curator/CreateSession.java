package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 创建会话，使用fluent风格
 *
 * @author eric
 */
public class CreateSession {
	public static void main(String[] args) throws InterruptedException {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.build();

		client.start();
		Thread.sleep(Integer.MAX_VALUE);

	}
}
