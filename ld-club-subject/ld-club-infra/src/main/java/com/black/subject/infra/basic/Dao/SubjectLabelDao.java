package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectLabel;
import com.black.subject.infra.mapper.SubjectLabelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目标签表 服务实现类
 * </p>
 */
@Service
public class SubjectLabelDao extends ServiceImpl<SubjectLabelMapper, SubjectLabel>  {
    /**
     *查询标签列表
     */
    public List<SubjectLabel> queryLabelByCategoryId(SubjectLabel subjectCategory) {
        return lambdaQuery().eq(SubjectLabel::getCategoryId, subjectCategory.getCategoryId()).list();
    }

    /**
     * 获得标签列表
     * @param collectLabelId
     * @return
     */
    public List<SubjectLabel> getLabelById(List<Long> collectLabelId) {
        return getBaseMapper().selectBatchIds(collectLabelId);
    }
}
