package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class MyWatcher implements Watcher {
	private CountDownLatch countDownLatch;

	public MyWatcher(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void process(WatchedEvent event) {
		log.info("Receive zookeeper event:{}", event);
		if (Event.KeeperState.SyncConnected == event.getState()) {
			countDownLatch.countDown();
		} else if (Event.EventType.NodeChildrenChanged == event.getType()) {
			log.info("Node children change ,please get and refresh them ");
		} else if (Event.EventType.NodeDataChanged == event.getType()) {
			log.info("Node data changed");
		}
	}
}
