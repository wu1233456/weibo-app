package com.example.weibo_app2.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultBean implements Serializable {
    //返回码
    public int error = 0;

    //消息提示
    public String errmsg = "";
    //数据
    public Map<String, Object> data = new HashMap<>();

    public ResultBean() {
    }

    public ResultBean(int error, String errmsg, Map<String, Object> data) {
        this.error = error;
        this.errmsg = errmsg;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "error=" + error +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}