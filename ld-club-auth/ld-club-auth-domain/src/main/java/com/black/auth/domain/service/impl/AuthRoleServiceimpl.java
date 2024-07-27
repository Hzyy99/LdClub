package com.black.auth.domain.service.impl;

import com.black.auth.common.enums.IsDeletedFlagEnum;
import com.black.auth.common.utils.AssertUtil;
import com.black.auth.domain.Dao.AuthRoleDao;
import com.black.auth.domain.convert.AuthRoleBOConverter;
import com.black.auth.domain.entity.AuthRoleBO;
import com.black.auth.domain.service.AuthRoleService;
import com.black.auth.infra.basic.entity.AuthRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class AuthRoleServiceimpl implements AuthRoleService {
    @Resource
    private AuthRoleDao authRoleDao;
    /**
     * 添加角色
     * @param authRoleBO
     * @return
     */
    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole convert = AuthRoleBOConverter.INSTANCE.convert(authRoleBO);
        convert.setCreatedTime(LocalDateTime.now());
        convert.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        boolean save = authRoleDao.save(convert);
        AssertUtil.isTrue(save, "添加角色失败");
        return save;
    }

    /**
     * 修改角色
     * @param authRoleBO
     * @return
     */
    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole convert = AuthRoleBOConverter.INSTANCE.convert(authRoleBO);
        convert.setUpdateTime(LocalDateTime.now());
        boolean update = authRoleDao.updateById(convert);
        AssertUtil.isTrue(update, "修改角色失败");
        return update;
    }

    /**
     * 删除角色
     * @param authRoleBO
     * @return
     */
    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole convert = AuthRoleBOConverter.INSTANCE.convert(authRoleBO);
        boolean remove = authRoleDao.removeById(convert);
        AssertUtil.isTrue(remove, "删除角色失败");
        return remove;
    }


}
