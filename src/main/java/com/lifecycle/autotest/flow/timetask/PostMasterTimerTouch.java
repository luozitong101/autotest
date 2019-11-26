package com.lifecycle.autotest.flow.timetask;

import com.lifecycle.autotest.dao.TaskGroupDao;
import com.lifecycle.autotest.model.TaskGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *    posMaster 定时任务触发
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 2:50
 */
@Component
@Slf4j
public class PostMasterTimerTouch {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gateway.pos_master}")
    private String posMasterUrl;

    @Autowired
    TaskGroupDao taskGroupDao;

    public void touch() {

        ResponseEntity<String> response = restTemplate.getForEntity(posMasterUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String taskId = response.getBody();
            if (taskId != null ) {
                log.info("taskgroup id:{},pos master task is success add to task group ,waiting execute",taskId);
                // 轮训的查询task group 表中的status字段
                List<Long> param = new ArrayList<>();
                param.add(Long.parseLong(taskId));
                List<TaskGroup> taskGroups = taskGroupDao.findByIds(param);
                if (taskGroups!= null && taskGroups.size() > 0) {
                    boolean success = taskGroups.get(0).getStatus().equalsIgnoreCase("SUCCESS");
                    if (!success) {

                    } else {
                        log.info("taskgroup id:{},pos master is execute finish, status:{}",taskId,success);
                    }
                }
            }
        }

    }


}
