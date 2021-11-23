package com.zookao.boot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationListener;

/**
 * User: zookao
 * Date: 2021-11-23
 */
@Slf4j
public class TestEventListener implements ApplicationListener<AvailabilityChangeEvent> {

    @Override
    public void onApplicationEvent(AvailabilityChangeEvent event) {
        log.info("监听到事件：" + event);
        if (ReadinessState.ACCEPTING_TRAFFIC == event.getState()){
            log.info("应用启动完成，可以请求了……");
        }
    }

}