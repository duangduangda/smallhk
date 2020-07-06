package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 递归创建节点，如果父节点不存在，则创建
 *
 * @author eric
 */
public class CreateNodeRecursive {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.retryPolicy(retryPolicy)
				.sessionTimeoutMs(5000)
				.build();
		client.start();
		String path = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/base/c2/c21", "second".getBytes());
		System.out.println(path);
	}
}
