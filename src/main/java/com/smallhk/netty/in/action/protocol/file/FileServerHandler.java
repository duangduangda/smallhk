package com.smallhk.netty.in.action.protocol.file;

import java.io.File;
import java.io.RandomAccessFile;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author eric
 *
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {
	
	private static final String CR = System.getProperty("line.separator");

	@Override
	protected void messageReceived(ChannelHandlerContext context,String msg) throws Exception {
		File file = new File(msg);
		
		if(!file.exists()){
			context.writeAndFlush("file not exists:" + file + CR);
			return;
		}
		
		if(!file.isFile()){
			context.writeAndFlush("not a file:" + file + CR);
			return;
		}
		context.write(file + " " + file.length() + CR);
		
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
		FileRegion fileRegion = new DefaultFileRegion(file, 0, randomAccessFile.length());
		context.write(fileRegion);
		context.writeAndFlush(CR);
		randomAccessFile.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context,Throwable cause) throws Exception{
		cause.printStackTrace();
		if(context.channel().isActive()){
			context.close();
		}
	}

}
