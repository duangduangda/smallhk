package com.smallhk.netty.in.action.codec.serializable;

import java.io.Serializable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/18
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String username;

    private String productName;

    private String address;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "subReqID=" + subReqID +
                ", username='" + username + '\'' +
                ", productName='" + productName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
