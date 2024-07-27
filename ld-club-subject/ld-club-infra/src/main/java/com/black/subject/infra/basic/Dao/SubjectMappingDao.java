package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.entity.SubjectMapping;
import com.black.subject.infra.mapper.SubjectMappingMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目分类关系表 服务实现类
 * </p>
 */
@Service
public class SubjectMappingDao extends ServiceImpl<SubjectMappingMapper, SubjectMapping> {
    /**
     * 查询映射关系
     * @param subjectInfoBO
     * @return
     */
    public List<SubjectMapping> getByLabelId(SubjectInfoBO subjectInfoBO) {
        return lambdaQuery().eq(SubjectMapping::getLabelId,subjectInfoBO.getLabelId())
                .eq(SubjectMapping::getCategoryId,subjectInfoBO.getCategoryId()).list();
    }

    public List<SubjectMapping> getLableId(SubjectMapping subjectMapping) {
        return lambdaQuery().eq(SubjectMapping::getCategoryId,subjectMapping.getCategoryId()).list();
    }
}
