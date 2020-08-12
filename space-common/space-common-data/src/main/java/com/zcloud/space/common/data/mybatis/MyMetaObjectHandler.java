package com.zcloud.space.common.data.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 自定义公共字段填充处理器
 * @Author Y
 * @Date 2020-01-06 18:21
 */
//@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作自动填充
     *
     * @author:
     * @param:
     * @return:
     * @date:
     */
    @Override
    public void insertFill(MetaObject metaObject) {

    }

    /**
     * 修改操作自动填充
     *
     * @author:
     * @param:
     * @return:
     * @date:
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
