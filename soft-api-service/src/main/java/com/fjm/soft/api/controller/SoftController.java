package com.fjm.soft.api.controller;

import com.fjm.soft.api.logic.ISoftLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-15 19:08
 * @Description:
 */
@RequestMapping("/soft")
@RestController
public class SoftController {

    @Resource
    private ISoftLogic softLogic;

    @GetMapping("hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "someone", required = false) String name) {
        return softLogic.sayHelloToSomeone(name);
    }
}
