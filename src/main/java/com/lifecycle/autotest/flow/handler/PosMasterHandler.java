package com.lifecycle.autotest.flow.handler;


import com.lifecycle.autotest.config.FileLocation;
import com.lifecycle.autotest.flow.Operation;
import com.lifecycle.autotest.flow.enums.AdifoxTestFlowEnum;
import com.lifecycle.autotest.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * <p>
 *      pos master 文件 上传到指定sftp文件目录
 * </p>
 *
 * @author luoyong
 * @date 2019-11-25 下午 3:30
 */
public class PosMasterHandler implements Operation {

    private static final Logger logger = LoggerFactory.getLogger(PosMasterHandler.class);

    @Autowired
    IOUtil ioUtil;

    @Override
    public Object handler() {
        long start = System.currentTimeMillis();
        File file = new File(FileLocation.POS_MASTER_DATA_FILE_LOCAL_PATH);
        ioUtil.uploadFileToSFTPServer(file,null);
        logger.info("step code:{},desc:{},cost:{}", AdifoxTestFlowEnum.INTERFACE_POS_MASTER.getCode(),AdifoxTestFlowEnum.INTERFACE_POS_MASTER.getDesc(),(System.currentTimeMillis() - start)/1000);
        return null;
    }
}
