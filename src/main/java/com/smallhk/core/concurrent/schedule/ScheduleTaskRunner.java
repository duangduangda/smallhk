package com.smallhk.core.concurrent.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * start a thread to run schedule task
 * 
 * @author duangduangda
 *
 */
public class ScheduleTaskRunner {

	public static void main(String[] args) {
		Runnable scheduleTask = new Runnable() {

			public void run() {
				System.out.println("[" + Thread.currentThread().getName() + " " + new Date()  + "] Using multiple thread to run schedule tasks...");
			}

		};

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(scheduleTask, getInitialDelayTime(), 60 * 1000, TimeUnit.MILLISECONDS);
	}

	private static long getInitialDelayTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 11,
				33, 00);
		return calendar.getTimeInMillis() - new Date().getTime();
	}
}
