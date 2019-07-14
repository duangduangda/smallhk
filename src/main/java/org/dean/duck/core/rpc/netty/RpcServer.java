package org.dean.duck.core.rpc.netty;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/9/6
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class RpcServer {
    public static void main(String[] args) throws InterruptedException {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
            RpcFrameWork.export(threadPoolExecutor, 8080);
    }
}
