package com.lifecycle.autotest.flow.handler;

import com.lifecycle.autotest.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *上传compliance check规则（在运行订单之前上传皆可）  上传文件到库
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 5:29
 */

@Component
@Slf4j
public class UploadCompileCheck2Db {

    @Autowired
    IOUtil ioUtil;

    public void handler(){
        // import compliance check/pos type/pos type maintenance：RU_POS_TYPE
        List<String> compileCheckUrlList = new ArrayList();
        for (String s : compileCheckUrlList) {
            ioUtil.uploadFile2Db(s);
        }

    }

}
