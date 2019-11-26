package com.lifecycle.autotest.model;

/**
 * @program: adiFox
 * @description: ${description}
 * @author: weecj
 * @create: 2019-01-11 09:55
 **/
public interface TaskLog {

    /**
     * 日志级别
     * 0:info、debug、warn、error
     * 1:debug、warn、error
     * 2:warn、error
     * 3:error
     */

    public void info(long level, String log);

    public void debug(long level, String log);

    public void debug(long level, Throwable throwable);

    public void warn(long level, String log);

    public void error(long level, String log);

    public void info(String log);

    public void debug(String log);

    public void debug(Throwable throwable);

    public void warn(String log);

    public void error(String log);

}