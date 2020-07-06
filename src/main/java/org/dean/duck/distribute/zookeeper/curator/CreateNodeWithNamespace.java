package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 创建节点，curator默认创建的是持久节点 1. 创建包含隔离空间的会话 2. 创建子节点
 *
 * @author eric
 */
public class CreateNodeWithNamespace {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
		// 创建包含隔离空间的会话,默认创建/base为根节点
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.retryPolicy(retryPolicy)
				.sessionTimeoutMs(5000)
				.namespace("base")
				.build();
		client.start();
		String path = client.create().forPath("/c1", "init".getBytes());
		System.out.println(path);
	}
}
