package com.lifecycle.autotest.model;

import java.util.Date;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-03-01 11:28
 **/
public interface ScannerInterface {

     String getFileName();

     Date getFileUploadDate();

     String getEofFileName();

     /**
      * 获取文件位置。可能是本地文件地址。可能是s3.或者其他
      * @return
      */
     String getFileLocation();



}
