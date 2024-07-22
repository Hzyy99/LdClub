package com.black.subject.domain.service.impl;

import com.black.subject.domain.convert.SubjectInfoConverter;
import com.black.subject.domain.service.SubjectInfoService;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Dao.SubjectInfoDao;
import com.black.subject.infra.basic.entity.SubjectInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SubjectInfoServiceimpl implements SubjectInfoService {

    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 添加题目
     * @param subjectInfoBO
     * @return
     */
    @Override
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        boolean save = subjectInfoDao.save(subjectInfo);
        return save;
    }
}
