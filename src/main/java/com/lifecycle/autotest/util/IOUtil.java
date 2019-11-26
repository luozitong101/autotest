package com.lifecycle.autotest.util;


import com.jcraft.jsch.Session;
import com.lifecycle.autotest.config.EdiBelleConfig;
import com.lifecycle.autotest.config.TaskUrl;
import com.lifecycle.autotest.model.EdiParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-25 下午 3:04
 */
@Component
@Slf4j
public   class IOUtil {




    @Autowired
    private EdiBelleConfig ediBelleConfig;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskUrl taskUrl;

    @Autowired
    private Environment environment;

    public static String read(String path) throws Exception{
//        FileInputStream inputStream = new FileInputStream(new File(path));
//        BufferedOutputStream outputStream = new BufferedOutputStream(new Out);
//        org.apache.commons.io.IOUtil.copy(inputStream,outputStream);
        return null;
    }


    public static File read(String path,String ext) {

        return null ;
    }

    /**
     *  upload file to sftp server
     **/
    public  void uploadFileToSFTPServer(File file, EdiParams ediParams) {
        SftpInterface sftpInterface = new LockSftpUtil(ediBelleConfig.getSftpUser(), ediBelleConfig.getSftpPassword(), ediBelleConfig.getSftpDomain(), ediBelleConfig.getSftpPort(), new Properties());
        Session session = null;
        try {
            session = sftpInterface.createSession();
            SftpUploadHelp.uploadFileAndEof2Sftp(ediBelleConfig.isZip(), ediBelleConfig.getSftpUploadFilePath(), environment.getProperty("file.upload.rootpath") + ediBelleConfig.getSftpLocalFilePath(), file.getPath(), session, sftpInterface);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            sftpInterface.closeSession(session);
        }
    }


    public void uploadFile2Db(String filePath) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        // hard code
        form.add("filename","tm_mapping");

        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);

        String s = restTemplate.postForObject(taskUrl.getImportExcelUrl(), files, String.class);
        if (s != null) {
            log.info("upload tm mapping success,go next step");
        } else {
            log.error("upload tm mapping file failure,resason:{}",s);
        }
    }
}
