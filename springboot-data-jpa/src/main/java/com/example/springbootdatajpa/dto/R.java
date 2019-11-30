package com.example.springbootdatajpa.dto;

import java.io.Serializable;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 8519228848702845742L;
    private static final int OK = 0;
    private static final int ERROR = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; // 服务端数据
    private int status = OK; // 返回状态码
    private String msg = ""; // 描述信息

    public static R isOk(){
        return new R();
    }

    public static R isError(){
        return new R().status(ERROR);
    }

    public static R isError(Throwable e){
        return isError().msg(e);
    }


    public R data(T data){
        this.setData(data);
        return this;
    }

    public R status(int status){
        this.setStatus(status);
        return this;
    }

    public R msg(Throwable e){
        this.setMsg(e.toString());
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
