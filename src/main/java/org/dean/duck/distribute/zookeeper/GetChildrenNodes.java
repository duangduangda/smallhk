package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author eric
 */
@Slf4j
public class GetChildrenNodes {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static final String ZK_SERVER = "127.0.0.1:2181";


	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new MyWatcher(countDownLatch));
		countDownLatch.await();
		zooKeeper.create("/zk_book", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zooKeeper.create("/zk_book/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		List<String> children = zooKeeper.getChildren("/zk_book", true);
		System.out.println(children);
		zooKeeper.create("/zk_book/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

	}
}
