package com.lifecycle.autotest.flow.handler;

import com.lifecycle.autotest.config.FileLocation;
import com.lifecycle.autotest.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


/**
 * <p>
 *维护TM MAPPING表 在trade meeting admin/trade meeting/TM mapping表中上传链接中的文件（import按钮上传或Create）
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 4:08
 */

@Component
@Slf4j
public class UploadTmMapping {

  @Autowired
  IOUtil ioUtil;




    public void touch() {
        //设置请求头
        ioUtil.uploadFile2Db(FileLocation.ADIFOX_TM_MAPPING);


    }



}
