package com.ComplaintMgmt;

import org.apache.log4j.BasicConfigurator;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.ScheduleBuilder;

public class ScheduleEmail {

	public void scdMail(String category, String details, String priority, String comp_no) {

		System.out.println(priority);

		try {
			BasicConfigurator.configure();
			JobDetail j = JobBuilder.newJob(SendScheduleEmail.class).build();

			String tName = "Trigger" + System.currentTimeMillis();

			Trigger t1 = TriggerBuilder.newTrigger().withIdentity(tName)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInHours(12))
					.build();

			Trigger t2 = TriggerBuilder.newTrigger().withIdentity(tName)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInSeconds(30))
					.build();

			Trigger t3 = TriggerBuilder.newTrigger().withIdentity(tName)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInHours(2))
					.build();

			j.getJobDataMap().put(SendScheduleEmail.category, category);
			j.getJobDataMap().put(SendScheduleEmail.details, details);
			j.getJobDataMap().put(SendScheduleEmail.priority, priority);
			j.getJobDataMap().put(SendScheduleEmail.comp_no, comp_no);

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
