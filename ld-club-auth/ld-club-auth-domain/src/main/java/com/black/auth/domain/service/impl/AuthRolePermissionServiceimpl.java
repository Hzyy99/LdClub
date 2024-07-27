package com.black.auth.domain.service.impl;

import com.black.auth.common.enums.IsDeletedFlagEnum;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.Dao.AuthRolePermissionDao;
import com.black.auth.domain.entity.AuthRolePermissionBO;
import com.black.auth.domain.service.AuthRolePermissionService;
import com.black.auth.infra.basic.entity.AuthRolePermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRolePermissionServiceimpl implements AuthRolePermissionService {
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            rolePermissionList.add(authRolePermission);
        });
        boolean b = authRolePermissionDao.saveBatch(rolePermissionList);
        AssertUtil.isTrue(b, "添加角色权限失败");
        return b;
    }
}
