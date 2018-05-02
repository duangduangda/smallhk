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
public class Data {
    private int n;
    private int m;

    public Data(int n, int m){
        this.n = n;
        this.m = m;
    }

    @Override
    public  String toString(){
        int r = n / m;
        return n + "/" + m + " = " + r;
    }
}
