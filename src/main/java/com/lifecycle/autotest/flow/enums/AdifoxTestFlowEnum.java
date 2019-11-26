package com.lifecycle.autotest.flow.enums;


import com.lifecycle.autotest.flow.Operation;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-25 下午 2:15
 */
public enum AdifoxTestFlowEnum {
    /**
     * 修改数据库中接口数据的modifieddate 执行文件中的sql
     **/
    INIT_SQL("1"," 修改数据库中接口数据的modifieddate 执行文件中的sql",null),
    /**
     *接口任务（pos master数据）
     **/
    INTERFACE_POS_MASTER("1","链接中的文件放到制定的SFTP目录,运行gateway任务",null),
    /**
     * 接口任务（HUB article数据）
     **/
    INTERFACE_HUB_ARTICLE("1","链接中的文件放到制定的SFTP目录,运行portal定时任务：HUB 商品属性",null),
    /**
     *维护TM MAPPING表
     **/
    MAINTAIN_TM_MAPOING("1","在trade meeting admin/trade meeting/TM mapping表中上传链接中的文件（import按钮上传）",null),
    /**
     *上传基础数据到sftp
     **/
    UPLOAD_BASEDATA_SFTP("1","把链接中的文件放到SFTP,运行master任务,运行general任务,运行NKAorder任务",null),
    /**
     * 生成sta article
     **/
    PRODUCE_STA_ARTICLE("1","生成sta article,运行portal定时任务,自动生成STA_ARTICLE定时任务",null),
    /**
     *上传compliance check规则 将链接中的文件上传到对应的目录
     * compliance check/pos type/pos type maintenance：RU_POS_TYPE
     * compliance check/pos type/Pos Type Alias Mapping：POS_TYPE_ALIAS_MAPPING
     * compliance check/pos type/Concept Name Mapping：CONCEPT_NAME_MAPPING
     * compliance chekc/mandatory/Flex TGT：RU_MANDATORY_FLEX
     * compliance chekc/mandatory/mandatory pacakge upload：mandatory package文件夹下的所有文件
     * compliance chekc/mandatory/Vertical POS Package Mapping List Upload：vertical mapping list文件夹下的所有文件
     * compliance chekc/assorment/MOQ Setting：ASSORTMENT_MOQ
     * compliance chekc/assorment/assorment pacakge upload：assortment pacakge文件夹下的所有文件
     **/
    UPLOAD_COMPILANCE_CHCEK("1","上传compliance check规则,将链接中的文件上传到对应的目录",null),

    /**
     *发sta range,article,article list,pos&sta到sftp,在system mgmt/security/EDI send表中勾选要发送的订货会数据点击send data按钮进行发送数据（一次可以发送的数据不超过50个
     **/
    SEND_BASE_DATA_SFTP("1","发sta range,article,article list,pos&sta到sftp,在system mgmt/security/EDI send表中勾选要发送的订货会数据点击send data按钮进行发送数据（一次可以发送的数据不超过50个",null),
    /**
     * account 根据发送的数据开始做单
     **/
    ACCOUNT_PALCE_ORDER_BY_RECEIVE_DATA("1","根据发送的数据开始做单",null),
    /**
     *上传订单到sftp 把链接中的订单数据放到对应的SFTP目录
     **/
    UPLOAD_ORDER_SFTP("1","上传订单到sftp 把链接中的订单数据放到对应的SFTP目录",null),
    /**
     *adifox从sftp读取订单
     **/
    READ_ORDER_SFTP("1","adifox从sftp读取订单,在trade meeting admin/trade meeting/adifox tm window勾选订货会30695 30713 30714，点击order merge按钮",null),
    /**
     *check result通过，adifox邮件通知销售批单
     **/
    CHECK_RESULT_PASS_SEND_EMAIL("1","check result通过，adifox邮件通知销售批单,等待邮件发送",null),
    /**
     *销售驳回订单
     **/
    SALES_REJECT_ORDER("1","销售驳回订单,在trade meeting order/EDI order/order progress勾选订货会30695 30713 30714下的订单，点击unfrozen按钮",null),
    /**
     *发送check result到sftp
     **/
    SEND_CHECK_RESULT_SFTP("1","发送check result到sftp,等待portal定时任务运行：SFTP上传-CHECKRESULT",null),
    /**
     *BELLE重新做单,发送订单到sftp
     **/
    AGAIN_PLACE_ORDER("1","BELLE重新做单,发送订单到sftp,把链接中的订单数据放到对应的SFTP目录",null),
    /**
     *adifox接受订单,通过check result,通知销售批单
     **/
    CHECK_RESULT_SUCCESS_NOTIFY_SALE_APPROVE("1","adifox接受订单,通过check result,通知销售批单,在trade meeting admin/trade meeting/adifox tm window勾选订货会30695 30713 30714，点击order merge按钮",null),
    /**
     *销售审批通过订单
     **/
    SALE_APPROVE_ORDER("1","销售审批通过订单,在trade meeting order/EDI order/order progress勾选订货会30695 30713 30714下的订单，点击approve按钮",null),
    /**
     *adiportal发unfreeze&drop给adifox,把链接中的文件放到SFTP,运行master任务
     **/
    ADIPORTAL_SEND_UNFREEZE_DROP_TO_ADIFOX("1","adiportal发unfreeze&drop给adifox,把链接中的文件放到SFTP,运行master任务",null),
    /**
     *生成sta article运行portal定时任务：自动生成STA_ARTICLE定时任务
     *      **/
    PRODUCE_STA_ARTICLE_UNFREEZE("1","生成sta article运行portal定时任务：自动生成STA_ARTICLE定时任务",null),
    /**
     *adifox将unfreeze的结果发送到sftp
     **/
    ADIFOX_SEND_UNFREEZE_RESULT_SFTP("1","difox将unfreeze的结果发送到sftp",null),
    /**
     *接收变动数据，重新调整订单
     **/
    ACCOUNT_RECEIVE_CHANGE_RE_ORDER("1","ACCOUNT 接收变动数据，重新调整订单",null),
    /**
     *重新发送订单到sftp
     **/
    AGAIN_SEND_ORDER_SFTP("1","重新发送订单到sftp",null),

    /**
     *adifox check订单,系统check不在unfreeze范围内,生成check result给belle.等待portal定时任务运行：SFTP上传-CHECKRESULT
     **/
    CHECK_NOT_IN_UNFREEZE_CHECK_RESULT_SFTP("1","adifox check订单,系统check不在unfreeze范围内,生成check result给belle.等待portal定时任务运行：SFTP上传-CHECKRESULT",null),
    /**
     *接受到unfreeze的订单的check result,重新做单
     **/
    RECEIVE_UNFREEZE_AND_AGAIN_PLACE_ORDER("1","接受到unfreeze的订单的check result,重新做单",null),
    /**
     *重新上传订单到sftp
     **/
    AGAIN_UPLOAD_SFTP("1","重新上传订单到sftp,把链接中的订单数据放到对应的SFTP目录",null),
    /**
     *adifox 系统check订单通过,通知销售批单
     **/
    ADIFOX_CHECK_ORDER_PASS_NOTIFY_SALE_APPROVE_ORDER("1","adifox 系统check订单通过,通知销售批单,等待portal定时任务运行：SFTP上传-CHECKRESULT",null),
    /**
     *销售驳回，adifox返回check result给belle,在trade meeting order/EDI order/order progress勾选订货会30695 30713 30714下的订单，点击unfrozen按钮,
     * 等待portal定时任务运行：SFTP上传-CHECKRESULT
     **/
    SALE_REJECT_AND_ADIFOX_SEND_CHECK_RESULT_TO_ACCOUNT("1","销售驳回，adifox返回check result给belle",null),
    /**
     *belle接收check result，重新调整订单，并上传sftp
     **/
    ACCOUNT_RECEIVE_CHECK_RESULT_AND_AGAIN_UPLOAD_ORDER_SFTP("1","belle接收check result，重新调整订单，并上传sftp",null),
    /**
     *adifox 系统检查订单通过，通知销售批单,等待portal定时任务运行：SFTP上传-CHECKRESULT
     **/
    ADIFOX_CHECK_ORDER_PASS_NOTIFY_SALE_APPROVE("1","adifox 系统检查订单通过，通知销售批单,等待portal定时任务运行：SFTP上传-CHECKRESULT",null),
    /**
     *销售审批通过订单
     **/
    SALE_PASS_ORDER("1","销售审批通过订单,在trade meeting order/EDI order/order progress勾选订货会30695 30713 30714下的订单，点击approve按钮",null),
    /**
     *交单结束
     **/
    SURRENDER_FINISH("1","交单结束",null),
    /**
     *发送HUB
     **/
    SEND_HUB("1","运行portal定时任务：SFTP上传-HUB",null),
    /**
     *发送APO
     **/
    SEND_APO("1","发送APO,运行portal定时任务：SFTP上传-APO",null),
    /**
     *发送AFS,在trade meeting admin/trade meeting/adifox tm window下勾选30695 30713 30714中的一场订货会,点击open按钮,点击send to erp按钮
     **/
    SEND_AFS("1","发送AFS,在trade meeting admin/trade meeting/adifox tm window下勾选30695 30713 30714中的一场订货会,点击open按钮,点击send to erp按钮",null);


    private String code;
    private String desc;

    private Operation operation;

     AdifoxTestFlowEnum(String code, String desc, Operation operation) {

        this.code = code;
        this.desc = desc;
        this.operation = operation;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
