package org.dean.duck.distribute.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/7
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class CuratorDemo {
    private static final String zkClusters = "10.100.138.128:2181";
    public static void main(String[] args) throws Exception {
        createSession();
        createSessionFluentely();
        createNode();
        deleteNode();
    }

    private static void deleteNode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkClusters).sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy).namespace("base").build();
        client.start();
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/zkbook/c1");
        client.delete().deletingChildrenIfNeeded().withVersion(stat.getAversion()).forPath("/zkbook/c1");
    }


    private static void createNode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkClusters).sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy).namespace("base").build();
        client.start();
        client.create().
                creatingParentsIfNeeded().
                withMode(CreateMode.EPHEMERAL).forPath("/zkbook/c1","c1".getBytes());

        Stat stat = new Stat();
        String string = new String(client.getData().storingStatIn(stat).forPath("/zkbook/c1"));
        System.out.println(">>>>>>>>>>>>>>>>" + string);


    }


    private static void createSessionFluentely() throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkClusters).sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy).namespace("base").build();
        client.start();
        Thread.sleep(Integer.MAX_VALUE);

    }

    private static void createSession() throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkClusters,5000,3000,retryPolicy);
        client.start();
        Thread.sleep(1000);
    }
}
