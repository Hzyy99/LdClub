package com.black.auth.domain.service;


import com.black.auth.domain.entity.AuthRolePermissionBO;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
public interface AuthRolePermissionService {

    Boolean add(AuthRolePermissionBO authRolePermissionBO);

}
