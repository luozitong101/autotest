package com.lifecycle.autotest.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 4:29
 */
@Data
@Getter
@Setter
public class EdiReallySendResponse {


    /**
     *  {"message":"Complete","data":{"message":"Running Task total quantity: 1","code":0},"callbackEvent":"ExecuteWebAction","code":0}
     **/


    private String message;

    private Data1 data ;

    private String callbackEvent;

    @Data
    @Getter
    @Setter
    public class Data1{
        private String message;
        private int code;

    }

    private int code;

}

