package com.black.subject.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.black.subject.common.entity.PageInfo;
import com.black.subject.common.enums.IsDeletedFlagEnum;
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
import com.black.subject.infra.config.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        /**
         * 分页查询
         */

        List<SubjectMapping> byLabelId = subjectMappingDao.getByLabelId(subjectInfoBO);
        ArrayList<Long> longs = new ArrayList<>();
        byLabelId.stream().forEach(subjectMapping -> {
            longs.add(subjectMapping.getSubjectId());
        });
        List<Long> collectid = longs.stream().distinct().collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(subjectInfoBO.getPageNo());
        pageInfo.setPageSize(subjectInfoBO.getPageSize());
        Page<SubjectInfo> subjectPage = subjectInfoDao.getSubjectPage(collectid,subjectInfoBO);
        /**
         * 封装结果
         */
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoToBO(subjectPage.getRecords());
        AssertUtil.isListEmpty(subjectInfoBOS, "查询题目列表失败");
        PageResult<SubjectInfoBO> subjectInfoBOPageResult = new PageResult<>();
        subjectInfoBOPageResult.setPageSize(subjectPage.getSize());
        subjectInfoBOPageResult.setPage(subjectPage.getCurrent());
        subjectInfoBOPageResult.setCounts(subjectPage.getTotal());
        subjectInfoBOPageResult.setItems(subjectInfoBOS);
        return subjectInfoBOPageResult;
    }
    /**
     * 查询题目详情
     * @param subjectInfoBO
     * @return
     */
    @Override
    public SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        SubjectInfo quarysubjectInfo = subjectInfoDao.getById(subjectInfo);
        AssertUtil.isEmpty(quarysubjectInfo, "查询题目详情失败");
        SubjectInfoBO quarysubjectInfoBo = SubjectInfoConverter.INSTANCE.convertInfoToBo(quarysubjectInfo);
        return quarysubjectInfoBo;
    }
}
