package com.black.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.black.subject.application.convert.SubjectInfoDTOConverter;
import com.black.subject.application.dto.SubjectInfoDTO;
import com.black.subject.common.entity.Result;
import com.black.subject.common.utils.AssertUtil;
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
        AssertUtil.isEmpty(subjectInfoDTO.getSubjectName(), "题目内容不能为空");
        AssertUtil.isEmpty(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
        AssertUtil.isEmpty(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
        AssertUtil.isEmpty(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
        AssertUtil.isListEmpty(subjectInfoDTO.getCategoryIds(), "分类ID不能为空");
        AssertUtil.isListEmpty(subjectInfoDTO.getLabelIds(), "标签ID不能为空");
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOToBO(subjectInfoDTO);
        Boolean result = subjectInfoService.add(subjectInfoBO);
        return Result.success(result);
    }
}
