package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author eric
 */
@Slf4j
public class SyncCreateNode {
	private final static String ZK_SERVER = "localhost:2181";
	private final static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new MyWatcher(countDownLatch));
		countDownLatch.await();
		String path = zooKeeper.create("/zk_learn", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		log.info("Success to create node:{}", path);
	}
}
