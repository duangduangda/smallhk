package org.dean.duck.netty.in.action.callback;

/**
 * Title. <br>
 * Description.
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
public interface Fetcher {
    void fetchData(FetcherCallback fetcherCallback);
}
