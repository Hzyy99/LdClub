package com.black.auth.application.controller;

import com.black.auth.application.convert.AuthRoleDTOConverter;
import com.black.auth.application.dto.AuthRoleDTO;
import com.black.auth.common.entity.Result;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.entity.AuthRoleBO;
import com.black.auth.domain.service.AuthRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * RoleController
 */
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Resource
    private AuthRoleService authRoleService;

    /**
     * 添加角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO){
        AssertUtil.isEmpty(authRoleDTO.getRoleKey(), "角色key不能为空");
        AssertUtil.isEmpty(authRoleDTO.getRoleName(), "角色名称不能为空");
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
        Boolean add = authRoleService.add(authRoleBO);
        return Result.success(add);
    }

    /**
     * 修改角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO){
        AssertUtil.isEmpty(authRoleDTO.getId(), "角色id不能为空");
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
        Boolean update = authRoleService.update(authRoleBO);
        return Result.success(update);
    }

    /**
     * 删除角色
     * @param authRoleDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO){
        AssertUtil.isEmpty(authRoleDTO.getId(), "角色id不能为空");
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOToBO(authRoleDTO);
        Boolean delete = authRoleService.delete(authRoleBO);
        return Result.success(delete);
    }
}
