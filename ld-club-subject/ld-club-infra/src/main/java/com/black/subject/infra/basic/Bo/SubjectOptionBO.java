package com.black.subject.infra.basic.Bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目dto
 */
@Data
public class SubjectOptionBO implements Serializable {

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

}

