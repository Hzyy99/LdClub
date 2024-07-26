package com.black.subject.infra.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 题目信息表
 * </p>
 *
 * @author black
 * @since 2024-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("subject_info")
public class SubjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题目名称
     */
    @TableField("subject_name")
    private String subjectName;

    /**
     * 题目难度
     */
    @TableField("subject_difficult")
    private Integer subjectDifficult;

    /**
     * 出题人名
     */
    @TableField("settle_name")
    private String settleName;

    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    @TableField("subject_type")
    private Integer subjectType;

    /**
     * 题目分数
     */
    @TableField("subject_score")
    private Integer subjectScore;

    /**
     * 题目解析
     */
    @TableField("subject_parse")
    private String subjectParse;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    private Integer isDeleted;


}
