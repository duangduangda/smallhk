package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @author eric
 */
@Slf4j
public class MyDataCallback implements AsyncCallback.DataCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		log.info(rc + "," + path + "," + new String(data) + stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
	}
}
