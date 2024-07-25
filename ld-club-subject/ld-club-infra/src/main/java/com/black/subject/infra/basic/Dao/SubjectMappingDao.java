package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectMapping;
import com.black.subject.infra.mapper.SubjectMappingMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目分类关系表 服务实现类
 * </p>
 */
@Service
public class SubjectMappingDao extends ServiceImpl<SubjectMappingMapper, SubjectMapping> {

}
