package com.dsc.sso.service;

/**
 * 注册Service
 * @author 60221
 */
public interface RegisterService {
    /**
     * 勾选
     * @param param
     * @param type
     * @return
     */
    boolean checkData(String param, int type);

    /**
     * 注册
     * @param userName
     * @param userPwd
     * @return
     */
    int register(String userName,String userPwd);
}
