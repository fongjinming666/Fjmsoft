package com.fjm.soft.logic.impl;

import com.fjm.soft.logic.ISoftLogic;
import com.fjm.soft.mapper.base.SysUserMapper;
import com.fjm.soft.common.model.base.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import static com.fjm.soft.common.utils.idgen.SnowFlake.createId;

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
        SysUser s = new SysUser()
                .setId(createId())
                .setAccount("accout").setName("fjm").setPassword("123456")
                .setUpdateTime(System.currentTimeMillis());
        sysUserMapper.insert(s);
        return "hello ";
    }
}
