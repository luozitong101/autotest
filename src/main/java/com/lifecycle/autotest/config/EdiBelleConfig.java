package com.lifecycle.autotest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     Account Belle 配置信息
 * </p>
 *
 * @author MrLee
 */
@Configuration
@Deprecated
public class EdiBelleConfig {

    @Value("${edi.belle.sftp.ck.domain}")
    private String sftpDomain;

    @Value("${edi.belle.sftp.ck.port}")
    private Integer sftpPort;

    @Value("${edi.belle.sftp.ck.user}")
    private String sftpUser;

    @Value("${edi.belle.sftp.ck.password}")
    private String sftpPassword;

    @Value("${edi.belle.sftp.ck.upload.path}")
    private String sftpUploadFilePath;

    @Value("${edi.belle.sftp.ck.local.path}")
    private String sftpLocalFilePath;

    @Value("${edi.belle.sftp.ck.iszip}")
    private boolean zip;

    public String getSftpDomain() {
        return sftpDomain;
    }

    public void setSftpDomain(String sftpDomain) {
        this.sftpDomain = sftpDomain;
    }

    public Integer getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(Integer sftpPort) {
        this.sftpPort = sftpPort;
    }

    public String getSftpUser() {
        return sftpUser;
    }

    public void setSftpUser(String sftpUser) {
        this.sftpUser = sftpUser;
    }

    public String getSftpPassword() {
        return sftpPassword;
    }

    public void setSftpPassword(String sftpPassword) {
        this.sftpPassword = sftpPassword;
    }

    public String getSftpUploadFilePath() {
        return sftpUploadFilePath;
    }

    public void setSftpUploadFilePath(String sftpUploadFilePath) {
        this.sftpUploadFilePath = sftpUploadFilePath;
    }

    public String getSftpLocalFilePath() {
        return sftpLocalFilePath;
    }

    public void setSftpLocalFilePath(String sftpLocalFilePath) {
        this.sftpLocalFilePath = sftpLocalFilePath;
    }

    public boolean isZip() {
        return zip;
    }

    public void setZip(boolean zip) {
        this.zip = zip;
    }
}
