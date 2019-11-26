package com.lifecycle.autotest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: lifecycle
 * @description: ${description}
 * @author: weecj
 * @create: 2019-01-11 10:16
 **/
public class TaskGroup implements Serializable {

    private static final long serialVersionUID = -1;

    private Long id;

    /**
     * 任务组类型
     */
    private String groupType;

    /**
     * 任务组名称
     */
    private String groupName;

    /**
     * 任务状态
     *          未执行:M
     *          正在执行:I
     *          执行失败:E
     *          执行成功:S
     */
    private String status;

    /**
     * 日志路径
     */
    private String logPath;

    /**
     * 开始执行时间
     */
    private Date startDate;

    /**
     * 结束执行时间
     */
    private Date endDate;

    /**
     * 任务执行完成回调的bean
     */
    private String completeBean;

    /**
     * 并发执行数
     */
    private Integer concurrentNum = 5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getCompleteBean() {
        return completeBean;
    }

    public void setCompleteBean(String completeBean) {
        this.completeBean = completeBean;
    }

    public Integer getConcurrentNum() {
        return concurrentNum;
    }

    public void setConcurrentNum(Integer concurrentNum) {
        this.concurrentNum = concurrentNum;
    }
}