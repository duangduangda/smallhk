package org.dean.duck.netty.in.action.protocol.netty;

import java.io.Serializable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/10/5
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NettyMessage implements Serializable{

    private static final long serialVersionUID = 9079214178784062349L;
    private Header header;

    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    public enum MessageType{
        LOGIN_REQ((byte)0,"登录请求"),LOGIN_RESP((byte)1,"登录反馈"),HEARTBEAT_REQ((byte)2,"心跳检测请求"),HEARTBEAT_RESP((byte)3,"心跳检测反馈");
        byte code;
        String desc;

        MessageType(byte code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public byte value(){
            return this.code;
        }
    }
}
