package com.lifecycle.autotest.model;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-03-01 11:10
 **/
public interface EdiConfigInterface {

    String getSftpUsername();


    String getSftpPassword();


    Integer getSftpPort();


    String getSftpDomain();


    String getSftpScannerPath();


    String getSftpBackPath();


    String getLocalOrigPath();


    String getLocalWorkPath();


}
