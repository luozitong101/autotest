package com.lifecycle.autotest.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *   点击edi send 按钮 第一个请求 URL:http://192.168.132.246:24789/servlets/binserv/AP type:post
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 3:47
 */
@Data
@Getter
@Setter
public class SendRequestData4Log {

    /**
     *[
     *     {
     *         "command": "com.agilecontrol.portal7.AdminPanelCmd",
     *         "id": 1,
     *         "params": {
     *             "cmd": "portal7.recordLog",
     *             "desc": "Send Data",
     *             "id": 690
     *         }
     *     }
     * ]
     **/


    /***
     *   response:  [{"message":"Complete","id":"1","code":0}]
     **/


    private String command;
    private int id;
    private Params params;

    @Data
    @Getter
    @Setter
    public class Params{
        public String cmd;
        private String desc;
        private int id;
    }

}
