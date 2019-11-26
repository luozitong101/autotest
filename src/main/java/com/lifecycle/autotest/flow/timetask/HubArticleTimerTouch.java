package com.lifecycle.autotest.flow.timetask;

import com.lifecycle.autotest.config.TaskUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *HUB article 定时任务触发
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 3:22
 */
@Slf4j
@Component
public class HubArticleTimerTouch {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskUrl taskUrl;

    public void touch(){

        String url = taskUrl.getOriginalHostCompany()+taskUrl.getHubAttr();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        boolean success = response.getStatusCode() == HttpStatus.OK;
        if (success) {
            boolean complete = response.getBody().equalsIgnoreCase("Complete");
            if (complete) {
                log.info("hub attr task execute finish,go next step..");
            } else {
                log.info("hub attr task execute failure,reason:{}",response.getBody());

            }
        }

    }
}
