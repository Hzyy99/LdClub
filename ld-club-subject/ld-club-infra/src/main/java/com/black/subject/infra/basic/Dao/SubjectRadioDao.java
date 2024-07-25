package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectRadio;
import com.black.subject.infra.mapper.SubjectRadioMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 单选题信息表 服务实现类
 * </p>
 *
 * @author <a>black</a>
 * @since 2024-07-16
 */
@Service
public class SubjectRadioDao extends ServiceImpl<SubjectRadioMapper, SubjectRadio> {
    /**
     * 根据题目id查询单选题信息
     * @param subjectRadio
     * @return
     */
    public List<SubjectRadio> getByIdList(SubjectRadio subjectRadio) {
        return lambdaQuery().eq(SubjectRadio::getSubjectId, subjectRadio.getSubjectId()).list();
    }
}
