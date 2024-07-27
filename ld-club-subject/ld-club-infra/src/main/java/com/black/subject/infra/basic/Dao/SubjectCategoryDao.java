package com.black.subject.infra.basic.Dao;


import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;
import com.black.subject.infra.basic.entity.SubjectCategory;
import com.black.subject.infra.basic.entity.SubjectMapping;
import com.black.subject.infra.mapper.SubjectCategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目分类 服务实现类
 * </p>
 */
@Service
public class SubjectCategoryDao extends ServiceImpl<SubjectCategoryMapper, SubjectCategory> {
    /**
     * 根据id查询分类
     * @param subjectCategory
     * @return
     */
    public List<SubjectCategory> queryCategoryAndLabel(SubjectCategory subjectCategory) {
        return lambdaQuery().eq(SubjectCategory::getId,subjectCategory.getId()).list();
    }
    /**
     * 根据类别查询分类
     * @param subjectCategory
     * @return
     */
    public List<SubjectCategory> queryPrimaryCategory(SubjectCategory subjectCategory) {
        return lambdaQuery().eq(SubjectCategory::getCategoryType,subjectCategory.getCategoryType()).list();
    }
    /**
     * 根据父id查询分类
     * @param subjectCategory
     * @return
     */
    public List<SubjectCategory> queryCategoryByPrimary(SubjectCategory subjectCategory) {
        return lambdaQuery().eq(SubjectCategory::getParentId,subjectCategory.getParentId()).
                eq(SubjectCategory::getCategoryType,subjectCategory.getCategoryType()).
                list();
    }
    /**
     * 根据父id查询分类
     * @param subjectCategory
     * @return
     */
    public List<SubjectCategory> queryCategoryByParentId(SubjectCategory subjectCategory) {
        return lambdaQuery().eq(SubjectCategory::getParentId,subjectCategory.getParentId())
                .eq(SubjectCategory::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode()).list();
    }
}
