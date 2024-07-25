package com.black.subject.domain.handler.subject;

import com.black.subject.common.enums.SubjectInfoTypeEnum;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目类型处理器工厂
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlers;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    /**
     * 获取处理器
     * @param subjectType
     * @return
     */
    public SubjectTypeHandler getHandler(int subjectType) {
        SubjectInfoTypeEnum enumByCode = SubjectInfoTypeEnum.getEnumByCode(subjectType);
        return handlerMap.get(enumByCode);

    }
    /**
     * 初始化Bean
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        subjectTypeHandlers.stream().forEach(subjectTypeHandler -> {
            handlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        });
    }
}
