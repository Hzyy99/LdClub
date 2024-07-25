package com.black.subject.domain.service;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;

import java.util.List;

/**
 * <p>
 * 题目分类 服务类
 * </p>
 *
 * @author <a>black</a>
 * @since 2024-07-16
 */

public interface SubjectCategoryService{
    /**
     * 新增分类
     */
    void add(SubjectCategoryBO subjectCategoryBo);
    /**
     * 查询分类
     */
    List<SubjectCategoryBO> queryPrimaryCategory(SubjectCategoryBO subjectCategoryBO);
    /**
     * 更新分类
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategoryByPrimary(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}




