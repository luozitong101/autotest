package com.lifecycle.autotest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-03-01 10:26
 **/
public class ScannerEdiOrderDataPojo {

    /**
     * 当前account的類型
     */
//    protected AccountEnum accountEnum;
    protected EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum;

    protected String fileName;

    protected String eofFileName;

    private Pattern pattern;

    /**
     * belle  大区  : S
     */
    private String region;


    /**
     * channel : AS
     */
    private String channel;


    /**
     * 季节 : 2019Q3
     */
    private String seasonCode;


    /**
     * 版本:LB
     */
    private String mileStone;


    /**
     * 版本序列号 :3
     */
    private String mileStoneSeq;

    private String brand;

    private String comcode;

    private Date fileDate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public ScannerEdiOrderDataPojo(EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum, String fileName, String eofFileName) {

//        this.accountEnum = accountEnum;
        this.ediOrderInterfaceDataEnum=ediOrderInterfaceDataEnum;
        this.fileName = fileName;
        this.eofFileName = eofFileName;
        this.fileName = fileName;
        pattern = ediOrderInterfaceDataEnum.getPattern();
        handle(fileName);
    }

    private void handle(String fileName) {
        Matcher matcher = pattern.matcher(fileName);
        if (!matcher.matches()) {
            throw new RuntimeException("无法匹配指定正则:" + pattern.pattern() + ",文件名:" + fileName);
        }
        region = matcher.group("region");
        String tmSeason = matcher.group("tmSeason");
        //ad2019Q2
        channel = tmSeason.substring(0, tmSeason.length() - 6);
        seasonCode = tmSeason.substring(tmSeason.length() - 4);
        String milestoneAndSeq = matcher.group("milestoneAndSeq");
        mileStone = milestoneAndSeq.substring(0, 2);
        mileStoneSeq = milestoneAndSeq.substring(2);
        String time = matcher.group("time");

        List<String> channelList = Arrays.asList("AD", "AO", "AS", "AK");

        if (channelList.contains(channel)) {
            brand = "11";
            comcode = "9500";
        } else {
            brand = "26";
            comcode = "9850";
        }

        try {
            fileDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析文件名上的时间
     * ORDER_TOPSPORTS_E_AS2019Q3_LB3_20190219202501.csv
     *
     * @return
     */
    protected static Date parserFileDate(String fileName) {
        String[] list = fileName.split("_");
        String time = list[list.length - 1];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


//    public AccountEnum getAccountEnum() {
//        return accountEnum;
//    }
//
//    public void setAccountEnum(AccountEnum accountEnum) {
//        this.accountEnum = accountEnum;
//    }


    public EdiOrderInterfaceDataEnum getEdiOrderInterfaceDataEnum() {
        return ediOrderInterfaceDataEnum;
    }

    public void setEdiOrderInterfaceDataEnum(EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum) {
        this.ediOrderInterfaceDataEnum = ediOrderInterfaceDataEnum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEofFileName() {
        return eofFileName;
    }

    public void setEofFileName(String eofFileName) {
        this.eofFileName = eofFileName;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        this.seasonCode = seasonCode;
    }

    public String getMileStone() {
        return mileStone;
    }

    public void setMileStone(String mileStone) {
        this.mileStone = mileStone;
    }

    public String getMileStoneSeq() {
        return mileStoneSeq;
    }

    public void setMileStoneSeq(String mileStoneSeq) {
        this.mileStoneSeq = mileStoneSeq;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getComcode() {
        return comcode;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }
}
