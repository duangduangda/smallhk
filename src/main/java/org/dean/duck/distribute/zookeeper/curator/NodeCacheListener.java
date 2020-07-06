package org.dean.duck.distribute.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 使用nodecache持续监听
 *
 * @author eric
 */
public class NodeCacheListener {
	public static void main(String[] args) throws Exception {
		String path = "/zk-book/nocache";
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("localhost:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath(path, "init".getBytes());
		NodeCache nodeCache = new NodeCache(client, path, false);
		nodeCache.start(true);
		nodeCache.getListenable().addListener(() -> System.out.println("Node update,new data:" + new String(nodeCache.getCurrentData().getData())));
		client.setData().forPath(path, "uuu".getBytes());
		Thread.sleep(1000);
		client.delete().deletingChildrenIfNeeded().forPath(path);
		Thread.sleep(Integer.MAX_VALUE);


	}
}
