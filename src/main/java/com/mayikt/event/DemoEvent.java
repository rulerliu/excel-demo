package com.mayikt.event;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }



}
