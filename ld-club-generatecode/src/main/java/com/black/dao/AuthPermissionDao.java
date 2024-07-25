package com.black.dao;

import com.black.entity.AuthPermission;
import com.black.mapper.AuthPermissionMapper;
import com.black.service.IAuthPermissionService;
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
public class AuthPermissionDao extends ServiceImpl<AuthPermissionMapper, AuthPermission> implements IAuthPermissionService {

}
