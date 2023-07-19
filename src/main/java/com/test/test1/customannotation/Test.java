package com.test.test1.customannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class Test {
    public static void main(String[] args) {
//        using reflection
        Demo d = new Demo();
        Class<? extends Demo> aClass = d.getClass();
        CustomAnnotation annotation = aClass.getAnnotation(CustomAnnotation.class);
        System.out.println(annotation.name()+"-- "+annotation.value());
    }
}
