//package com.mayikt.quartz;
//
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyScheduler {
//
//    @Autowired
//    SchedulerFactoryBean schedulerFactoryBean;
//
//    public void scheduleJobs() throws SchedulerException {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        startJob1(scheduler);
//        startJob2(scheduler);
//    }
//
//    private void startJob1(Scheduler scheduler) throws SchedulerException{
//        JobDetail jobDetail = JobBuilder.newJob(MySchedulerJob.class)
//                .withIdentity("job1", "group1").build();
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
//                .withSchedule(scheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail,cronTrigger);
//    }
//
//    private void startJob2(Scheduler scheduler) throws SchedulerException{
//        JobDetail jobDetail = JobBuilder.newJob(MySchedulerJob2.class)
//                .withIdentity("job2", "group1").build();
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
//                .withSchedule(scheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail,cronTrigger);
//    }
//}