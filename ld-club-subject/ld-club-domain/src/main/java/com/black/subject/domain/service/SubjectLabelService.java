package com.black.subject.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.black.subject.infra.basic.Bo.SubjectLabelBO;
import com.black.subject.infra.basic.entity.SubjectLabel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题目标签表 服务类
 * </p>
 *
 * @author <a>black</a>
 * @since 2024-07-16
 */

public interface SubjectLabelService{

    Boolean add(SubjectLabelBO subjectLabelBO);

    Boolean update(SubjectLabelBO subjectLabelBO);

    Boolean delete(SubjectLabelBO subjectLabelBo);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
