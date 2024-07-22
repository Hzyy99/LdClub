package com.black.subject.domain.service.impl;

import com.black.subject.domain.convert.SubjectCategoryConverter;
import com.black.subject.domain.convert.SubjectLabelConverter;
import com.black.subject.domain.service.SubjectLabelService;
import com.black.subject.infra.basic.Bo.SubjectCategoryBO;
import com.black.subject.infra.basic.Bo.SubjectLabelBO;
import com.black.subject.infra.basic.Dao.SubjectLabelDao;
import com.black.subject.infra.basic.entity.SubjectCategory;
import com.black.subject.infra.basic.entity.SubjectLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SubjectLabelServiceimpl implements SubjectLabelService {

    @Resource
    private SubjectLabelDao subjectLabelDao;
    /**
     * 添加标签
     */
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        boolean save = subjectLabelDao.save(subjectLabel);
        return save;
    }
    /**
     * 修改标签
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        boolean result = subjectLabelDao.updateById(subjectLabel);
        return result;
    }
    /**
     * 删除标签
     */
    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBo) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBo);
        boolean result = subjectLabelDao.removeById(subjectLabel);
        return result;
    }
    /**
     * 根据分类id查询标签
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectCategory = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        List<SubjectLabel> ListLabel = subjectLabelDao.queryLabelByCategoryId(subjectCategory);
        List<SubjectLabelBO> subjectCategoryBOS = SubjectLabelConverter.INSTANCE.convertLabelToBoList(ListLabel);
        return subjectCategoryBOS;
    }
}
