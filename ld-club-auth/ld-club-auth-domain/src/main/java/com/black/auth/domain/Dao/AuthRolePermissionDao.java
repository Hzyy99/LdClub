package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthRolePermission;
import com.black.auth.infra.basic.mapper.AuthRolePermissionMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
@Component
public class AuthRolePermissionDao extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission>  {

    public List<AuthRolePermission> selectList(AuthRolePermission authRolePermission) {
        return lambdaQuery().eq(AuthRolePermission::getRoleId,authRolePermission.getRoleId()).list();
    }

}
