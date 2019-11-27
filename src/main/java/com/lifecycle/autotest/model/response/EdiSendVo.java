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
 * @date 2019-11-27 下午 3:37
 */

@Data
@Getter
@Setter
public class EdiSendVo {


    private long tmid;

    private String account;

    private String channel;

    private int id;

    private String interfaceName;
}
