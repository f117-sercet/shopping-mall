package com.dsc.supergo.sso.service;


import com.dsc.supergo.base.service.BaseService;
import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.pojo.User;
import com.dsc.supergo.sso.pojo.UserInfo;

/**
 * UserService
 * @author dsc
 */
public interface UserService extends BaseService<User> {

    public HttpResult doLogin(UserInfo userInfo);
}
