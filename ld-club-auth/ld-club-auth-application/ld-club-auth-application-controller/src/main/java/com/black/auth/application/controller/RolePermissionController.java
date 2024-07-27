package com.black.auth.application.controller;

import com.black.auth.application.convert.AuthRolePermissionDTOConverter;
import com.black.auth.application.dto.AuthRolePermissionDTO;
import com.black.auth.common.entity.Result;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.entity.AuthRolePermissionBO;
import com.black.auth.domain.service.AuthRolePermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限Controller
 */
@RestController
@RequestMapping("/rolePermission/")
public class RolePermissionController {

    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        AssertUtil.isEmpty(authRolePermissionDTO.getPermissionIdList(), "权限关联不能为空");
        AssertUtil.isEmpty(authRolePermissionDTO.getRoleId(), "角色不能为空");
        AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBO(authRolePermissionDTO);
        Boolean result = authRolePermissionService.add(authRolePermissionBO);
        return Result.success(result);
    }
}
