package com.lifecycle.autotest.bean;

import com.lifecycle.autotest.flow.handler.LoginAdifox;
import com.lifecycle.autotest.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 上午 11:35
 */
@Component
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static Logger LOGGER = LoggerFactory
            .getLogger(CustomClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequestDetails(httpRequest);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }




    private void logRequestDetails(HttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        LoginAdifox loginAdifox = (LoginAdifox)SpringContextUtil.getBean("loginAdifox");
        if (loginAdifox.getCookies() != null) {
            List<String> cookies = loginAdifox.getCookies();
            headers.put(HttpHeaders.COOKIE,cookies);
        }
        LOGGER.info("Headers: {}", request.getHeaders());
        LOGGER.info("Request Method: {}", request.getMethod());
        LOGGER.info("Request URI: {}", request.getURI());
    }
}
