package com.black.subject.domain.service;

import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.config.PageResult;

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

    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO);
}
