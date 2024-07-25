package com.black.subject.domain.handler.subject;

import com.black.subject.common.enums.IsDeletedFlagEnum;
import com.black.subject.common.enums.SubjectInfoTypeEnum;
import com.black.subject.common.utils.AssertUtil;
import com.black.subject.domain.convert.RadioSubjectConverter;
import com.black.subject.infra.basic.Bo.SubjectAnswerBO;
import com.black.subject.infra.basic.Bo.SubjectInfoBO;
import com.black.subject.infra.basic.Bo.SubjectOptionBO;
import com.black.subject.infra.basic.Dao.SubjectRadioDao;
import com.black.subject.infra.basic.entity.SubjectRadio;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioDao subjectRadioDao;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    /**
     * 单选添加
     * @param subjectInfoBO
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        LinkedList<SubjectRadio> subjectRadios = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadios.add(subjectRadio);
        });
        boolean batchResult = subjectRadioDao.saveBatch(subjectRadios);
        AssertUtil.isTrue(batchResult, "单选题添加失败");
    }

    /**
     * 查询Option
     * @param subjectId
     * @return
     */
    @Override
    public SubjectOptionBO queryOption(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectId));
        List<SubjectRadio> result = subjectRadioDao.getByIdList(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = RadioSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
