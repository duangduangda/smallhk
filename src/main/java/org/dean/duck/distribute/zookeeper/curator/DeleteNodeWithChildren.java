package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 删除节点，并递归删除子节点
 *
 * @author eric
 */
public class DeleteNodeWithChildren {
	public static void main(String[] args) throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		String path = "/base/c4";
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
				.forPath(path, "init".getBytes());
		Stat stat = new Stat();
		client.getData().storingStatIn(stat).forPath(path);
		client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
	}
}
