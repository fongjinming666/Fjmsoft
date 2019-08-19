package com.fjm.soft.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-16 16:17
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.fjm.soft.mapper")
public class MybatisPlusConfig {

    /**
     * mybatis-plus 配置
     */
    @Resource
    public MybatisPlusProperties mybatisPlusProperties;

    @Autowired
    @Qualifier("hikariDataSource")
    private DataSource hikariDataSource;

    /**
     * 性能分析拦截器，不建议生产使用 用来观察 SQL 执行情况及执行时长, 默认dev,staging 环境开启
     *
     * @return com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
     * @author fxbin
     */
    @Bean
    @Profile({"dev"})
    public PerformanceInterceptor performanceInterceptor() {
        //启用性能分析插件, SQL是否格式化 默认false,此处开启
        return new PerformanceInterceptor().setFormat(true);
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    /**
     * 具备逻辑删除
     *
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * @return
     */
    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() {
        try {
            MybatisSqlSessionFactoryBean sqlSessionFactoryBean = mybatisSqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(hikariDataSource);
            return sqlSessionFactoryBean;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MybatisSqlSessionFactoryBean
     *
     * @return
     * @throws IOException
     */
    private MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setGlobalConfig(mybatisPlusProperties.getGlobalConfig());
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisPlusProperties.getConfiguration());
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisPlusProperties.getMapperLocations()[0]));
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        return mybatisSqlSessionFactoryBean;
    }
}
