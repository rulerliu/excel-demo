//package com.mayikt.elastic_job;
//
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 活动订购对账定时任务
// */
//@Slf4j
//@Component
//public class MyJob2 implements SimpleJob {
//    public static final String jobName = "TestQuartz02";
//    @Autowired
//    private Environment env;
//
//    @Override
//    public void execute(ShardingContext shardingContext) {
//        long start = System.currentTimeMillis();
//        int totalPart = shardingContext.getShardingTotalCount();
//        int partNo = shardingContext.getShardingItem();
//        log.info("{}开始，总共{}分片，当前分片{}", jobName, totalPart, partNo);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("TestQuartz02----" + sdf.format(new Date()));
//    }
//
//}
