package com.fjm.soft.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import org.springframework.stereotype.Component;

import static com.fjm.soft.utils.idgen.SnowFlake.createId;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-16 17:37
 * @Description:
 */
@Component
public class SnowFlakeIdGenerator implements IKeyGenerator {

    /**
     * uuid生成器
     *
     * @param incrementerName
     * @return
     */
    @Override
    public String executeSql(String incrementerName) {
        return "select " + createId() + " from dual";
    }
}
