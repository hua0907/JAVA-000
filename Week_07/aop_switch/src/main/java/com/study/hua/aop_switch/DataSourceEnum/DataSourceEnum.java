package com.study.hua.aop_switch.DataSourceEnum;

public enum DataSourceEnum {


    MASTER("masterDataSource"),
    SLAVE_A("slave_a_dataSource"),
    SLAVE_B("slave_b_dataSource"),
    SLAVE_C("slave_c_dataSource");

    private String name;

    DataSourceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
