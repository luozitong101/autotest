package com.lifecycle.autotest.flow.timetask;

import com.lifecycle.autotest.flow.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *  自动生成sta article 运行portal定时任务 自动生成sta_article
 * </p>
 *
 * @author luoyong
 * @date 2019-11-25 下午 5:41
 */
@Component
@Slf4j
public class ProduceStaTimerTouch implements Operation {



    @Value("${adifox.originalHost_company}")
    private String originalHost;

    @Value("${adifox.sta_article_url}")
    private String staArticle ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ProduceStaTimerTouch(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Object handler() {
    //  自动生成sta_article定时任务
    // == hard code
        String url  = originalHost + staArticle;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("touch create_sta_article task success,waiting task finish....");
        long start = System.currentTimeMillis();
        if (response.getStatusCode() == HttpStatus.OK) {
            // sta_article 定时任务触发成功,等待执行完成
            if (response.getBody().equalsIgnoreCase("Complete")) {
                // 定时任务处理完成
                log.info("execute create_sta_article task finish,cost:{}",(System.currentTimeMillis()- start)/1000);
            } else {
                log.info("execute create_sta_article task fail");
            }

        }


        return null;
    }
}
