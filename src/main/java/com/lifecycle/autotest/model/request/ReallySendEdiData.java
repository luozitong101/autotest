package com.lifecycle.autotest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 4:10
 */
@Data
@Getter
@Setter
public class ReallySendEdiData {

    /**
     *{
     *     "callbackEvent": "ExecuteWebAction",
     *     "cmd": "com.lifecycle.adifoxInterface.cmd.SendBasicData",
     *     "command": "com.lifecycle.adifoxInterface.cmd.AdifoxInterfaceCmd",
     *     "query": {
     *         "query": {
     *             "dir_perm": 1,
     *             "fixedcolumns": "",
     *             "init_query": false,
     *             "param_str2": "",
     *             "param_str2_obj": "",
     *             "qlcid": 2720639,
     *             "table": "V_FAIR_SEND",
     *             "tableId": 17656
     *         },
     *         "selection": [
     *             "9730519"
     *         ],
     *         "table": 17656
     *     }
     * }
     **/


    private String callbackEvent;
    private String cmd;
    private String command;
    private Query query;

    @Data
    @Getter
    @Setter
    public class Query{

        private List<String> selection;
        private int table;
        @JsonProperty(value = "query")
        private Query2 query;

        @Data
        @Getter
        @Setter
        public class Query2{
            private int dir_perm;
            private String fixedcolumns;
            private boolean init_query;
            private String param_str2;
            private String param_str2_obj;
            private int qlcid;
            private int tableId;
        }

    }




}
