package com.mayikt.elastic_job;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyScheduler implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    JobConfig jobConfig;
    @Autowired
    CoordinatorRegistryCenter regCenter;
    @Autowired
    MyJob1 myJob1;
    @Autowired
    MyJob2 myJob2;

    private static final String DELETE_NODE = "删除定时任务节点/";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(jobConfig.getBaseSleepTimeMilliseconds(),
                jobConfig.getMaxRetries());

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(jobConfig.getZkServerList(), retryPolicy)) {
            client.start();
            log.info(DELETE_NODE + jobConfig.getNamespace());
            Stat stat = client.checkExists().forPath("/" + jobConfig.getNamespace());
            if (stat != null) {
                client.delete().deletingChildrenIfNeeded().forPath("/" + jobConfig.getNamespace());
                log.info(DELETE_NODE + jobConfig.getNamespace() + "成功");
            }
            startMyJob1();
            startMyJob2();
        } catch (Exception e) {
            log.error(DELETE_NODE + jobConfig.getNamespace() + "失败", e);
        }

    }

    private JobScheduler getSpringJobScheduler(ElasticJob job, String jobName, String canonicalName, String cron,
                                               Integer totalPartNO) {
        return new SpringJobScheduler(job, regCenter, createJobConfiguration(jobName, canonicalName, cron, totalPartNO));
    }

    private LiteJobConfiguration createJobConfiguration(String jobName, String canonicalName, String cron, Integer totalPartNO) {
        log.info("创建作业配置开始");
        log.info("定义作业核心配置");

        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder(jobName, cron, totalPartNO)
                .build();
        log.info("定义SIMPLE类型配置");
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, canonicalName);
        log.info("定义Lite作业根配置");
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
        log.info("创建作业配置结束");
        return simpleJobRootConfig;
    }

    private void startMyJob1() {
        try{
            log.info("启动name-job1定时任务");
            JobScheduler jobScheduler = getSpringJobScheduler(myJob1, "name-job1",
                    MyJob1.class.getCanonicalName(), jobConfig.getJob1Cron(),
                    jobConfig.getJob1Shards());
            jobScheduler.init();
            log.info("启动name-job1定时任务完毕");
        }catch (Exception e){
            log.error("启动name-job1定时任务失败：{}", e.getMessage(), e);
        }
    }

    private void startMyJob2() {
        try{
            log.info("启动name-job2定时任务");
            JobScheduler jobScheduler = getSpringJobScheduler(myJob2, "name-job2",
                    MyJob1.class.getCanonicalName(), jobConfig.getJob2Cron(),
                    jobConfig.getJob2Shards());
            jobScheduler.init();
            log.info("启动name-job2定时任务完毕");
        }catch (Exception e){
            log.error("启动name-job2定时任务失败：{}", e.getMessage(), e);
        }
    }

}
