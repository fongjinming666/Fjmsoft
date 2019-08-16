package com.fjm.soft.logic.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjm.soft.logic.ISoftLogic;
import com.fjm.soft.mapper.SysUserMapper;
import com.fjm.soft.model.base.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-15 19:07
 * @Description:
 */
@Service
public class SoftLogicImpl implements ISoftLogic {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public String sayHelloToSomeone(String someone) {
        if (StringUtils.isEmpty(someone)) {
            return "say hello to who ?";
        }
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, someone));
        return "hello " + sysUser.getName()
                + " !, your account is " + sysUser.getAccount()
                + ",and your password is " + sysUser.getPassword();
    }
}
