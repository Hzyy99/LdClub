package com.black.subject.domain.handler.subject;

import com.black.subject.common.enums.SubjectInfoTypeEnum;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Bo.SubjectOptionBO;


/**
 * 题目处理器
 */
public interface SubjectTypeHandler {

    SubjectInfoTypeEnum getHandlerType();

    void add(SubjectInfoBO subjectInfoBO);

    SubjectOptionBO queryOption(int subjectId);
}
