package com.black.subject.domain.service.impl;

import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.enums.SubjectInfoTypeEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.convert.SubjectInfoConverter;
import com.black.subject.domain.handler.subject.SubjectTypeHandler;
import com.black.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.black.subject.domain.service.SubjectInfoService;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Dao.SubjectInfoDao;
import com.black.subject.infra.basic.Dao.SubjectMappingDao;
import com.black.subject.infra.basic.entity.SubjectInfo;
import com.black.subject.infra.basic.entity.SubjectMapping;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class SubjectInfoServiceimpl implements SubjectInfoService {

    @Resource
    private SubjectInfoDao subjectInfoDao;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingDao subjectMappingDao;
    /**
     * 添加题目
     * @param subjectInfoBO
     * @return
     */
    @Override
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        /**
         * 插入题目
         */
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoDao.save(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);

        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        LinkedList<SubjectMapping> subjectMappings = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId ->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMappings.add(subjectMapping);
            });
            boolean mappingresult = subjectMappingDao.saveBatch(subjectMappings);
            AssertUtil.isTrue(mappingresult, "添加题目映射失败");
        });
        return true;
    }
}
