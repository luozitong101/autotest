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
 * 运行刷缓存任务：REFRESH REDIS COMPLIANCE CHECK
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 5:49
 */
@Component
@Slf4j
public class RefreshCacheTimerTask {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskUrl taskUrl;
    public void touch() {

        ResponseEntity<String> response = restTemplate.getForEntity(taskUrl.getCompileCheckUrl(), String.class);
        boolean b = response.getStatusCode() == HttpStatus.OK;
        if (b) {
            String taskId = response.getBody();
            log.info("compile check  redis cache task touch success,waiting for task finish");

        }
    }

}
