package com.dsc.sso.service;

/**
 * 会员Service
 * @author 60221
 */
public interface MemberService {
    /**
     * 头像上传
     * @param userId
     * @param token
     * @param imgData
     * @return
     */
    String imageUpload(Long userId,String token,String imgData);
}
