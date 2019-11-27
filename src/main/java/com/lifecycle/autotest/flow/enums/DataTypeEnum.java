package com.lifecycle.autotest.flow.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *    数据文件类型
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 5:45
 */
@Getter
public enum DataTypeEnum {


    POS_MASTER(1,"POS_MATER"),
    HUB_ARTICLE(2,"hub_article"),
    ZIP_MASTER(3,"zip_master"),
    ZIP_GENERAL(4,"zip_general"),
    ZIP_PRE_PRDER(5,"zip_pre_order"),
    ORDER(6,"order");


   private int code;
   private String desc;

    DataTypeEnum(int code,String desc) {
       this.code = code;
       this.desc = desc;
   }





}
