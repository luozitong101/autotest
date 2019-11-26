package com.lifecycle.autotest.flow.timetask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 2:44
 */
@Component
@Slf4j
public abstract class AbstractTouch implements Touch {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void call() {

    }

    @Override
    public void callBack() {

    }


     void doit() {
        call();
        callBack();
     }
}
