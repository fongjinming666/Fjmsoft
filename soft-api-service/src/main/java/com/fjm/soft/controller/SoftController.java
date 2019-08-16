package com.fjm.soft.controller;

import com.fjm.soft.SoftApplication;
import com.fjm.soft.logic.ISoftLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * spring boot 本身.
     */
    private static Logger logger = LoggerFactory.getLogger(SoftApplication.class);

    private static Logger loggerController = LoggerFactory.getLogger(SoftController.class);

    @Resource
    private ISoftLogic softLogic;

    @GetMapping("hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "someone", required = false) String name) {
        logger.info("test application logging...");
        loggerController.info("test controller logging...");
        return softLogic.sayHelloForSomeone(name);
    }
}
