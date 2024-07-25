package com.black.dao;

import com.black.entity.AuthUserRole;
import com.black.mapper.AuthUserRoleMapper;
import com.black.service.IAuthUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-23
 */
@Service
public class AuthUserRoleDao extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> implements IAuthUserRoleService {

}
