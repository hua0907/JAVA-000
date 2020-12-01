package com.study.hua.aop_switch.holder;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();


    public static String get() {
        return contextHolder.get();
    }

    public static void set(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }

    public static void clear() {
        contextHolder.remove();
    }


}
