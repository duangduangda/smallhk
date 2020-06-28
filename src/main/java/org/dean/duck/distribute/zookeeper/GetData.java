package org.dean.duck.distribute.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author eric
 */
public class GetData {
	private final static String ZK_SERVER = "localhost:2181";
	private final static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static Stat stat = new Stat();

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		String path = "/zk_book/c1";
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new MyWatcher(countDownLatch));
		countDownLatch.await();
		zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(new String(zooKeeper.getData(path, true, stat)));
		zooKeeper.setData(path, "123".getBytes(), -1);
	}
}
