package com.lifecycle.autotest.flow.handler;

import com.lifecycle.autotest.model.LoginResponse;
import com.lifecycle.autotest.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *    模拟登陆adifox系统
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 上午 11:18
 */
@Component
public class LoginAdifox {
    private static final Logger logger = LoggerFactory.getLogger(LoginAdifox.class);

    @Autowired
    private RestTemplate restTemplate;

    private List<String> cookies;

    public List<String> getCookies() {
        return cookies;
    }



    public String login(String username,String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        String value = "[{\"id\":1,\"command\":\"com.agilecontrol.portal7.AdminPanelCmd\",\"params\":{\"username\":\"root\",\"password\":\"adifoxprod\",\"langid\":\"\",\"langcode\":\"zh\",\"piccode\":\"\",\"cmd\":\"portal7.Login\"}}]";
        map.add("transactions", value);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity("", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("login success,response:{}",response.getBody());
            HttpHeaders headers1 = response.getHeaders();
            List<String> cookies = headers1.get("Set-Cookie");
            this.cookies = cookies;

        }
        return null ;

    }


}
