package com.black.subject.domain.handler.subject;

import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.enums.SubjectInfoTypeEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.convert.BriefSubjectConverter;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Bo.SubjectOptionBO;
import com.black.subject.infra.basic.Dao.SubjectBriefDao;
import com.black.subject.infra.basic.entity.SubjectBrief;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class BriefTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefDao subjectBriefDao;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        boolean save = subjectBriefDao.save(subjectBrief);
        AssertUtil.isTrue(save,"简单题添加失败");
    }

    @Override
    public SubjectOptionBO queryOption(int subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefDao.getById(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
