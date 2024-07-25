package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectInfo;

import com.black.subject.infra.mapper.SubjectInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目信息表 服务实现类
 * </p>
 */
@Service
public class SubjectInfoDao extends ServiceImpl<SubjectInfoMapper, SubjectInfo> {

}
