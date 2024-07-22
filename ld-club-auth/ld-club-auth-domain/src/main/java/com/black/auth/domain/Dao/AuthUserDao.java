package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthRole;
import com.black.auth.infra.basic.entity.AuthUser;
import com.black.auth.infra.basic.mapper.AuthUserMapper;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */

public class AuthUserDao extends ServiceImpl<AuthUserMapper, AuthUser>  {
    /**
     * 根据用户名获取用户信息
     * @param authUser
     * @return
     */
    public AuthUser getUserInfo(AuthUser authUser) {
        return lambdaQuery().eq(AuthUser::getUserName, authUser.getUserName()).one();
    }
    /**
     * 根据用户名批量获取用户信息
     * @param userNameList
     * @return
     */
    public List<AuthUser> getUserInfoList(List<String> userNameList) {
        LambdaQueryWrapper<AuthUser> authUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authUserLambdaQueryWrapper.eq(AuthUser::getUserName, userNameList);
        return this.getBaseMapper().selectList(authUserLambdaQueryWrapper);
    }
}
