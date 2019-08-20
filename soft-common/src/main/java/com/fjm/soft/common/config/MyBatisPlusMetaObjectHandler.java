package com.fjm.soft.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fjm.soft.common.model.BaseObject;
import com.fjm.soft.common.model.LogicalDeleteSupport;
import org.apache.ibatis.reflection.MetaObject;

import static com.fjm.soft.common.utils.DateUtils.getTransactionDate;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-19 19:11
 * @Description:
 */
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof BaseObject) {
            this.setFieldValByName("createTime", getTransactionDate().getTime(), metaObject);
        }
        if (metaObject.getOriginalObject() instanceof LogicalDeleteSupport) {
            this.setFieldValByName("deleted", false, metaObject);
            this.setFieldValByName("deleteTime", 0L, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
