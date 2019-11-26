package com.lifecycle.autotest.model;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * EdiBusinessServiceImpl 入参对象之一
 * </p>
 */
public class EdiParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date taskStartTime;
    private Long taskId;
    private String fileName;
    private String filePath;
    private Fair fair;
    private TaskLog taskLog;
    private AccountEnum accountEnum;

    /**
     * <p>
     * 文件名中的类似于AD2019Q2的字符串
     * </p>
     */
    private String adTmSeason;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAdTmSeason() {
        return adTmSeason;
    }

    public void setAdTmSeason(String adTmSeason) {
        this.adTmSeason = adTmSeason;
    }

    public Fair getFair() {
        return fair;
    }

    public void setFair(Fair fair) {
        this.fair = fair;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TaskLog getTaskLog() {
        return taskLog;
    }

    public void setTaskLog(TaskLog taskLog) {
        this.taskLog = taskLog;
    }

    public AccountEnum getAccountEnum() {
        return accountEnum;
    }

    public void setAccountEnum(AccountEnum accountEnum) {
        this.accountEnum = accountEnum;
    }
}
