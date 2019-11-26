package com.lifecycle.autotest.model;

import java.util.regex.Pattern;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-03-01 10:27
 **/
public class EdiOrderInterfaceDataEnum implements ScannerEnumInterface {


//    /**
//     * 扫描的belle文件
//     * ORDER_TOPSPORTS_S_AD2019Q3_EB1_20190304145529.zip
//     */
//    BELLE(Pattern.compile("ORDER_TOPSPORTS_(?<region>[\\s\\S]*)_(?<tmSeason>[\\s\\S]*)_(?<milestoneAndSeq>[\\s\\S]*)_(?<time>[0-9]{14})[\\s\\S]*")),
//    /**
//     * ORDER_SSCY_S_AD2019Q3_EB1_20190304145529.zip
//     */
//    SSCY(Pattern.compile("ORDER_SSCY_(?<region>[\\s\\S]*)_(?<tmSeason>[\\s\\S]*)_(?<milestoneAndSeq>[\\s\\S]*)_(?<time>[0-9]{14})[\\s\\S]*")),
//    /**
//     * ORDER_YY_S_AD2019Q3_EB1_20190304145529.zip
//     */
//    YY(Pattern.compile("ORDER_YY_(?<region>[\\s\\S]*)_(?<tmSeason>[\\s\\S]*)_(?<milestoneAndSeq>[\\s\\S]*)_(?<time>[0-9]{14})[\\s\\S]*"));

    public EdiOrderInterfaceDataEnum(Pattern pattern) {
        this.pattern = pattern;
    }


    /**
     * 接口匹配正则表达式
     */
    private Pattern pattern;

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }}
