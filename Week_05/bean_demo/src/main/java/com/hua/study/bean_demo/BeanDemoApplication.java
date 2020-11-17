package com.hua.study.bean_demo;

import com.hua.study.bean_demo.pojo.*;
import com.hua.study.bean_demo.selector.MyImportSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Import({Student1.class, MyImportSelector.class})
@ImportResource("classpath:spring-beans.xml")
public class BeanDemoApplication implements ApplicationRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(BeanDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        // @comment注册
        Student s = context.getBean(Student.class);
        System.out.println(s);

        // @Import注册
        Student1 s1 = context.getBean(Student1.class);
        System.out.println(s1);

        // @bean注册
        Student2 s2 = context.getBean(Student2.class);
        System.out.println(s2);

        //xml配置文件注册
        Student3 s3 = context.getBean(Student3.class);
        System.out.println(s3);

        //ImportSelector 和 @Import
        Student4 s4 = context.getBean(Student4.class);
        System.out.println(s4);

        //BeanFactoryAware
        Student5 s5 = context.getBean(Student5.class);
        System.out.println(s5);
    }
}
