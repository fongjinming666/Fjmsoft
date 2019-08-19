package com.fjm.soft.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fjm.soft.model.BaseObject;
import com.fjm.soft.model.LogicalDeleteSupport;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-16 17:17
 * @Description:
 */

@Data
@Accessors(chain = true)
@TableName(value = "sys_user")
@KeySequence(value = "snowFlakeIdGenerator")
public class SysUser implements BaseObject, LogicalDeleteSupport, Serializable {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String account;

    private String name;

    private String password;

    private Long createTime;

    private Long updateTime;

    private boolean deleted;

    private Long deleteTime;
}
