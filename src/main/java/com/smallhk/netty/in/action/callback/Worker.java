package com.smallhk.netty.in.action.callback;

/**
 * Title. <br>
 * Description. class for callback
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class Worker {

    public void work(){
        Fetcher fetcher = new MyFetcher(new Data(1, 0));
        fetcher.fetchData(new FetcherCallback() {
            @Override
            public void onData(Data data) throws Exception {
                System.out.println("Received data:" + data);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("An error accour" + throwable.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.work();
    }
}
