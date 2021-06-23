package com.commlib.http;

import com.google.gson.annotations.SerializedName;

public class HttpResponse<T> {
//    @SerializedName("code")
//    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T data;
    @SerializedName("list")
    private T list;
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(/*"code=" + code + */" msg=" + msg);
        if (null != data) {
            sb.append(" subjects:" + data.toString());
        }
        return sb.toString();
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
