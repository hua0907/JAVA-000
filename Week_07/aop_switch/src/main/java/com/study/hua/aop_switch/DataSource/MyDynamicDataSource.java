package com.study.hua.aop_switch.DataSource;

import com.study.hua.aop_switch.holder.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class MyDynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceContextHolder.get();
        logger.info("当前数据源名称是：" + dataSourceName);
        return dataSourceName;
    }
}
