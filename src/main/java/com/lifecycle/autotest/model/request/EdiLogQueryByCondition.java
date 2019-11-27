package com.lifecycle.autotest.model.request;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 2:29
 */
public class EdiLogQueryByCondition {

    /**
     *[
     *     {
     *         "command": "Query",
     *         "id": 1,
     *         "params": {
     *             "columns": [
     *                 "B_FAIR_ID",
     *                 "ACCOUNT",
     *                 "SALES_CHANNEL",
     *                 "FILE_PATH",
     *                 "INTERFACE_NAME",
     *                 "STATUS",
     *                 "ERRORMSG",
     *                 "ISERROR",
     *                 "ARGS",
     *                 "TM_SEASON",
     *                 "COST_TIME",
     *                 "MODIFIEDDATE",
     *                 "ID"
     *             ],
     *             "count": true,
     *             "orderby": [
     *                 {
     *                     "asc": false,
     *                     "column": "MODIFIEDDATE"
     *                 }
     *             ],
     *             "params": {
     *                 "combine": "and",
     *                 "expr1": {
     *                     "combine": "and",
     *                     "expr1": {
     *                         "column": "ACCOUNT",
     *                         "condition": "BELLE"
     *                     },
     *                     "expr2": {
     *                         "column": "B_FAIR_ID",
     *                         "condition": 30743
     *                     }
     *                 },
     *                 "expr2": {
     *                     "column": "SALES_CHANNEL",
     *                     "condition": "AD"
     *                 }
     *             },
     *             "range": 10,
     *             "start": 0,
     *             "table": "B_ADIFOX_BASICDATA_DTL_LOG"
     *         }
     *     }
     * ]
     **/


}
