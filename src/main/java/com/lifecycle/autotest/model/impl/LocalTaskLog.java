package com.lifecycle.autotest.model.impl;


import com.lifecycle.autotest.model.TaskLog;
import com.lifecycle.autotest.util.DateUtil;
import com.lifecycle.autotest.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * @program: adiFox
 * @description: ${description}
 * @author: weecj
 * @create: 2019-01-17 14:04
 **/
public class LocalTaskLog implements TaskLog {

    private Logger logger = LoggerFactory.getLogger(LocalTaskLog.class);

    private final String rootPath;
    private FileOutputStream infoWriter;
    private PrintWriter  info;

    private FileOutputStream debugWriter;
    private PrintWriter debug;

    private FileOutputStream warnWriter;
    private PrintWriter warn;

    private FileOutputStream errorWriter;
    private PrintWriter error;

    private String dataForamet = DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS;


    public LocalTaskLog(String rootPath) {
        this.rootPath = rootPath;

        File rootPathFile = new File(rootPath);
        if (!rootPathFile.exists()) {
            rootPathFile.mkdirs();
        }
        String fileName = StringUtil.getRand(8, StringUtil.character);
        fileName += "_";
        fileName += DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);

        try {
            File file = new File(rootPath + File.separator + fileName + ".info.log");
            file.createNewFile();
            infoWriter = new FileOutputStream(file);
            info=new PrintWriter(new BufferedWriter(new OutputStreamWriter(infoWriter,"UTF-8")));
        } catch (Exception e) {
        }
        try {
            File file = new File(rootPath + File.separator + fileName + ".warn.log");
            file.createNewFile();
            warnWriter = new FileOutputStream(file);
            warn = new PrintWriter(new BufferedWriter(new OutputStreamWriter(warnWriter,"UTF-8")));
        } catch (Exception e) {
        }

        try {
            File file = new File(rootPath + File.separator + fileName + ".debug.log");
            file.createNewFile();
            debugWriter = new FileOutputStream(file);
            debug = new PrintWriter(new BufferedWriter(new OutputStreamWriter(debugWriter,"UTF-8")));
        } catch (Exception e) {
        }

        try {
            File file = new File(rootPath + File.separator + fileName + ".error.log");
            file.createNewFile();
            errorWriter = new FileOutputStream(file);
            error = new PrintWriter(new BufferedWriter(new OutputStreamWriter(errorWriter,"UTF-8")));
        } catch (Exception e) {
        }
    }

    @Override
    public synchronized void info(long level, String log) {
        if (!StringUtil.isTrimEmpty(log) && info != null) {
            StringBuffer stringBuffer = new StringBuffer("lifecycle:");
            stringBuffer.append(DateUtil.parseDateToStr(new Date(), dataForamet));
            stringBuffer.append("_");
            stringBuffer.append(level);
            stringBuffer.append("     ");
            stringBuffer.append(log).append("\n");
            try {
                info.write(stringBuffer.toString());
                info.flush();
            } catch (Exception e) {
                logger.error("写入文件失败:" + e.getMessage(), e);
            }
        }
    }

    @Override
    public synchronized void debug(long level, String log) {
        if (!StringUtil.isTrimEmpty(log) && debug != null) {
            StringBuffer stringBuffer = new StringBuffer("lifecycle:");
            stringBuffer.append(DateUtil.parseDateToStr(new Date(), dataForamet));
            stringBuffer.append("_");
            stringBuffer.append(level);
            stringBuffer.append("     ");
            stringBuffer.append(log).append("\n");
            try {
                debug.write(stringBuffer.toString());
                debug.flush();
            } catch (Exception e) {
                logger.error("写入文件失败:" + e.getMessage(), e);
            }
        }
    }

    @Override
    public synchronized void debug(long level, Throwable throwable) {
        if (throwable != null && debug != null) {
            StringBuffer stringBuffer = new StringBuffer("lifecycle:");
            stringBuffer.append(DateUtil.parseDateToStr(new Date(), dataForamet));
            stringBuffer.append("_");
            stringBuffer.append(level);
            stringBuffer.append("     ");
            stringBuffer.append("\n");
            try {
                debug.write(stringBuffer.toString());
                getExceptionMsg(throwable, debug);
                debug.flush();
            } catch (Exception e) {
                logger.error("写入文件失败:" + e.getMessage(), e);
            }
        }
    }


    public void getExceptionMsg(Throwable throwable, PrintWriter bufferedWriter) {
        if (throwable == null) {
            return;
        }
        throwable.printStackTrace(bufferedWriter);
    }


    @Override
    public synchronized void warn(long level, String log) {
        if (!StringUtil.isTrimEmpty(log) && warn != null) {
            StringBuffer stringBuffer = new StringBuffer("lifecycle:");
            stringBuffer.append(DateUtil.parseDateToStr(new Date(), dataForamet));
            stringBuffer.append("_");
            stringBuffer.append(level);
            stringBuffer.append("     ");
            stringBuffer.append(log).append("\n");
            try {
                warn.write(stringBuffer.toString());
                warn.flush();
            } catch (Exception e) {
                logger.error("写入文件失败:" + e.getMessage(), e);
            }
        }
    }

    @Override
    public synchronized void error(long level, String log) {
        if (!StringUtil.isTrimEmpty(log) && error != null) {
            StringBuffer stringBuffer = new StringBuffer("lifecycle:");
            stringBuffer.append(DateUtil.parseDateToStr(new Date(), dataForamet));
            stringBuffer.append("_");
            stringBuffer.append(level);
            stringBuffer.append("     ");
            stringBuffer.append(log).append("\n");
            try {
                error.write(stringBuffer.toString());
                error.flush();
            } catch (Exception e) {
                logger.error("写入文件失败:" + e.getMessage(), e);
            }
        }
    }

    @Override
    public void info(String log) {
        info(99, log);
    }


    @Override
    public void debug(String log) {
        debug(99, log);
    }

    @Override
    public void debug(Throwable throwable) {
        debug(99, throwable.getLocalizedMessage());
    }

    @Override
    public void warn(String log) {
        warn(99, log);
    }

    @Override
    public void error(String log) {
        error(99, log);
    }

    public void close() {
        if (info != null) {
            try {
                info.close();
            } catch (Exception e) {
            }
        }

        if (debug != null) {
            try {
                debug.close();
            } catch (Exception e) {
            }
        }

        if (warn != null) {
            try {
                warn.close();
            } catch (Exception e) {
            }
        }

        if (error != null) {
            try {
                error.close();
            } catch (Exception e) {
            }
        }

        if (infoWriter != null) {
            try {
                infoWriter.close();
            } catch (Exception e) {
            }
        }

        if (debugWriter != null) {
            try {
                debugWriter.close();
            } catch (Exception e) {
            }
        }

        if (warnWriter != null) {
            try {
                warnWriter.close();
            } catch (Exception e) {
            }
        }

        if (errorWriter != null) {
            try {
                errorWriter.close();
            } catch (Exception e) {
            }
        }
    }
}