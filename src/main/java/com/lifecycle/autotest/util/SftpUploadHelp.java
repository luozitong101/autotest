package com.lifecycle.autotest.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.jcraft.jsch.ChannelSftp.APPEND;
import static com.lifecycle.autotest.util.SftpInterface.uploadLocalFile2SFTP;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-02-17 17:28
 **/
public class SftpUploadHelp {

    static Logger logger = LoggerFactory.getLogger(SftpUploadHelp.class);

    /**
     * 上传文件到sftp指定地点,还有eof
     *
     * @param isZip        当前文件是否需要压缩
     * @param sftpPath     需要上传的sftp路径
     * @param tempPath     临时文件的生成目录,用来生成压缩文件
     * @param fileFullPath 需要上传的文件全路径
     * @throws Exception 上传出错了
     */
    public static void uploadFileAndEof2Sftp(Boolean isZip, String sftpPath, String tempPath, String fileFullPath, Session session, SftpInterface sftpInterface) throws Exception {
        File file = new File(fileFullPath);
        if (!file.exists()) {
            logger.info("当前文件不存在,无法上传:" + fileFullPath);
            return;
        }
        String fileName = file.getName();
        String zipName = null;
        String zipPath = null;
        if (isZip) {
            int lastIndexOf = fileName.lastIndexOf(".");
            //有后缀 去除后缀 拼接 zip
            if (lastIndexOf != -1) {
                zipName = fileName.substring(0, lastIndexOf + 1) + "zip";
            } else { //没有后缀 直接加.zip
                zipName = fileName + ".zip";
            }
            generateZIP(fileName, fileFullPath, tempPath, zipName);
            zipPath = tempPath + zipName;
        } else {
            zipName = fileName;
            zipPath = tempPath + fileName;
        }

        //上传文件
        uploadLocalFile2SFTP(session, sftpInterface, zipPath, sftpPath, zipName);
        //上传eof文件
        uploadCompletedByEof(session, sftpInterface, sftpPath, zipName, tempPath);
    }

    /**
     * 原路径，目标路径
     */
    public static void generateZIP(String fileName, String sourceFilePath, String zipPath, String zipName) throws
            Exception {
        FileOutputStream outputStream = null;
        ZipOutputStream out = null;
        FileInputStream fis = null;
        try {
            File targetFile = new File(zipPath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            outputStream = new FileOutputStream(zipPath + zipName);
            out = new ZipOutputStream(new BufferedOutputStream(outputStream));
            fis = new FileInputStream(sourceFilePath);
            out.putNextEntry(new ZipEntry(fileName));
            //进行写操作
            int j = 0;
            byte[] buffer = new byte[2048];
            while ((j = fis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                //关闭输入流
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 通过创建eof文件告知上传完毕
     * 只能在本地生成完毕后传到sftp上
     */
    public static void uploadCompletedByEof(Session session, SftpInterface sftpInterface, String sftpPath, String fileName, String tempPath) throws Exception {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = sftpInterface.createSFTPChannel(session);
            String localFilePath = null;
            int lastIndexOf = fileName.lastIndexOf(".");
            String sftpEofPath = null;
            if (lastIndexOf != -1) {
                sftpEofPath = sftpPath + fileName.substring(0, lastIndexOf + 1) + "eof";
                localFilePath = tempPath + fileName.substring(0, lastIndexOf + 1) + "eof";
            } else {
                sftpEofPath = sftpPath + fileName + ".eof";
                localFilePath = tempPath + fileName + ".eof";
            }
            if (!SftpInterface.isDirExist(sftpPath, channelSftp)) {
                SftpInterface.createDir(sftpPath, channelSftp);
            }
            File file = new File(tempPath);
            if (!file.exists()) {//路径
                if (file.mkdirs()) {
                    file = new File(localFilePath);
                    if (!file.exists()) {
                        boolean re = file.createNewFile();
                        if (!re) {
                            throw new Exception("create file fail");
                        }
                    }
                }

            } else {
                file = new File(localFilePath);
                if (file.exists()) {
                    if (file.delete()) {
                        if (!file.createNewFile()) {
                            throw new Exception("create file fail");
                        }
                    }
                } else {
                    if (!file.createNewFile()) {
                        throw new Exception("create file fail");
                    }
                }

            }

            if (SftpInterface.isFileExist(sftpEofPath, channelSftp)) {
                channelSftp.rm(sftpEofPath);
                channelSftp.put(localFilePath, sftpEofPath, APPEND);
            } else {
                channelSftp.put(localFilePath, sftpEofPath, APPEND);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                sftpInterface.closeChannel(channelSftp, false);
            }
        }

    }
}
