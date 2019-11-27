/**
 * Copyright 2019 bejson.com
 */
package com.lifecycle.autotest.model.request;

import java.util.List;

/**
 * Auto-generated: 2019-11-27 14:6:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Params {

    private String table;
    private List<String> columns;
    private int start;
    private int range;
    private boolean count;
    private List<Orderby> orderby;

    private Expr1 expr1;
    private Expr2 expr2;

    public Expr1 getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr1 expr1) {
        this.expr1 = expr1;
    }

    public Expr2 getExpr2() {
        return expr2;
    }

    public void setExpr2(Expr2 expr2) {
        this.expr2 = expr2;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public boolean getCount() {
        return count;
    }

    public void setOrderby(List<Orderby> orderby) {
        this.orderby = orderby;
    }

    public List<Orderby> getOrderby() {
        return orderby;
    }



}