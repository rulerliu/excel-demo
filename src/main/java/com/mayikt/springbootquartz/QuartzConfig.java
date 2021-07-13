//package com.mayikt.springbootquartz;
//
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Quartz定时任务配置类
// * @author pan_junbiao
// **/
//@Configuration
//public class QuartzConfig
//{
//    private static String JOB_GROUP_NAME = "PJB_JOBGROUP_NAME";
//    private static String TRIGGER_GROUP_NAME = "PJB_TRIGGERGROUP_NAME";
//
//    /**
//     * 定时任务1：
//     * 同步用户信息Job（任务详情）
//     */
//    @Bean
//    public JobDetail syncUserJobDetail()
//    {
//        JobDetail jobDetail = JobBuilder.newJob(SyncUserJob.class)
//                .withIdentity("syncUserJobDetail",JOB_GROUP_NAME)
//                .usingJobData("userName", "pan_junbiao的博客") //设置参数（键值对）
//                .usingJobData("blogUrl","https://blog.csdn.net/pan_junbiao")
//                .usingJobData("blogRemark","您好，欢迎访问 pan_junbiao的博客")
//                .storeDurably() //即使没有Trigger关联时，也不需要删除该JobDetail
//                .build();
//        return jobDetail;
//    }
//
//    /**
//     * 定时任务1：
//     * 同步用户信息Job（触发器）
//     */
//    @Bean
//    public Trigger syncUserJobTrigger()
//    {
//        //每隔5秒执行一次
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
//
//        //创建触发器
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(syncUserJobDetail())//关联上述的JobDetail
//                .withIdentity("syncUserJobTrigger",TRIGGER_GROUP_NAME)//给Trigger起个名字
//                .withSchedule(cronScheduleBuilder)
//                .build();
//        return trigger;
//    }
//
//    @Bean
//    public JobDetail testQuartz1() {
//        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger testQuartzTrigger1() {
//        //5秒执行一次
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(testQuartz1())
//                .withIdentity("testTask1")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//    @Bean
//    public JobDetail testQuartz2() {
//        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
//    }
//
//    @Bean
//    public Trigger testQuartzTrigger2() {
//        //cron方式，每隔5秒执行一次
//        return TriggerBuilder.newTrigger().forJob(testQuartz2())
//                .withIdentity("testTask2")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
//                .build();
//    }
//
//}