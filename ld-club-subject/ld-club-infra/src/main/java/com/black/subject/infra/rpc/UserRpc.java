package com.black.subject.infra.rpc;

import com.black.subject.api.req.AuthUserDTO;
import com.black.subject.api.req.Result;
import com.black.subject.api.service.UserFeignService;
import com.black.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRpc {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()) {
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        userInfo.setAvatar(data.getAvatar());
        return userInfo;
    }

}
