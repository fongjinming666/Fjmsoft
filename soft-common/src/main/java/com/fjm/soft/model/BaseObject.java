/*
 * Project:    全课通平台 - 通用工具模块
 *
 * FileName:   BaseObject.java
 * CreateTime: 2018-06-27 15:49:51
 */
package com.fjm.soft.model;

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
