package com.dsc.supergo.http;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 错误类
 * @author 60221
 */
@Getter
@Setter
public class Error implements Serializable {
    private String field;
    private Integer id;
    private String msg;
    private Integer vmId = 1;

    public Error() {
    }

    public Error(String field, Integer id, String msg) {
        this.field = field;
        this.id = id;
        this.msg = msg;
    }
}
