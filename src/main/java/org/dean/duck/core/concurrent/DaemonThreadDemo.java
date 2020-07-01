package org.dean.duck.core.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DaemonThreadDemo  implements Runnable{

	@Override
	public void run() {
		System.out.println("进入后台线程.....");
		try {
			writeToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出后台线程.....");
	}

	private void writeToFile() throws Exception{
		File file = new File("e:" + File.separator + "daemon.log");
		OutputStream os = new FileOutputStream(file,true);
		int count = 0;
		while(count < 900){
			os.write(("\r\n" + Thread.currentThread().getName() + " print word-" + count++).getBytes());
			System.out.println(Thread.currentThread().getName() + " print word-" + count);
			Thread.sleep(2000);
		}
		os.close();
	}

}
