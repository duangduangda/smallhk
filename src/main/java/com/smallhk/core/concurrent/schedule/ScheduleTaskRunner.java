package com.smallhk.core.concurrent.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * start a thread to run schedule task
 * @author duangduangda
 *
 */
public class ScheduleTaskRunner {

	public static void main(String[] args) {
		Runnable scheduleTask = new Runnable(){

			public void run() {
				System.out.println(new Date() + "Using multiple thread to run schedule tasks...");
			}
			
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(scheduleTask, getInitialDelayTime(), 60*60*1000, TimeUnit.MILLISECONDS);
	}

	private static long getInitialDelayTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 14);
		cal.set(Calendar.MINUTE, 5);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 001);
		long initialDelayTime = cal.getTimeInMillis()- new Date().getTime();
		System.out.println(initialDelayTime);
		return initialDelayTime;
	}
}


