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
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-25 下午 3:38
 */
public class HubArticleHandler implements Operation {


    private static final Logger logger = LoggerFactory.getLogger(HubArticleHandler.class);

    @Autowired
    IOUtil ioUtil;

    @Override
    public Object handler() {
        long start = System.currentTimeMillis();
        File file = new File(FileLocation.HUB_ARTICLE_DATA_FILE_LOCAL_PATH);
        ioUtil.uploadFileToSFTPServer(file,null);
        logger.info("step code:{},desc:{},cost:{}", AdifoxTestFlowEnum.INTERFACE_HUB_ARTICLE.getCode(),AdifoxTestFlowEnum.INTERFACE_HUB_ARTICLE.getDesc(),(System.currentTimeMillis() - start)/1000);
        return null;
    }
}
