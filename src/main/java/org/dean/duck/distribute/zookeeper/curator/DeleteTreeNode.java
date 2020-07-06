package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 删除叶子节点
 *
 * @author eric
 */
public class DeleteTreeNode {
	public static void main(String[] args) throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		String path = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/base/c3/c31");
		System.out.println(path);
		client.delete().forPath("/base/c3/c31");
		System.out.println(client.getChildren().toString());
	}
}
