package com.black.dao;

import com.black.entity.AuthUser;
import com.black.mapper.AuthUserMapper;
import com.black.service.IAuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author black
 * @since 2024-07-23
 */
@Service
public class AuthUserDao extends ServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

}
