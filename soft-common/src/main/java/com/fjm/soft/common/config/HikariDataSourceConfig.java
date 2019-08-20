package com.fjm.soft.common.config;

import com.fjm.soft.common.idgen.SnowFlake;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-04-23 17:18
 * @Description:
 */
@Configuration
public class HikariDataSourceConfig {

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

    /**
     * shiro数据源(单数据源-hikari连接池).
     *
     * @return
     */
    @Bean(name = "hikariDataSource")
    @Qualifier("hikariDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
