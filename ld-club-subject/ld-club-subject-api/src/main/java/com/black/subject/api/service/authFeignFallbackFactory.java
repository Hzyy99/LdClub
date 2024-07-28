package com.black.subject.api.service;

import com.black.subject.api.req.AuthUserDTO;
import com.black.subject.api.req.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class authFeignFallbackFactory implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable cause) {
        return new UserFeignService() {
            @Override
            public Result<AuthUserDTO> getUserInfo(AuthUserDTO authUserDTO) {
                return Result.fail("远程调用获取用户信息接口发生熔断");
            }

            @Override
            public Result<List<AuthUserDTO>> listUserInfoByIds(List<String> userNameList) {
                return Result.fail("远程调用获取批量用户信息接口发生熔断");
            }
        };
    }
}
