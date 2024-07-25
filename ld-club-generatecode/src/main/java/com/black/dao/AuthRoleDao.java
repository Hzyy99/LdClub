package com.black.dao;

import com.black.entity.AuthRole;
import com.black.mapper.AuthRoleMapper;
import com.black.service.IAuthRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-23
 */
@Service
public class AuthRoleDao extends ServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {

}
