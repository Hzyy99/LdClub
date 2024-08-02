package com.black.subject.domain.service;


import com.black.subject.common.entity.PageResult;
import com.black.subject.infra.basic.entity.SubjectInfoEs;


public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}
