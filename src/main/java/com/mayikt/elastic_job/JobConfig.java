package com.mayikt.elastic_job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="job")
@Data
public class JobConfig {
    private Integer baseSleepTimeMilliseconds;
    private Integer maxSleepTimeMilliseconds;
    private Integer maxRetries;
    private String zkServerList;
    private String namespace;
    private Integer ftpTimeOut;
    private Integer perFileLineNumber;

    private Integer job1Shards;
    private String job1Cron;

    private Integer job2Shards;
    private String job2Cron;

}
