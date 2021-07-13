//package com.mayikt.quartz;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import java.util.Date;
//
//public class MySchedulerJob implements Job {
//
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		System.out.println("MySchedulerJob: The time is now " + new Date().toLocaleString());
//	}
//
//}