package com.biabuluo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.biabuluo.utils.SecurityContextUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date;
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        date = Date.from(zdt.toInstant());
        Long userId = null;
        try{
            userId = SecurityContextUtil.getUserId();
        }catch (Exception e){
            //注册是没有userid
            e.printStackTrace();
            userId = -1L;
        }

        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date;
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        date = Date.from(zdt.toInstant());
        this.setFieldValByName("updateTime", date, metaObject);
        this.setFieldValByName("updateBy", SecurityContextUtil.getUserId(), metaObject);
    }
}
