package com.lifecycle.autotest.model.request;

/**
 * <p>
 *
 * </p>
 *
 * @author luoyong
 * @date 2019-11-27 下午 2:18
 */
public class EdiSendQueryByCondition {

    /**
     *  [
     *     {
     *         "command": "Query",
     *         "id": 1,
     *         "params": {
     *             "columns": [
     *                 "B_FAIR_ID",
     *                 "ACCOUNT",
     *                 "SALES_CHANNEL",
     *                 "INTERFACE_NAME",
     *                 "TM_SEASON",
     *                 "STATUS",
     *                 "COST_TIME",
     *                 "LAST_TIME",
     *                 "CN",
     *                 "IS_UPDATE",
     *                 "ID"
     *             ],
     *             "count": true,
     *             "orderby": [
     *                 {
     *                     "asc": true,
     *                     "column": "B_FAIR_ID"
     *                 },
     *                 {
     *                     "asc": true,
     *                     "column": "ACCOUNT"
     *                 },
     *                 {
     *                     "asc": true,
     *                     "column": "SALES_CHANNEL"
     *                 },
     *                 {
     *                     "asc": true,
     *                     "column": "INTERFACE_NAME"
     *                 }
     *             ],
     *             "params": {
     *                 "combine": "and",
     *                 "expr1": {
     *                     "combine": "and",
     *                     "expr1": {
     *                         "column": "B_FAIR_ID",
     *                         "condition": 30519
     *                     },
     *                     "expr2": {
     *                         "column": "ACCOUNT",
     *                         "condition": "BELLE"
     *                     }
     *                 },
     *                 "expr2": {
     *                     "column": "SALES_CHANNEL",
     *                     "condition": "AD"
     *                 }
     *             },
     *             "range": 20,
     *             "start": 0,
     *             "table": "V_FAIR_SEND"
     *         }
     *     }
     * ]
     **/
}
