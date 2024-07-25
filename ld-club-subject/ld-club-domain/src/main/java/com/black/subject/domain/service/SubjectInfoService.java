package com.black.subject.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.entity.SubjectInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目信息表 服务类
 * </p>
 *
 * @author <a>black</a>
 * @since 2024-07-16
 */

public interface SubjectInfoService {

    Boolean add(SubjectInfoBO subjectInfoBO);
}
