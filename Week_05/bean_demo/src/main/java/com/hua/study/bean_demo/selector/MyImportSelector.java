package com.hua.study.bean_demo.selector;

import com.hua.study.bean_demo.pojo.Student4;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                Student4.class.getName()
        };
    }
}
