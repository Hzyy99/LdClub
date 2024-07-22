package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthPermission;
import com.black.auth.infra.basic.mapper.AuthPermissionMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */

public class AuthPermissionDao extends ServiceImpl<AuthPermissionMapper, AuthPermission>  {
    /**
     * 查询权限
     * @param permissionIdList
     * @return
     */
    public List<AuthPermission> getByidList(List<Long> permissionIdList) {
        LambdaQueryWrapper<AuthPermission> authPermissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authPermissionLambdaQueryWrapper.eq(AuthPermission::getId, permissionIdList);
        return this.getBaseMapper().selectList(authPermissionLambdaQueryWrapper);
    }

}
