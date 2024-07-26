package com.black.subject.infra.basic.Dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;

import com.black.subject.infra.basic.entity.SubjectInfo;
import com.black.subject.infra.mapper.SubjectInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目信息表 服务实现类
 * </p>
 */
@Service
public class SubjectInfoDao extends ServiceImpl<SubjectInfoMapper, SubjectInfo> {
    /**
     * 分页查询
     * @param
     * @param subjectIds
     * @return
     */
    public Page<SubjectInfo> getSubjectPage(List<Long> subjectIds,SubjectInfoBO subjectInfoBO) {
        LambdaQueryWrapper<SubjectInfo> subjectInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        subjectInfoLambdaQueryWrapper.in(SubjectInfo::getId, subjectIds);
        Page<SubjectInfo> subjectInfoPage = new Page<>();
        subjectInfoPage.setPages(subjectInfoBO.getPageNo());
        subjectInfoPage.setSize(subjectInfoBO.getPageSize());
        return getBaseMapper().selectPage(subjectInfoPage, subjectInfoLambdaQueryWrapper);
    }
}
