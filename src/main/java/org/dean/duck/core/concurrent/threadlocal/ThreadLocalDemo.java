package org.dean.duck.core.concurrent.threadlocal;

public class ThreadLocalDemo {

	private final static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		
		@Override
		protected Integer initialValue(){
			return 0;
		}
	};
	
	public static class MyThread implements Runnable{
		private int index;
		
		
		public MyThread(int index){
			this.index = index;
		}

		@Override
		public void run() {
			System.out.println("线程 "+ index + "初始值 ："  + value.get());
			for(int i = 0;i < 10 ;i++){
				value.set(value.get() + i);
			}
			System.out.println("线程 "+ index + "累计值： "  + value.get());
		}
		
	}
}
