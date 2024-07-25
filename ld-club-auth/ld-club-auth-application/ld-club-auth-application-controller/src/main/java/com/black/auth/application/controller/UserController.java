package com.black.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.black.auth.application.convert.AuthUserDTOConverter;
import com.black.auth.application.dto.AuthUserDTO;
import com.black.auth.domain.entity.AuthUserBO;
import com.black.auth.domain.service.AuthUserService;
import com.black.subject.common.entity.Result;
import com.black.subject.common.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Resource
    public AuthUserService authUserService;
    /**
     * 注册用户
     * @param authUserDTO
     * @return
     */
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        AssertUtil.isEmpty(authUserDTO.getUserName(), "用户名不能为空");
        AssertUtil.isEmpty(authUserDTO.getPassword(), "密码不能为空");
        AssertUtil.isEmpty(authUserDTO.getEmail(), "邮箱不能为空");
        AssertUtil.isEmpty(authUserDTO.getPhone(), "手机号不能为空");
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        Boolean register = authUserService.register(authUserBO);
        return Result.success(register);
    }
    /**
     * 更新用户信息
     * @param authUserDTO
     * @return
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO){
        AssertUtil.isEmpty(authUserDTO.getUserName(), "用户名字不能为空");
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        Boolean update = authUserService.update(authUserBO);
        return Result.success(update);
    }
    /**
     * 删除用户
     * @param authUserDTO
     * @return
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO){
        if (log.isInfoEnabled()){
            log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
        }
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        Boolean delete = authUserService.delete(authUserBO);
        return Result.success(delete);
    }
    /**
     * 获取用户信息
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        AssertUtil.isEmpty(authUserBO.getUserName(), "用户名不能为空");
        AuthUserBO authUserBOs =authUserService.getUserInfo(authUserBO);
        AuthUserDTO authUserDTOs = AuthUserDTOConverter.INSTANCE.convertBOToDTO(authUserBOs);
        return Result.success(authUserDTOs);
    }
    /**
     * 批量获取用户信息
     * @param userNameList
     * @return
     */
    @RequestMapping("listByIds")
    public Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList){
        AssertUtil.isListEmpty(userNameList, "用户名集合不能为空");
        List<AuthUserBO> userInfos = authUserService.listUserInfoBynames(userNameList);
        return null;
    }
    /**
     * 用户启用or禁用
     * @param authUserDTO
     * @return
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO){
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        AssertUtil.isEmpty(authUserBO.getId(), "用户ID不能为空");
        AssertUtil.isEmpty(authUserBO.getStatus(), "用户状态不能为空");
        Boolean update = authUserService.update(authUserBO);
        return Result.success(update);
    }
    /**
     * 用户登录
     *
     * @param validCode
     * @return
     */
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode){
        AssertUtil.isEmpty(validCode, "验证码不能为空");
        SaTokenInfo saTokenInfo = authUserService.doLogin(validCode);
        return Result.success(saTokenInfo);
    }
    /**
     * 用户退出
     * @param userName
     * @return
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam String userName){
        AssertUtil.isEmpty(userName, "用户名不能为空");
        StpUtil.logout(userName);
        return Result.success();
    }
    @RequestMapping("test")
    public String test(){
        return "123";
    }
}
