package com.lifecycle.autotest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-26 上午 11:50
 */
@Data
@Getter
@Setter
public class LoginResponse {

    private int code;
    private int id;
    private String message ;

    private Result result;

    @Data
    @Getter
    @Setter
    public class Result{

        private String localekey;
        private String redirect;
        private String token;

    }

}
