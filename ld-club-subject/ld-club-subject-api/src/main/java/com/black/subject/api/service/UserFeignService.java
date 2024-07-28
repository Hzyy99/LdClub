package com.black.subject.api.service;

import com.black.subject.api.req.AuthUserDTO;
import com.black.subject.api.req.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * OpenFeignService
 */
@FeignClient(name = "ld-club-auth",fallback = authFeignFallbackFactory.class)
public interface UserFeignService {

    @RequestMapping("/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);

    @RequestMapping("/user/listByIds")
    Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList);
}
