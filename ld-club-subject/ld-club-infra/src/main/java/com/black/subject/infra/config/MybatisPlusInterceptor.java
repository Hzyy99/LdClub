package com.black.subject.infra.config;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 公共字段填充
 */
@Component
@Slf4j
public class MybatisPlusInterceptor implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime",LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createTime",DateTime.class, DateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictInsertFill(metaObject, "updateTime",LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", Date.class, DateTime.now());
    }
}
