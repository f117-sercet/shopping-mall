package com.dsc.supergo.sso.controller;
import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.sso.pojo.UserInfo;
import com.dsc.supergo.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/doLogin")
    public HttpResult doLogin(@RequestBody UserInfo userInfo) {
        HttpResult result = userService.doLogin(userInfo);
        return result;
    }
}
