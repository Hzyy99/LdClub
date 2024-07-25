package com.black.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.black.subject.application.convert.SubjectLabelDTOConverter;
import com.black.subject.application.dto.SubjectLabelDTO;
import com.black.subject.common.entity.Result;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.convert.SubjectLabelConverter;
import com.black.subject.domain.service.SubjectLabelService;
import com.black.subject.infra.basic.Bo.SubjectLabelBO;
import com.black.subject.infra.basic.entity.SubjectLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 题目标签表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    public SubjectLabelService subjectLabelService;
    /**
     * 添加标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        AssertUtil.isEmpty(subjectLabelDTO.getLabelName(), "标签名称不能为空");
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        Boolean result = subjectLabelService.add(subjectLabelBO);
        AssertUtil.isTrue(result, "添加失败");
        return Result.success(result);
    }
    /**
     * 更新标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        Boolean update = subjectLabelService.update(subjectLabelBO);
        AssertUtil.isTrue(update, "更新失败");
        return Result.success(update);
    }
    /**
     * 删除标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        SubjectLabelBO subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        Boolean delete = subjectLabelService.delete(subjectLabelBo);
        AssertUtil.isTrue(delete, "删除失败");
        return Result.success(delete);
    }
    /**
     * 根据分类id查询标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
        List<SubjectLabelBO> subjectLabelBoList =  subjectLabelService.queryLabelByCategoryId(subjectLabelBO);
        List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConverter.INSTANCE.convertBOToLabelDTOList(subjectLabelBoList);
        AssertUtil.isListEmpty(subjectLabelDTOS, "查询失败");
        return Result.success(subjectLabelDTOS);
    }
}

