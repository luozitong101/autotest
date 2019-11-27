package com.lifecycle.autotest.flow;

import com.lifecycle.autotest.dao.SftpConfigDao;
import com.lifecycle.autotest.model.SftpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 5:50
 */
@Component
public class ReadSftpConfig {

    @Autowired
    SftpConfigDao sftpConfigDao;
    List<SftpConfig> configs = null;


    /**
     *   读取sftp配置
     **/
    public List<SftpConfig> readSftpConfig() {
        if (configs == null) {
            configs = sftpConfigDao.find();
        }
        return configs;
    }


}
