package com.dsc.front.exception;

import com.dsc.common.pojo.Result;
import com.dsc.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;

/***
 * @author dsc
 */
@ControllerAdvice
public class RestCtrlExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result<Object> bindExceptionHandler(BindException e){
        String errorMsg="请求数据校验不合法: ";
        if(e!=null){
            errorMsg=e.getMessage();
            log.warn(errorMsg);
        }
        return new ResultUtil<>().setErrorMsg(errorMsg);
    }
}
