package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthPermission;
import com.black.auth.infra.basic.mapper.AuthPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-23
 */
@Service
public class AuthPermissionDao extends ServiceImpl<AuthPermissionMapper, AuthPermission> {

    public List<AuthPermission> getByidList(List<Long> permissionIdList) {
        return this.getBaseMapper().selectBatchIds(permissionIdList);
    }
}
