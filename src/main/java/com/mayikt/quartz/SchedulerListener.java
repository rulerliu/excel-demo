//package com.mayikt.quartz;
//
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//@Configuration
//public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    public MyScheduler myScheduler;
//
//    @Autowired
//    MyJobFactory myJobFactory;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            myScheduler.scheduleJobs();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//        bean.setOverwriteExistingJobs(true);
//        bean.setStartupDelay(1);
//        bean.setJobFactory(myJobFactory);
//        return bean;
//    }
//
//}