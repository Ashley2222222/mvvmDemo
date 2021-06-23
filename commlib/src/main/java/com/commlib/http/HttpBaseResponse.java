package com.commlib.http;

import com.google.gson.annotations.SerializedName;

public class HttpBaseResponse<T> {
//    @SerializedName("code")
//    private int code;

    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String msg;

    @SerializedName("obj")
    private T obj;
    @SerializedName("key")
    private String key;
    @SerializedName("list")
    private T list;
    @SerializedName("rows")
    private T rows;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("result=" + result + " msg=" + msg);
        if (null != obj) {
            sb.append(" subjects:" + obj.toString());
        }
        return sb.toString();
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
