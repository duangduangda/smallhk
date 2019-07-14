package org.dean.duck.core.concurrent.produceconsume;

import java.io.Serializable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/15
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class TData implements Serializable {
    private final int data;

    public TData(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }


    @Override
    public String toString(){
        return "TData:" + data;
    }
}
