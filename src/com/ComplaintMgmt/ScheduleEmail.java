package com.ComplaintMgmt;

import org.apache.log4j.BasicConfigurator;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ScheduleEmail {

	public void scdMail(String category, String details, String priority, String comp_no, int time1, int time2, int time3, String level) {

		System.out.println(priority);

		try {
			BasicConfigurator.configure();
			JobDetail j = JobBuilder.newJob(SendScheduleEmail.class).build();

			String tName = "Trigger" + System.currentTimeMillis();
			
			Trigger t1 = TriggerBuilder.newTrigger().withIdentity(tName).startAt(DateBuilder.futureDate(time1, IntervalUnit.MINUTE))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
					.build();

			Trigger t2 = TriggerBuilder.newTrigger().withIdentity(tName).startAt(DateBuilder.futureDate(time2, IntervalUnit.MINUTE))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
					.build();

			Trigger t3 = TriggerBuilder.newTrigger().withIdentity(tName).startAt(DateBuilder.futureDate(time3, IntervalUnit.MINUTE))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
					.build();
			
			
//
//			Trigger t1 = TriggerBuilder.newTrigger().withIdentity(tName)
//					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInHours(time1))
//					.build();
//
//			Trigger t2 = TriggerBuilder.newTrigger().withIdentity(tName)
//					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInHours(time2))
//					.build();
//
//			Trigger t3 = TriggerBuilder.newTrigger().withIdentity(tName)
//					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInHours(time3))
//					.build();

			j.getJobDataMap().put(SendScheduleEmail.category, category);
			j.getJobDataMap().put(SendScheduleEmail.details, details);
			j.getJobDataMap().put(SendScheduleEmail.priority, priority);
			j.getJobDataMap().put(SendScheduleEmail.comp_no, comp_no);
			j.getJobDataMap().put(SendScheduleEmail.level, level);

			Scheduler sc = new StdSchedulerFactory().getScheduler();
			sc.start();
			if (priority.equals("Minor")) {
				sc.scheduleJob(j, t1);
			} else if (priority.equals("Major")) {
				sc.scheduleJob(j, t2);
			} else if (priority.equals("Critical")) {
				sc.scheduleJob(j, t3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
