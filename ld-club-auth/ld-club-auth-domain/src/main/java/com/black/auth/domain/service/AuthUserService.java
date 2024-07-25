package com.black.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.black.auth.domain.entity.AuthUserBO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
public interface AuthUserService {
    /**
     * 用户注册
     * @param authUserBO
     * @return
     */
    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);

    List<AuthUserBO> listUserInfoBynames(List<String> userNameList);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);
}
