/**
 * Copyright 2019 bejson.com
 */
package com.lifecycle.autotest.model.response;

import java.util.List;

/**
 * Auto-generated: 2019-11-27 14:13:51
 *  [
 *     {
 *         "code": 0,
 *         "count": 77,
 *         "id": "1",
 *         "message": "Complete:17.601 seconds",
 *         "rows": [
 *
 *             [
 *                 30609,
 *                 "BELLE",
 *                 "AO",
 *                 "ARTICLE_LIST",
 *                 "AO2020Q2",
 *                 "N/A",
 *                 null,
 *                 null,
 *                 null,
 *                 "Y",
 *                 "9130609"
 *             ],
 *             [
 *                 30609,
 *                 "BELLE",
 *                 "AO",
 *                 "MILESTONE",
 *                 "AO2020Q2",
 *                 "N/A",
 *                 null,
 *                 null,
 *                 null,
 *                 "Y",
 *                 "9530609"
 *             ]
 *         ]
 *     }
 * ]
 *
 */
public class EdiSendQueryResponse {

    private int code;
    private int count;
    private String id;
    private String message;
    private List<List<Object>> rows;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

}