package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author eric
 */
@Slf4j
public class SimpleSessionWithSessionId implements Watcher {
	private static final CountDownLatch countDownLatch = new CountDownLatch(1);
	private static final String ZK_SERVER = "127.0.0.1:2181";

	@Override
	public void process(WatchedEvent event) {
		log.info("Receive zookeeper event:{}", event);
		if (Event.KeeperState.SyncConnected == event.getState()) {
			countDownLatch.countDown();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new SimpleSessionWithSessionId());
		countDownLatch.await();
		// 获取当前对话的sessionId及password
		long sessionId = zooKeeper.getSessionId();
		byte[] sessionPassword = zooKeeper.getSessionPasswd();

		//新建一个zk实例，使用非法的sessionId
		zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new SimpleSessionWithSessionId(), 1l, "test".getBytes());

		// 新建一个zk实例，复用之前的sessionId
		zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new SimpleSessionWithSessionId(), sessionId, sessionPassword);

		Thread.sleep(Integer.MAX_VALUE);

	}
}
