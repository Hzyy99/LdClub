package com.black.auth.domain.service;

import com.black.auth.domain.entity.AuthPermissionBO;
import com.black.auth.infra.basic.entity.AuthPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
public interface AuthPermissionService {

    Boolean addpermission(AuthPermissionBO authPermissionBO);

    Boolean updatepermission(AuthPermissionBO authPermissionBO);

    Boolean deletepermission(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
