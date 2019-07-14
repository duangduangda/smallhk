package org.dean.duck.distribute.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/8
 * <p>
 * Company:
 * <p>
 *
 * @author:
 * <p>
 * Version: 1.0
 * <p>
 */
public class CuratorRecipesDemo {

    private  static final String zkClusters = "10.100.138.128:2181";
    private static final String path = "/zkbook/nodecache";
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .namespace("base").retryPolicy(new ExponentialBackoffRetry(1000,3))
                .connectString(zkClusters).sessionTimeoutMs(5000).build();
        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());

        final NodeCache nodeCache = new NodeCache(client,path,false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>Node data update,new Data:" + new String(nodeCache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path,"u".getBytes());
        Thread.sleep(1000);
//        client.delete().deletingChildrenIfNeeded().forPath(path);
//        Thread.sleep(Integer.MAX_VALUE);


    }
}
