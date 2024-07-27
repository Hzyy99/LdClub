package com.black.auth.application.controller;

import com.black.auth.application.convert.AuthPermissionDTOConverter;
import com.black.auth.application.dto.AuthPermissionDTO;
import com.black.auth.common.entity.Result;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.entity.AuthPermissionBO;
import com.black.auth.domain.service.AuthPermissionService;
import com.black.auth.infra.basic.entity.AuthPermission;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限Controller
 */
@RestController
@RequestMapping("/permission/")
public class PermissionController {

    @Resource
    private AuthPermissionService authPermissionService;
    /**
     * 新增权限
     */
    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO){
        AssertUtil.isEmpty(authPermissionDTO.getName(), "权限名称不能为空");
        AssertUtil.isEmpty(authPermissionDTO.getParentId(), "权限父ID不能为空");
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
        Boolean result = authPermissionService.addpermission(authPermissionBO);
        return Result.success(result);
    }
    /**
     * 修改权限
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO){
        AssertUtil.isEmpty(authPermissionDTO.getId(), "权限ID不能为空");
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
        Boolean result = authPermissionService.updatepermission(authPermissionBO);
        return Result.success(result);
    }

    /**
     * 删除权限
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        AssertUtil.isEmpty(authPermissionDTO.getId(), "权限ID不能为空");
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBO(authPermissionDTO);
        Boolean result = authPermissionService.deletepermission(authPermissionBO);
        return Result.success(result);
    }
    /**
     * 查询用户权限
     * @param userName
     * @return
     */
    @RequestMapping("getPermission")
    public Result<List<String>> getPermission(String userName){
        return Result.success(authPermissionService.getPermission(userName));
    }
}
