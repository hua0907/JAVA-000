package com.study.hua.aop_switch.config;


import com.study.hua.aop_switch.DataSource.MyDynamicDataSource;
import com.study.hua.aop_switch.DataSourceEnum.DataSourceEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class DataSourceConfig {

    private static List<String> SLAVE_LIST = Arrays.stream(DataSourceEnum.values()).skip(0).map(DataSourceEnum::getName).collect(Collectors.toList());

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.slave.a")
    public DataSource slaveA() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave.b")
    public DataSource slaveB() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave.c")
    public DataSource slaveC() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
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
