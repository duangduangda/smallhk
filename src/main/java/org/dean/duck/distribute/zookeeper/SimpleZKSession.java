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
public class SimpleZKSession implements Watcher {
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static final String ZK_SERVER = "127.0.0.1:2181";

	@Override
	public void process(WatchedEvent event) {
		log.info("Receive watched event:{}", event);
		if (Event.KeeperState.SyncConnected == event.getState()) {
			// 异步连接成功之后，解除主线程对countDown的阻塞，继续执行主线程其他逻辑
			countDownLatch.countDown();
		}

	}

	public static void main(String[] args) throws IOException {
		ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER, 5000, new SimpleZKSession());
		System.out.println(zooKeeper.getState());
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		log.info("Zookeeper session established");
	}
}
