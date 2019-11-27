package com.lifecycle.autotest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *   sftp config 配置
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 5:38
 */

@Data
@Getter
@Setter
public class SftpConfig {

    private int id;

    private String dataType;

    private String dataName;

    private String environment;

    private String sftpIp;

    private String sftpPort;

    private String sftpUsername;

    private String sftpPassword;

    private String sftpFileOrigPath;

    private boolean isActive;

}
