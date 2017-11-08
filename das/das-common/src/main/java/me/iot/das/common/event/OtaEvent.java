package me.iot.das.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by sylar on 16/6/1.
 */
public class OtaEvent extends ApplicationEvent {
    public OtaEvent(Object source) {
        super(source);
    }
}
