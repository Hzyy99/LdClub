package com.black.auth.application.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 权限dto

 */
@Data
public class AuthPermissionDTO implements Serializable {

    private Long id;
    
    private String name;
    
    private Long parentId;
    
    private Integer type;
    
    private String menuUrl;
    
    private Integer status;
    
    private Integer show;
    
    private String icon;
    
    private String permissionKey;
}

