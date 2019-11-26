package com.lifecycle.autotest.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 下午 3:35
 */
@Configuration
@Data
@Getter
@Setter
public class TaskUrl {

    @Value("${adifox.originalHost_company}")
    private String originalHostCompany;

    @Value("${adifox.sta_article_url}")
    private String  staArticleUrl;

    @Value("${adifox.hub_attr}")
    private String hubAttr;

    @Value("${adifox.tm_mapping}")
    private String tmMapping;

    @Value("${adifox.exportExcel}")
    private String importExcelUrl;

    @Value("${gateway.compile_check}")
    private String compileCheckUrl;

}
