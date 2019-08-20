package com.fjm.soft;

import com.fjm.soft.common.utils.idgen.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-16 16:02
 * @Description:
 */
@SpringBootApplication
public class SoftApplication {

    /**
     * 数据中心.
     */
    @Value("${soft.datacenterId}")
    private long datacenterId;

    /**
     * 机器标识.
     */
    @Value("${soft.machineId}")
    private long machineId;


    @Bean
    public SnowFlake getSnowFlake(){
        return new SnowFlake(datacenterId, machineId);
    }

    public static void main(String[] args) {
        SpringApplication.run(SoftApplication.class, args);
    }
}
