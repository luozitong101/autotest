package com.lifecycle.autotest.util;

import com.jcraft.jsch.*;

/**
 * sftp连接接口
 * 有的是需要池化的，有的连接数有限制，统一成一个接口
 *
 * @author jin.weimin@lifecycle.cn
 * created in 2019-01-09 14:46
 */
public interface SftpInterface {

    /**
     * 创建一个文件目录
     */
    public static void createDir(String createPath, ChannelSftp sftp) throws Exception {
        //回到初始目录
        sftp.cd("/");
        if (isDirExist(createPath, sftp)) {
        } else {
            String[] pathArray = createPath.split("/");
            for (String path : pathArray) {
                if ("".equals(path)) {
                    continue;
                }
                if (isDirExist(path, sftp)) {
                    sftp.cd(path);
                    if (isDirExist(createPath, sftp)) {
                    }
                } else {
                    // 建立目录
                    sftp.mkdir(path);
                    // 进入并设置为当前目录
                    sftp.cd(path);
                }
            }
        }


    }

    /**
     * 判断目录是否存在
     */
    public static boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        String noSuchFile = "no such file";
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (noSuchFile.equals(e.getMessage().toLowerCase())) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 判断远程文件是否存在
     *
     * @param srcSftpFilePath
     * @return
     * @throws SftpException
     */
    public static boolean isFileExist(String srcSftpFilePath, ChannelSftp channelSftp) throws SftpException {
        boolean isExitFlag = false;
        // 文件大于等于0则存在文件
        if (getFileSize(srcSftpFilePath, channelSftp) >= 0) {
            isExitFlag = true;
        }
        return isExitFlag;
    }

    /**
     * 得到远程文件大小
     *
     * @param srcSftpFilePath
     * @return 返回文件大小，如返回-2 文件不存在，-1文件读取异常
     * @throws SftpException
     */
    static long getFileSize(String srcSftpFilePath, ChannelSftp channelSftp) throws SftpException {
        //文件大于等于0则存在
        long fileSize = 0;
        String noSuchFile = "no such file";
        try {
            SftpATTRS sftpATTRS = channelSftp.lstat(srcSftpFilePath);
            fileSize = sftpATTRS.getSize();
        } catch (Exception e) {
            //获取文件大小异常
            fileSize = -1;
            if (noSuchFile.equals(e.getMessage().toLowerCase())) {
                //文件不存在
                fileSize = -2;
            }
        }
        return fileSize;
    }

    /**
     * 上传本地文件到sftp
     * @param session       session
     * @param sftpInterface 接口
     * @param fileFullPath  本地文件全路径
     * @param sftpPath      要上传的sftp路径
     * @param sftpFileName  要上传的sftp的文件名称
     * @throws Exception
     */
    static void uploadLocalFile2SFTP(Session session, SftpInterface sftpInterface, String fileFullPath, String sftpPath, String sftpFileName) throws Exception {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = sftpInterface.createSFTPChannel(session);
            SftpInterface.createDir(sftpPath, channelSftp);
            channelSftp.put(fileFullPath, sftpPath + sftpFileName);
        } catch (Exception e) {
            throw e;
        } finally {
            sftpInterface.closeChannel(channelSftp, false);
        }

    }


    /**
     * 创建session,从session中创建sftp channel
     *
     * @return the instance of <code>Session</code> class.
     * @throws JSchException if <code>username</code> or <code>host</code> are invalid.
     */
    Session createSession() throws Exception;

    /**
     * 创建sftp channel
     *
     * @return channel中对文件进行操作
     * @throws JSchException if <code>username</code> or <code>host</code> are invalid.
     */
    ChannelSftp createSFTPChannel() throws Exception;


    /**
     * @param session
     * @return
     * @throws Exception
     */
    ChannelSftp createSFTPChannel(Session session) throws Exception;

    /**
     * 关闭channel，默认关闭session
     *
     * @param channel channel
     */
    void closeChannel(Channel channel);

    /**
     * 关闭channel
     *
     * @param channel      channel
     * @param closeSession 是否关闭session
     */
    void closeChannel(Channel channel, boolean closeSession);

    /**
     * 关闭session
     *
     * @param session session
     */
    void closeSession(Session session);
}
