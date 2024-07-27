package com.black.subject.application.controller;

import cn.hutool.core.bean.BeanUtil;
import com.black.subject.application.convert.SubjectCategoryDTOConverter;
import com.black.subject.application.convert.SubjectLabelDTOConverter;
import com.black.subject.application.dto.SubjectCategoryDTO;
import com.black.subject.application.dto.SubjectLabelDTO;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;
import com.black.subject.domain.service.SubjectCategoryService;
import com.black.subject.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
/**
 * <p>
 * 题目分类 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    SubjectCategoryService subjectCategoryService;
    /**
     * 新增分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        AssertUtil.isEmpty(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
        AssertUtil.isEmpty(subjectCategoryDTO.getCategoryName(), "分类名称不能为空");
        AssertUtil.isEmpty(subjectCategoryDTO.getParentId(), "父级ID不能为空");
        SubjectCategoryBO category = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
        subjectCategoryService.add(category);
        return Result.success();
    }
    /**
     * 查询分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.
                convertDtoToCategoryBO(subjectCategoryDTO);
        List<SubjectCategoryBO> subjectCategoryBoList = subjectCategoryService.queryPrimaryCategory(subjectCategoryBO);
        List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
                convertBoToCategoryDTOList(subjectCategoryBoList);
        return Result.success(subjectCategoryDTOList);
    }
    /**
     * 修改分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        AssertUtil.isEmpty(subjectCategoryDTO.getId(), "修改ID不能为空");
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.
                convertDtoToCategoryBO(subjectCategoryDTO);
        Boolean result = subjectCategoryService.update(subjectCategoryBO);
        return Result.success(result);

    }
    /**
     * 查询二级分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        AssertUtil.isEmpty(subjectCategoryDTO.getParentId(), "父级ID不能为空");
        AssertUtil.isEmpty(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.
                convertDtoToCategoryBO(subjectCategoryDTO);
        List<SubjectCategoryBO> subjectCategoryDTOList = subjectCategoryService.queryCategoryByPrimary(subjectCategoryBO);
        AssertUtil.isListEmpty(subjectCategoryDTOList, "查询二级分类失败");
        return Result.success(subjectCategoryDTOList);
    }
    /**
     * 删除分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        AssertUtil.isEmpty(subjectCategoryDTO.getId(), "删除ID不能为空");
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
        Boolean delete = subjectCategoryService.delete(subjectCategoryBO);
        AssertUtil.isTrue(delete, "删除分类失败");
        return Result.success(true);
    }
    /**
     * 查询分类和标签
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBO(subjectCategoryDTO);
        List<SubjectCategoryBO> ListLabelBO = subjectCategoryService.queryCategoryAndLabel(subjectCategoryBO);
        LinkedList<SubjectCategoryDTO> dtoList = new LinkedList<>();
        ListLabelBO.forEach(bo -> {
            SubjectCategoryDTO dto = SubjectCategoryDTOConverter.INSTANCE.convertBoToCategoryDTO(bo);
            List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBOToLabelDTOList(bo.getLabelBOList());
            dto.setLabelDTOList(labelDTOList);
            dtoList.add(dto);
        });
        return Result.success(dtoList);
    }
}

