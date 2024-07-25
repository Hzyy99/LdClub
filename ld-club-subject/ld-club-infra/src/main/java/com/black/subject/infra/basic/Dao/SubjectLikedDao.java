package com.black.subject.infra.basic.Dao;

import com.black.subject.infra.basic.entity.SubjectLiked;

import com.black.subject.infra.mapper.SubjectLikedMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目点赞表 服务实现类
 * </p>
 */
@Service
public class SubjectLikedDao extends ServiceImpl<SubjectLikedMapper, SubjectLiked> {

}
