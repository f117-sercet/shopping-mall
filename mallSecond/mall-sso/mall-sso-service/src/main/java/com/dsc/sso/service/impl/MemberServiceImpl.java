package com.dsc.sso.service.impl;

import com.dsc.common.execetion.MallException;
import com.dsc.common.jedis.JedisClient;
import com.dsc.common.utils.QiniuUtil;
import com.dsc.mall.manager.dto.front.Member;
import com.dsc.mall.manager.mapper.TbMemberMapper;
import com.dsc.mall.manager.pojo.TbMember;
import com.dsc.sso.service.LoginService;
import com.dsc.sso.service.MemberService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

/**
 *成员Service实现类
 * @author 60221
 */
public class MemberServiceImpl implements MemberService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private TbMemberMapper tbMemberMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("1000")
    private Integer SESSION_EXPIRE;
    @Override
    public String imageUpload(Long userId, String token, String imgData) {

        //过滤data:URL
        String base64= QiniuUtil.base64Data(imgData);
        String imgPath=QiniuUtil.qiniuUpload(base64);

        TbMember tbMember=tbMemberMapper.selectByPrimaryKey(userId);
        if (tbMember==null){
            throw  new MallException("通过id用户失败");
        }

        //更新缓存
        Member member=loginService.getUserByToken(token);
        jedisClient.set("SESSION:"+ token,new Gson().toJson(member));
        return imgPath;
    }
}
