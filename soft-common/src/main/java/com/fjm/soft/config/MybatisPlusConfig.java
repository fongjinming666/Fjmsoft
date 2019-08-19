package com.fjm.soft.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
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

    @Autowired
    @Qualifier("hikariDataSource")
    private DataSource hikariDataSource;

    /**
     * mybatis-plus 配置
     */
    @Resource
    public MybatisPlusProperties mybatisPlusProperties;

    /**
     * 性能分析拦截器，不建议生产使用 用来观察 SQL 执行情况及执行时长, 默认dev,staging 环境开启
     *
     * @return com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
     * @author fxbin
     */
    @Bean
    @Profile({"dev"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(500000000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
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
     * mybatisplus自定义填充公共字段 ,即没有传的字段自动填充.
     *
     * @return
     */
    @Bean
    public MyBatisPlusMetaObjectHandler getMyBatisPlusMetaObjectHandler() {
        return new MyBatisPlusMetaObjectHandler();
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
     * MybatisSqlSessionFactoryBean
     *
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
        mybatisPlusProperties = getMybatisPlusProperties();
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setGlobalConfig(mybatisPlusProperties.getGlobalConfig());
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisPlusProperties.getConfiguration());
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisPlusProperties.getMapperLocations()[0]));
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{
                performanceInterceptor(),
                paginationInterceptor(),
                new OptimisticLockerInterceptor()
        });
        return mybatisSqlSessionFactoryBean;
    }

    /**
     * MybatisPlusProperties
     *
     * @return
     */
    public MybatisPlusProperties getMybatisPlusProperties() {
        mybatisPlusProperties.getGlobalConfig().setMetaObjectHandler(getMyBatisPlusMetaObjectHandler());
        mybatisPlusProperties.getGlobalConfig().setSqlInjector(sqlInjector());
        return mybatisPlusProperties;
    }
}
