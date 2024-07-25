package com.black.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举
 * 1单选 2多选 3判断 4简答
 */
@Getter
public enum SubjectInfoTypeEnum {

    RADIO(1,"单选"),
    MULTIPLE_CHOICE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");

    private Integer code;
    private String desc;

    SubjectInfoTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static SubjectInfoTypeEnum getEnumByCode(Integer code) {
        for (SubjectInfoTypeEnum subjectInfoTypeEnum : SubjectInfoTypeEnum.values()) {
            if (subjectInfoTypeEnum.getCode().equals(code)) {
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }

}
