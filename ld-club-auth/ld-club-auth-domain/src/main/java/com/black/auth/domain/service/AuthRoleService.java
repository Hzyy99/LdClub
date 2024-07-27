package com.black.auth.domain.service;

import com.black.auth.domain.entity.AuthRoleBO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
public interface AuthRoleService  {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
