package com.black.auth.domain.Dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.auth.infra.basic.entity.AuthUserRole;
import com.black.auth.infra.basic.mapper.AuthUserRoleMapper;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
@Component
public class AuthUserRoleDao extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> {

}
