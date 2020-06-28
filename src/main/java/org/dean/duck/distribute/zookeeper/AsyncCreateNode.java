package org.dean.duck.distribute.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author eirc
 */
public class AsyncCreateNode {
	private final static CountDownLatch countDownLatch = new CountDownLatch(1);
	private final static String ZK_SERVER = "localhost:2181";

	public static void main(String[] args) throws IOException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new MyWatcher(countDownLatch));
		countDownLatch.await();
		zooKeeper.create("/zk_learn_async/nodes", "second node".getBytes(),
				Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL,
				new MyStringCallback(), "I am a context.");
		Thread.sleep(Integer.MAX_VALUE);

	}
}
