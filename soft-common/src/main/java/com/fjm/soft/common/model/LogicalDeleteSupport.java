package com.fjm.soft.common.model;

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
