package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectMultiple;
import com.black.subject.infra.basic.entity.SubjectRadio;
import com.black.subject.infra.mapper.SubjectMultipleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 多选题信息表 服务实现类
 * </p>
 */
@Service
public class SubjectMultipleDao extends ServiceImpl<SubjectMultipleMapper, SubjectMultiple> {

    public List<SubjectMultiple> getByIdList(SubjectMultiple subjectMultiple) {
        return lambdaQuery().eq(SubjectMultiple::getSubjectId, subjectMultiple.getSubjectId()).list();
    }
}
