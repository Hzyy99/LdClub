package com.black.subject.infra.basic.Bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签bo
 */
@Data
public class SubjectLabelBO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标签分类
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 分类id
     */
    private Long categoryId;

}

