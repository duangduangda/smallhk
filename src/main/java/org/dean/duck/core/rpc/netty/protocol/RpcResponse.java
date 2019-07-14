package org.dean.duck.core.rpc.netty.protocol;

import java.io.Serializable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/9/4
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * Version: 1.0
 * <p>
 */
public class RpcResponse implements Serializable{
    private static final long serialVersionUID = -8382575034741383491L;
    private String requestId;
    private String error;
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
