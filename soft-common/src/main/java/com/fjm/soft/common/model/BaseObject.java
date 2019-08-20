package com.fjm.soft.common.model;

/**
 * 封装一般实体类基础属性
 *
 * @Author: fongjinming
 * @CreateTime: 2019-08-14 14:44
 * @Description:
 */
public interface BaseObject {

    /**
     * id.
     *
     * @return id
     */
    Long getId();

    /**
     * 创建时间.
     *
     * @return 创建时间
     */
    Long getCreateTime();

}
