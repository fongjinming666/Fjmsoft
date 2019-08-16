package com.fjm.soft.logic.impl;

import com.fjm.soft.logic.ISoftLogic;
import org.springframework.stereotype.Service;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-15 19:07
 * @Description:
 */
@Service
public class SoftLogicImpl implements ISoftLogic {

    @Override
    public String sayHelloForSomeone(String someone) {
        return "hello " + someone + " !";
    }
}
