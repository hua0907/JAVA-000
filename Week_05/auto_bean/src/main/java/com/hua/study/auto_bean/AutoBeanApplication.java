package com.hua.study.auto_bean;

import com.hua.study.auto_bean.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutoBeanApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AutoBeanApplication.class, args);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {
        Student student = applicationContext.getBean(Student.class);
        System.out.println("自动装备的student：" + student);
    }
}
