package com.mayikt.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println(Thread.currentThread().getName() + " bean-listener 收到了 publisher 发布的消息: " + msg);
    }
}