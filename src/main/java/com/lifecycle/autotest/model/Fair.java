package com.lifecycle.autotest.model;

import java.io.Serializable;

/**
 * @program: adiFox
 * @description: adifox订货会信息
 * @author: jiaox
 * @create: 2019-01-09 15:06
 **/
public class Fair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * adifox订货会id
     */
    private Long id;

    /**
     * 季度
     */
    private String season;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 订货会进行阶段【1 - blind buy、2 - early buy、3 - norm buy、4 - Late Added】
     */
    private String milestone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * Adiportal Tm Name
     */
    private String name;

    private String companyCode;

    private String rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}