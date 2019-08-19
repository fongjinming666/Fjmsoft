package com.fjm.soft.model.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fjm.soft.model.BaseObject;
import com.fjm.soft.model.LogicalDeleteSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements BaseObject, LogicalDeleteSupport, Serializable {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String account;

    private String name;

    private String password;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    private Long updateTime;

   // @TableField(fill = FieldFill.INSERT)
    private boolean deleted;

    @TableField(fill = FieldFill.INSERT)
    private Long deleteTime;
}
