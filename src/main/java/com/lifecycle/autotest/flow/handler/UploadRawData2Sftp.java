package com.lifecycle.autotest.flow.handler;

import com.lifecycle.autotest.config.FileLocation;
import com.lifecycle.autotest.dao.TaskGroupDao;
import com.lifecycle.autotest.flow.enums.AdifoxTestFlowEnum;
import com.lifecycle.autotest.model.TaskGroup;
import com.lifecycle.autotest.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *上传基础数据到sftp
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 5:14
 */
@Component
@Slf4j
public class UploadRawData2Sftp {

    @Autowired
    IOUtil ioUtil;

    @Autowired
    TaskGroupDao taskGroupDao;
    public void handler(){
        long start = System.currentTimeMillis();
        File file = new File(FileLocation.BASE_DATA_NKA_ORDER_LOCAL_PATH);
        ioUtil.uploadFileToSFTPServer(file,null);

        file = new File(FileLocation.BASE_DATA_GENERAL_LOCAL_PATH);
        ioUtil.uploadFileToSFTPServer(file,null);

        file = new File(FileLocation.BASE_DATA_MASTER_LOCAL_PATH);
        ioUtil.uploadFileToSFTPServer(file,null);

        log.info("step code:{},desc:{},cost:{}", AdifoxTestFlowEnum.UPLOAD_BASEDATA_SFTP.getCode(),AdifoxTestFlowEnum.UPLOAD_BASEDATA_SFTP.getDesc(),(System.currentTimeMillis() - start)/1000);

        // 运行master 任务
    //    interfaceZipController.allZipMaster();
        // 运行 general 任务
      //  interfaceZipController.allZipGeneral();
        // 运行NKAOorder 任务
   //     interfaceZipController.allZipPreOrder();
        List<Long> ids = new ArrayList<>();
        // 等待基础数据运行结束

        Set<Long> successtask = new HashSet<Long>();
        while(true) {
            List<TaskGroup> byIds = taskGroupDao.findByIds(ids);
            for (TaskGroup byId : byIds) {
                if (byId.getStatus().equalsIgnoreCase("SUCCESS")) {
                    successtask.add(byId.getId());
                }
            }
            if (successtask.size() == 3) {
                // 基础数据任务执行完成,执行下一步
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

}
