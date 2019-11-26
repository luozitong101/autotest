package com.lifecycle.autotest.model;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-02-28 17:17
 **/
public class EdiInterfaceConfigBeanImpl implements EdiConfigInterface {

    protected String sftpUsername;

    protected String sftpPassword;

    protected Integer sftpPort;

    protected String sftpDomain;

    protected String sftpScannerPath;

    protected String sftpBackPath;

    protected String localOrigPath;

    protected String localWorkPath;


    @Override
    public String getSftpUsername() {
        return sftpUsername;
    }

    public void setSftpUsername(String sftpUsername) {
        this.sftpUsername = sftpUsername;
    }

    @Override
    public String getSftpPassword() {
        return sftpPassword;
    }

    public void setSftpPassword(String sftpPassword) {
        this.sftpPassword = sftpPassword;
    }

    @Override
    public Integer getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(Integer sftpPort) {
        this.sftpPort = sftpPort;
    }

    @Override
    public String getSftpDomain() {
        return sftpDomain;
    }

    public void setSftpDomain(String sftpDomain) {
        this.sftpDomain = sftpDomain;
    }

    @Override
    public String getSftpScannerPath() {
        return sftpScannerPath;
    }

    public void setSftpScannerPath(String sftpScannerPath) {
        this.sftpScannerPath = sftpScannerPath;
    }

    @Override
    public String getSftpBackPath() {
        return sftpBackPath;
    }

    public void setSftpBackPath(String sftpBackPath) {
        this.sftpBackPath = sftpBackPath;
    }

    @Override
    public String getLocalOrigPath() {
        return localOrigPath;
    }

    public void setLocalOrigPath(String localOrigPath) {
        this.localOrigPath = localOrigPath;
    }

    @Override
    public String getLocalWorkPath() {
        return localWorkPath;
    }

    public void setLocalWorkPath(String localWorkPath) {
        this.localWorkPath = localWorkPath;
    }
}
