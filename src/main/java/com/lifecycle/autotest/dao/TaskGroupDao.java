package com.lifecycle.autotest.dao;

import com.lifecycle.autotest.model.TaskGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: adiFox
 * @description: ${description}
 * @author: weecj
 * @create: 2019-01-11 10:52
 **/
@Component("taskGroupDao")
public interface TaskGroupDao {


    public void insert(TaskGroup taskGroup);

    public List<TaskGroup> find(@Param("param") Map<String, Object> param, @Param("start") int start, @Param("end") int end);

    public void update(TaskGroup taskGroup);

    public TaskGroup selectById(@Param("id") Long id);

    public int selectByType(@Param("groupType") String groupType);

    List<TaskGroup> findByIds(@Param("param") List<Long> param);
}