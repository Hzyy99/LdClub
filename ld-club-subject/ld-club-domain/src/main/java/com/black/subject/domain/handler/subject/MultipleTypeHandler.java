package com.black.subject.domain.handler.subject;

import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.enums.SubjectInfoTypeEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.convert.MultipleSubjectConverter;
import com.black.subject.domain.convert.RadioSubjectConverter;
import com.black.subject.infra.basic.Bo.SubjectAnswerBO;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Bo.SubjectOptionBO;
import com.black.subject.infra.basic.Dao.SubjectMultipleDao;
import com.black.subject.infra.basic.entity.SubjectMultiple;
import com.black.subject.infra.basic.entity.SubjectRadio;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleDao subjectMultipleDao;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE_CHOICE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        LinkedList<SubjectMultiple> subjectRadios = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadios.add(subjectMultiple);
        });
        boolean batchResult = subjectMultipleDao.saveBatch(subjectRadios);
        AssertUtil.isTrue(batchResult, "多选题添加失败");
    }

    @Override
    public SubjectOptionBO queryOption(int subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(Long.valueOf(subjectId));
        List<SubjectMultiple> result = subjectMultipleDao.getByIdList(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
