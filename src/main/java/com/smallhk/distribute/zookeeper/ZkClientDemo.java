package com.smallhk.distribute.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
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
public class ZkClientDemo {
    public static void main(String[] args) throws InterruptedException {
//        createNode();
//        checkNodeExist();
        readData();
    }

    private static void readData() throws InterruptedException {
        String zkClusters = "10.100.138.128:2181";
        //创建会话
        String path = "/zkbook";
        ZkClient zkClient = new ZkClient(zkClusters, 10000,10000,new SerializableSerializer());
        zkClient.createEphemeral(path,"123");
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("node:" + dataPath + " changed and new data is " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("node:" + dataPath + " deleted");
            }
        });

        System.out.println(zkClient.readData(path));
        zkClient.writeData(path,"456");
        Thread.sleep(1000);
        System.out.println(zkClient.exists(path));
        zkClient.delete(path);
        System.out.println(zkClient.exists(path));
        Thread.sleep(1000);
    }

    private static void checkNodeExist() {
        ZkClient zkClient = createSession();
        // 判断结点是否存在
        boolean isExist = zkClient.exists("/eric");
        System.out.println(isExist);
    }

    private static void createNode() {
        ZkClient zkClient = createSession();
        // 创建临时node
        String path = zkClient.create("/eric","123".getBytes(), CreateMode.EPHEMERAL);
        System.out.println("create path:" + path);
    }

    private static ZkClient createSession(){
        String zkClusters = "10.100.138.128:2181";
        //创建会话
        ZkClient zkClient = new ZkClient(zkClusters, 10000,10000,new SerializableSerializer());
        System.out.println("connnect ok");
        return zkClient;
    }
}
