package com.black.auth.infra.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author black
 * @since 2024-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_permission")
public class AuthPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 权限类型 0菜单 1操作
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单路由
     */
    @TableField("menu_url")
    private String menuUrl;

    /**
     * 状态 0启用 1禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 展示状态 0展示 1隐藏
     */
    @TableField("`show`")
    private Integer show;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 权限唯一标识
     */
    @TableField("permission_key")
    private String permissionKey;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否被删除 0为删除 1已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}
