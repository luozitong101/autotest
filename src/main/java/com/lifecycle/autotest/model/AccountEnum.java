package com.lifecycle.autotest.model;


import java.util.function.BiFunction;

/**
 * @author jin.weimin@lifecycle.cn
 * Created by 2019-02-20 17:43
 **/
public class AccountEnum {

//    /**
//     * belle
//     */
//    BELLE("BELLE", "edi_belle", EdiOrderInterfaceDataEnum.BELLE, getServiceName(EdiServiceImpl.class)),
//    /**
//     * sscy
//     */
//    SSCY("SSCY", "edi_sscy", EdiOrderInterfaceDataEnum.SSCY, getServiceName(EdiServiceImpl.class)),
//    /**
//     * yy
//     */
//    YY("YY", "edi_yy", EdiOrderInterfaceDataEnum.SSCY, getServiceName(EdiServiceImpl.class));

    public AccountEnum(String name, EdiInterfaceConfigBeanImpl ediConfigBeanName, EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum, String beanName) {
        this.name = name;
        this.beanName = beanName;
        this.ediOrderInterfaceDataEnum = ediOrderInterfaceDataEnum;
        this.ediConfigInterface = ediConfigBeanName;

    }

    public BiFunction<String, String, ScannerEdiOrderDataPojo> getScannerPojoFunction() {
//        switch (this) {
//            case BELLE:
//                return (fileName, eofFileName) -> (ScannerEdiOrderDataPojo) new ScannerEdiBellePojo(fileName, eofFileName, BELLE);
//            case SSCY:
//                return (fileName, eofFileName) -> (ScannerEdiOrderDataPojo) new ScannerEdiSscyPojo(fileName, eofFileName, SSCY);
//            case YY:
//                return (fileName, eofFileName) -> (ScannerEdiOrderDataPojo) new ScannerEdiYyPojo(fileName, eofFileName, YY);
//            default:
//                throw new RuntimeException("新增了account。沒有在代碼裏加上");
//        }
        return (fileName, eofFileName) -> new ScannerEdiOrderDataPojo(this.getEdiOrderInterfaceDataEnum(),fileName, eofFileName);
    }

    /**
     * account 名称
     */
    private String name;

    private String beanName;

    /**
     * 每個edi的配置bean
     */
    private EdiConfigInterface ediConfigInterface;


    /**
     * 每個edi的掃描bean
     */
    private ScannerInterface scannerInterface;


    private EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EdiConfigInterface getEdiConfigInterface() {
        return ediConfigInterface;
    }

    public void setEdiConfigInterface(EdiConfigInterface ediConfigInterface) {
        this.ediConfigInterface = ediConfigInterface;
    }

    public ScannerInterface getScannerInterface() {
        return scannerInterface;
    }

    public void setScannerInterface(ScannerInterface scannerInterface) {
        this.scannerInterface = scannerInterface;
    }

    public EdiOrderInterfaceDataEnum getEdiOrderInterfaceDataEnum() {
        return ediOrderInterfaceDataEnum;
    }

    public void setEdiOrderInterfaceDataEnum(EdiOrderInterfaceDataEnum ediOrderInterfaceDataEnum) {
        this.ediOrderInterfaceDataEnum = ediOrderInterfaceDataEnum;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }}
