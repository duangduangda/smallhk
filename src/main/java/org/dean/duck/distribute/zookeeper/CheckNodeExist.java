package org.dean.duck.distribute.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author eric
 */
public class CheckNodeExist {
	private final static String ZK_SERVER = "localhost:2181";
	private final static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new MyWatcher(countDownLatch));
		countDownLatch.await();
		String path = "/zk_book/c1";
		zooKeeper.exists(path, true);
		zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zooKeeper.setData(path, "123".getBytes(), -1);
		zooKeeper.create(path + "/c11", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		zooKeeper.delete(path + "/c11", -1);
		zooKeeper.delete(path, -1);
		Thread.sleep(Integer.MAX_VALUE);

	}
}
