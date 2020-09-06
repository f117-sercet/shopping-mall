package com.dsc.supergo.http;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 对返回的结果进行处理
 * @author 60221
 */
@Getter
@Setter
public class HttpResult implements Serializable {
    private int code = 200;
    private String msg;
    private Object data;
    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }
    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }
    public static HttpResult ok() {
        return new HttpResult();
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
