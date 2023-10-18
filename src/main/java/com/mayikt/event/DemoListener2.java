package com.mayikt.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DemoListener2 {

    @EventListener(value = {DemoEvent.class}, condition = "#event.msg == 'liuwq'")
    @Async
    public void processApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        System.out.println(Thread.currentThread().getName() + " bean-listener2 收到了 publisher 发布的消息: " + msg);
    }

}