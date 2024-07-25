package com.black.dao;

import com.black.entity.AuthRolePermission;
import com.black.mapper.AuthRolePermissionMapper;
import com.black.service.IAuthRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-23
 */
@Service
public class AuthRolePermissionDao extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission> implements IAuthRolePermissionService {

}
