package com.study.hua.aop_switch.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.study.hua.aop_switch.DataSource.MyDynamicDataSource;
import com.study.hua.aop_switch.DataSourceEnum.DataSourceEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return new DruidDataSource();
    }


    @Bean("slave_a")
    @ConfigurationProperties(prefix = "spring.datasource.slave.a")
    public DataSource slaveA() {
        return new DruidDataSource();
    }

    @Bean("slave_b")
    @ConfigurationProperties(prefix = "spring.datasource.slave.b")
    public DataSource slaveB() {
        return new DruidDataSource();
    }

    @Bean("slave_c")
    @ConfigurationProperties(prefix = "spring.datasource.slave.c")
    public DataSource slaveC() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.MASTER.getName(), master());
        dataSourceMap.put(DataSourceEnum.SLAVE_A.getName(), slaveA());
        dataSourceMap.put(DataSourceEnum.SLAVE_B.getName(), slaveB());
        dataSourceMap.put(DataSourceEnum.SLAVE_C.getName(), slaveC());

        MyDynamicDataSource dataSource = new MyDynamicDataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        //默认数据源
        dataSource.setDefaultTargetDataSource(master());
        return dataSource;
    }

}
