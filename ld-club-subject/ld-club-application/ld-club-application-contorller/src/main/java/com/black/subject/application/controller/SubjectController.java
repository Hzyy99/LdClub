package com.black.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.black.subject.application.convert.SubjectInfoDTOConverter;
import com.black.subject.application.dto.SubjectInfoDTO;
import com.black.subject.common.entity.Result;
import com.black.subject.domain.service.SubjectInfoService;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    SubjectInfoService subjectInfoService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        if (log.isInfoEnabled()){
            log.info("add subjectInfoDTO:{}", JSON.toJSONString(subjectInfoDTO));
        }
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOToBO(subjectInfoDTO);
        Boolean result = subjectInfoService.add(subjectInfoBO);

        return Result.success();
    }
}
