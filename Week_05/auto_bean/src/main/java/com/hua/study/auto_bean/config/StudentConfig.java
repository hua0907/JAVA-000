package com.hua.study.auto_bean.config;

import com.hua.study.auto_bean.pojo.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(name = "student.enable", havingValue = "true", matchIfMissing = true)
public class StudentConfig {

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student student() {
        Student student = new Student();
        student.setName("hua");
        student.setAge(10);
        return student;
    }

}
