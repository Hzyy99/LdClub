package com.black.subject.domain.service.impl;

import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.service.SubjectCategoryService;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;
import com.black.subject.infra.basic.Dao.SubjectCategoryDao;
import com.black.subject.domain.convert.SubjectCategoryConverter;
import lombok.extern.slf4j.Slf4j;
import com.black.subject.infra.basic.entity.SubjectCategory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSON;

import java.util.List;

@Service
@Slf4j
public class SubjectCategoryServiceimpl implements SubjectCategoryService {

    @Resource
    private SubjectCategoryDao subjectCategoryDao;
    /**
     *  新增分类
     */
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        boolean save = subjectCategoryDao.save(subjectCategory);
        AssertUtil.isTrue(save, "新增失败");
    }
    /**
     *  查询分类
     */
    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory(SubjectCategoryBO subjectCategoryBO) {

        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        List<SubjectCategory> SubjectCategoryList = subjectCategoryDao.queryPrimaryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(SubjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}",
                    JSON.toJSONString(boList));
        }
        AssertUtil.isListEmpty(boList, "查询结果为空");
        return boList;
    }
    /**
     *  更新分类
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        boolean result = subjectCategoryDao.updateById(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        AssertUtil.isTrue(result, "更新失败");
        return result;
    }
    /**
     *  查询二级分类
     */
    @Override
    public List<SubjectCategoryBO> queryCategoryByPrimary(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        List<SubjectCategory> boList = subjectCategoryDao.queryCategoryByPrimary(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOS = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(boList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        AssertUtil.isListEmpty(subjectCategoryBOS, "查询结果为空");
        return subjectCategoryBOS;
    }
    /**
     *  删除分类
     */
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        boolean result = subjectCategoryDao.removeById(subjectCategory);
        AssertUtil.isTrue(result, "删除失败");
        return true;
    }
    /**
     *  查询分类和标签
     */
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryDao.queryCategoryAndLabel(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(subjectCategoryList);
        AssertUtil.isListEmpty(subjectCategoryBOList, "查询结果为空");
        return subjectCategoryBOList;
    }
}

