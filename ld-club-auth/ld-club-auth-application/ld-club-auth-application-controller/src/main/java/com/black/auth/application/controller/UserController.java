package com.black.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.black.auth.application.convert.AuthUserDTOConverter;
import com.black.auth.application.dto.AuthUserDTO;
import com.black.auth.domain.convert.AuthUserBOConverter;
import com.black.auth.domain.entity.AuthUserBO;
import com.black.auth.domain.service.AuthUserService;
import com.black.auth.infra.basic.entity.AuthUser;
import com.black.subject.common.entity.Result;
import com.black.subject.common.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
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
        if (log.isInfoEnabled()){
            log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
        }
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
        if (log.isInfoEnabled()){
            log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
        }
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
        Boolean update = authUserService.update(authUserBO);
        return Result.success(update);
    }
    /**
     * 获取用户信息
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        if (log.isInfoEnabled()){
            log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
        }
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
        if (log.isInfoEnabled()) {
            log.info("UserController.listUserInfoByIds.dto:{}", JSON.toJSONString(userNameList));
        }
        AssertUtil.isListEmpty(userNameList, "用户名集合不能为空");
        List<AuthUserBO> userInfos = authUserService.listUserInfoBynames(userNameList);
        return null;
    }

}
