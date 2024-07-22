package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthRole;
import com.black.auth.infra.basic.mapper.AuthRoleMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */

public class AuthRoleDao extends ServiceImpl<AuthRoleMapper, AuthRole>  {
    /**
     * 根据权限名称查询角色权限
     * @param authRole
     * @return
     */
    public AuthRole getByRoleName(AuthRole authRole) {
        return lambdaQuery().eq(AuthRole::getRoleName, authRole.getRoleName()).one();
    }
}
