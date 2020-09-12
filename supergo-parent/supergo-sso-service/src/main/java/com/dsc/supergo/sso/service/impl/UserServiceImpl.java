package com.dsc.supergo.sso.service.impl;


import com.dsc.supergo.base.service.BaseServiceImpl;
import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.pojo.User;
import com.dsc.supergo.sso.pojo.UserInfo;
import com.dsc.supergo.sso.service.UserService;
import com.dsc.supergo.sso.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public HttpResult doLogin(UserInfo userInfo) {
        User query = new User();
        query.setUsername(userInfo.getUsername());
        List<User> list = this.findByWhere(query);
        if (list == null || list.size() == 0) {
            return HttpResult.error(401, "用户名不存在");
        }
        User user = list.get(0);
        if (!BCrypt.checkpw(userInfo.getPassword(), user.getPassword())) {
            return HttpResult.error(401, "密码错误");
        }
        //登录成功
        String token = jwtUtil.createToken(user.getId() + "", user.getUsername(), "admin");

        return HttpResult.ok(token);
    }

}
