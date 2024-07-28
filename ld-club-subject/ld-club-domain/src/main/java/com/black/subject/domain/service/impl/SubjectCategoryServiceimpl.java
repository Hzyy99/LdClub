package com.black.subject.domain.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.service.SubjectCategoryService;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;
import com.black.subject.infra.basic.Bo.SubjectLabelBO;
import com.black.subject.infra.basic.Dao.SubjectCategoryDao;
import com.black.subject.domain.convert.SubjectCategoryConverter;
import com.black.subject.infra.basic.Dao.SubjectLabelDao;
import com.black.subject.infra.basic.Dao.SubjectMappingDao;
import com.black.subject.infra.basic.entity.SubjectLabel;
import com.black.subject.infra.basic.entity.SubjectMapping;
import lombok.extern.slf4j.Slf4j;
import com.black.subject.infra.basic.entity.SubjectCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryServiceimpl implements SubjectCategoryService {

    @Resource
    private SubjectCategoryDao subjectCategoryDao;
    @Resource
    private SubjectMappingDao subjectMappingDao;
    @Resource
    private SubjectLabelDao subjectLabelDao;
    @Resource(name = "labelThreadPool")
    private ThreadPoolTaskExecutor labelThreadPool;
    /**
     *  新增分类
     */
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategory.setCreatedTime(DateTime.now());
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
        AssertUtil.isListEmpty(boList, "查询结果为空");
        return boList;
    }
    /**
     *  更新分类
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setUpdateTime(DateTime.now());
        boolean result = subjectCategoryDao.updateById(subjectCategory);
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
        Long subjectCategoryId = subjectCategoryBO.getId();
        List<SubjectCategoryBO> subjectCategoryBOS = getSubjectCategoryBOS(subjectCategoryId);
        AssertUtil.isListEmpty(subjectCategoryBOS, "查询结果为空");
        return subjectCategoryBOS;
    }

    /**
     * 异步线程池获取标签
     * @param categoryId
     * @return
     */
    private List<SubjectCategoryBO> getSubjectCategoryBOS(Long categoryId){
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryDao.queryCategoryByParentId(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryList:{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(subjectCategoryList);
        HashMap<Long, List<SubjectLabelBO>>SubjectCategoryBOmap = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = categoryBOList.stream().map(categoryBO ->
                CompletableFuture.supplyAsync(() ->
                        getLabelBOList(categoryBO), labelThreadPool)
        ).collect(Collectors.toList());
        completableFutureList.forEach(future -> {
            try {
                Map<Long, List<SubjectLabelBO>> resultMap = future.get();
                if (!MapUtil.isEmpty(resultMap)) {
                    SubjectCategoryBOmap.putAll(resultMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        categoryBOList.forEach(categoryBO -> {
            if (!CollUtil.isEmpty(SubjectCategoryBOmap.get(categoryBO.getId()))) {
                categoryBO.setLabelBOList(SubjectCategoryBOmap.get(categoryBO.getId()));
            }
        });
        return categoryBOList;
    }

    private Map<Long, List<SubjectLabelBO>> getLabelBOList(SubjectCategoryBO category){
        HashMap<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingDao.getLableId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> collectLabelId = mappingList.stream().distinct().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelDao.getLabelById(collectLabelId);
        List<SubjectLabelBO> labelBOList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
            subjectLabelBO.setId(label.getId());
            subjectLabelBO.setLabelName(label.getLabelName());
            subjectLabelBO.setCategoryId(label.getCategoryId());
            subjectLabelBO.setSortNum(label.getSortNum());
            labelBOList.add(subjectLabelBO);
        });
        labelMap.put(category.getId(), labelBOList);
        return labelMap;
    }
}

