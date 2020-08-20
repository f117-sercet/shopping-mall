package com.dsc.common.execetion;

/**
 * @author dsc
 */
public class MallUploadException extends RuntimeException {

    private String msg;

    public MallUploadException(String msg){
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
