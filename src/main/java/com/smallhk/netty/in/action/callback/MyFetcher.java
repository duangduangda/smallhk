package com.smallhk.netty.in.action.callback;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/4/13
 * <p>
 * Company: 普信恒业科技发展（北京）有限公司
 * <p>
 *
 * @Author: yaohuadong@creditease.cn
 * <p>
 * Version: 1.0
 * <p>
 */
public class MyFetcher implements Fetcher{

    private  Data data;

    public MyFetcher(Data data){
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallback fetcherCallback) {
        try {
            fetcherCallback.onData(data);
        }catch (Exception e){
            fetcherCallback.onError(e);
        }
    }
}
