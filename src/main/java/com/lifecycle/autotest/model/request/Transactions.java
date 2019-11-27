/**
 * Copyright 2019 bejson.com
 */
package com.lifecycle.autotest.model.request;

/**
 * Auto-generated: 2019-11-27 14:6:34
 *  edi send log model
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
 *             "range": 20,
 *             "start": 0,
 *             "table": "V_FAIR_SEND"
 *         }
 *     }
 * ]
 *
 */
public class Transactions {

    private int id;
    private String command;
    private Params params;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Params getParams() {
        return params;
    }

}