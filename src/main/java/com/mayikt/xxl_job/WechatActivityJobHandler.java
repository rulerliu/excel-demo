package com.mayikt.xxl_job;
 
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
 
/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/20 0020 下午 2:26
 * @version: V1.0
 */
@Component
@Slf4j
public class WechatActivityJobHandler {
 
    @XxlJob("wechatActivityJobHandler")
    public ReturnT<String> wechatActivityJobHandler(String param) {
//        ShardingUtil.ShardingVO shardingVo = ShardingUtil.getShardingVo();
//        int index = shardingVo.getIndex();
        log.info(">>>定时任务开始出发<<<param:{}", param);
        return ReturnT.SUCCESS;
    }
 
    @XxlJob("wechatActivityJobHandler222")
    public ReturnT<String> wechatActivityJobHandler222(String param) {
//        ShardingUtil.ShardingVO shardingVo = ShardingUtil.getShardingVo();
//        int index = shardingVo.getIndex();
        log.info(">>>wechatActivityJobHandler222定时任务开始出发<<<param:{}", param);
        return ReturnT.SUCCESS;
    }
 
}