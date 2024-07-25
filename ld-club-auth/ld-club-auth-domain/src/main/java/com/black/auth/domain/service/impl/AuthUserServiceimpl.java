package com.black.auth.domain.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.black.auth.common.enums.AuthUserStatusEnum;
import com.black.auth.common.enums.IsDeletedFlagEnum;
import com.black.auth.domain.Dao.*;
import com.black.auth.domain.convert.AuthUserBOConverter;
import com.black.auth.domain.entity.AuthUserBO;
import com.black.auth.domain.redis.RedisUtil;
import com.black.auth.domain.service.AuthUserService;
import com.black.auth.infra.basic.entity.*;
import com.black.auth.common.utils.AssertUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthUserServiceimpl implements AuthUserService {

    @Resource
    private AuthUserDao authUserDao;
    @Resource
    private AuthRoleDao authRoleDao;
    @Resource
    private AuthUserRoleDao authUserRoleDao;
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;
    @Resource
    private AuthPermissionDao authPermissionDao;
    @Resource
    private RedisUtil redisUtil;

    private static final String NORMAL_USER = "NORMAL_USER";

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    private static final String LOGIN_PREFIX = "loginCode";

    private String salt = "ldclub";
    /**
     * 用户注册
     * @param authUserBO
     * @return
     */
    @Override
    public Boolean register(AuthUserBO authUserBO) {
        /**
         * 注册用户
         */
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        AuthUser user = authUserDao.getById(authUser.getId());
        if (user != null){
            return true;
        }
        authUser.setNickName(authUser.getUserName());
        authUser.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        authUser.setPassword("123456");
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        boolean save = authUserDao.save(authUser);
        AssertUtil.isTrue(save, "注册失败");
        /**
         * 添加初步的角色权限关联
         */
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(NORMAL_USER);
        AuthRole authRoleResult = authRoleDao.getByRoleKey(authRole);
        Long roleId = authRoleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        boolean authUserRoleSave = authUserRoleDao.save(authUserRole);
        AssertUtil.isTrue(authUserRoleSave, "权限关联失败");
        /**
         * 添加角色权限缓存
         */
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, JSONUtil.toJsonStr(roleList));
        /**
         * 添加权限缓存
         */
//        AuthRolePermission = new AuthRolePermission();
//        authRolePermission.setRoleId(roleId);
//        List<AuthRolePermission> authRolePermissionList = authRolePermissionDao.selectList(authRolePermission);
//        List<Long> PermissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
//        List<AuthPermission> permissionList = authPermissionDao.getByidList(PermissionIdList);
//        AssertUtil.isListEmpty(permissionList, "权限获取失败");
//        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
//        redisUtil.set(permissionKey, JSONUtil.toJsonStr(permissionList));
        return true;
    }
    /**
     * 更新用户信息
     * @param authUserBO
     * @return
     */
    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        boolean update = authUserDao.updateByUsername(authUser);
        AssertUtil.isTrue(update, "更新失败");
        return update;
    }
    /**
     * 获取用户信息
     * @param authUserBO
     * @return
     */
    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        AuthUser authuser1 = authUserDao.getUserInfo(authUser);
        AssertUtil.isEmpty(authuser1, "用户信息获取失败");
        AuthUserBO authUserBO1 = AuthUserBOConverter.INSTANCE.convertEntityToBO(authuser1);
        return authUserBO1;
    }
    /**
     * 批量获取用户信息
     * @param userNameList
     * @return
     */
    @Override
    public List<AuthUserBO> listUserInfoBynames(List<String> userNameList) {
        List<AuthUser> listuserinfo = authUserDao.getUserInfoList(userNameList);
        List<AuthUserBO> authUserBOS = AuthUserBOConverter.INSTANCE.convertEntityToBO(listuserinfo);
        AssertUtil.isListEmpty(authUserBOS, "批量用户信息获取失败");
        return authUserBOS;
    }
    /**
     * 删除用户
     * @param authUserBO
     * @return
     */
    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        boolean delete = authUserDao.removeById(authUser.getId());
        AssertUtil.isTrue(delete, "删除失败");
        return delete;
    }
    /**
     * 用户登录
     * @param validCode
     * @return
     */
    @Override
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(loginKey);
        AssertUtil.isEmpty(openId, "验证码失效");
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register(authUserBO);
        StpUtil.login(openId);
        return StpUtil.getTokenInfo();
    }

}
