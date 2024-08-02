package com.black.auth.domain.service.impl;

import com.black.auth.common.enums.IsDeletedFlagEnum;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.Dao.AuthPermissionDao;
import com.black.auth.domain.convert.AuthPermissionBOConverter;
import com.black.auth.domain.entity.AuthPermissionBO;
import com.black.auth.domain.redis.RedisUtil;
import com.black.auth.domain.service.AuthPermissionService;
import com.black.auth.infra.basic.entity.AuthPermission;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthPermissionServiceimpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";
    /**
     * 添加权限
     * @param authPermissionBO
     * @return
     */
    @Override
    public Boolean addpermission(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authPermission.setCreatedTime(LocalDateTime.now());
        boolean save = authPermissionDao.save(authPermission);
        AssertUtil.isTrue(save, "添加权限失败");
        return save;
    }
    /**
     * 修改权限
     * @param authPermissionBO
     * @return
     */
    @Override
    public Boolean updatepermission(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setUpdateTime(LocalDateTime.now());
        boolean update = authPermissionDao.updateById(authPermission);
        AssertUtil.isTrue(update, "修改权限失败");
        return update;
    }
    /**
     * 删除权限
     * @param authPermissionBO
     * @return
     */
    @Override
    public Boolean deletepermission(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        boolean update = authPermissionDao.removeById(authPermission);
        AssertUtil.isTrue(update, "删除权限失败");
        return update;
    }
    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue,
                new TypeToken<List<AuthPermission>>() {
                }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }
}
