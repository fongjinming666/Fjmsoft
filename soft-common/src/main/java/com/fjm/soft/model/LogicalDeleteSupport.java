/*
 * Project:    全课通平台 - 通用工具模块
 *
 * FileName:   LogicalDeleteSupport.java
 * CreateTime: 2018-06-27 15:16:35
 */
package com.fjm.soft.model;

/**
 * 让实体类支持逻辑删除的接口
 *
 * @Author: fongjinming
 * @CreateTime: 2019-08-14 14:44
 * @Description:
 */
public interface LogicalDeleteSupport {

    /**
     * 是否已删除.
     *
     * @return 是否已删除
     */
    boolean isDeleted();

    /**
     * 删除时间.
     *
     * @return 删除时间
     */
    Long getDeleteTime();

}
