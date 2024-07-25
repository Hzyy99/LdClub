package com.black.auth.domain.convert;

import com.black.auth.domain.entity.AuthRoleBO;
import com.black.auth.infra.basic.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-24T17:30:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_382 (Amazon.com Inc.)"
)
public class AuthRoleBOConverterImpl implements AuthRoleBOConverter {

    @Override
    public AuthRole convert(AuthRoleBO authRoleBO) {
        if ( authRoleBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBO.getId() );
        authRole.setRoleName( authRoleBO.getRoleName() );
        authRole.setRoleKey( authRoleBO.getRoleKey() );

        return authRole;
    }
}
