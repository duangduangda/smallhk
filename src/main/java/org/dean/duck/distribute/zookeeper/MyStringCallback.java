package org.dean.duck.distribute.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;

/**
 * 异步调用回调函数
 *
 * @author eric
 */
@Slf4j
public class MyStringCallback implements AsyncCallback.StringCallback {
	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		log.info("Create path result:{},{},,{},real path name:{}", rc, path, ctx, name);
	}
}
