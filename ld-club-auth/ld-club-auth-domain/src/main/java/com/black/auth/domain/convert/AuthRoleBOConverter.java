package com.black.auth.domain.convert;

import com.black.auth.domain.entity.AuthRoleBO;
import com.black.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleBOConverter {

    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convert(AuthRoleBO authRoleBO);
}
