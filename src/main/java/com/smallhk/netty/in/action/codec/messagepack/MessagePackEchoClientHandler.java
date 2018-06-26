package com.smallhk.netty.in.action.codec.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/6/26
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class MessagePackEchoClientHandler extends ChannelHandlerAdapter {

    private final  int sendNumber;

    public MessagePackEchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext context){
        Student[] students = initStudents();
        for (Student student:students){
            context.write(student);
        }
        context.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg){
        System.out.println("Client receive the msgpack message :" + msg);
        context.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }
    private Student[] initStudents() {
        Student[] students = new Student[sendNumber];
        Student stu = null;
        for (int i = 0;i < sendNumber;i++){
            stu = new Student();
            stu.setAge(i);
            stu.setName("student" + i );
            students[i] = stu;
        }
        return students;
    }
}
