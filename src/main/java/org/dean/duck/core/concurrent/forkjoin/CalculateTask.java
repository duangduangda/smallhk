/**
 * 
 */
package org.dean.duck.core.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * calculator with fork/join task
 * @author duangduangda
 *
 */
public class CalculateTask extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = 1L;
	private int start;
	private int end ;
	private static final int THRESHOLD = 8;
	
	
	public CalculateTask(int start,int end){
		this.start = start;
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.RecursiveTask#compute()
	 */
	@Override
	protected Integer compute() {
		int sum  = 0;
		if(end - start <= THRESHOLD){
			for(int i = start;i <= end;i++){
				sum += i;
			}
		}else{
			int mid = (start + end) / 2;
			CalculateTask leftTask = new CalculateTask(start,mid);
			CalculateTask rightTask = new CalculateTask(mid +  1,end);
			leftTask.fork();
			rightTask.fork();
			//merge the left and right result
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			
			sum =  leftResult + rightResult;
		}
		System.out.println("[" +Thread.currentThread().getName()+ "]" + "当前sum = " + sum);
		return sum;
	}

}
