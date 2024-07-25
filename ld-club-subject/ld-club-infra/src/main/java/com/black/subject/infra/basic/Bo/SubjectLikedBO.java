package com.black.subject.infra.basic.Bo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞表 bo
 */
@Data
public class SubjectLikedBO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

}

