package com.example.springappwithannotations.main;

import com.example.springappwithannotations.bean.GreetingService;
import com.example.springappwithannotations.bean.LifeCycleBean;
import com.example.springappwithannotations.loose.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args)
    {

        //Inversion of control -- we have given the control to the spring
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationBeanContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext("config");
//        GreetingService greetingService=(GreetingService) context.getBean("myBean");
//        greetingService.sayHello();
//        GreetingService greetingService=context.getBean(GreetingService.class); // no requirement of casting
//        greetingService.sayHello();

        // Error will come because Multiple beans of the same interface type — which one should Spring autowire?
//        UserService userServiceEmail = context.getBean(UserService.class);
//        userServiceEmail.notifyUser("Hey there!");
//        UserService userServiceSMS =context.getBean(UserService.class);
//        userServiceSMS.notifyUser("Hey there!");

        //After adding Qualifier or Primary
//        UserService userServiceEmail = context.getBean(UserService.class);
//        userServiceEmail.notifyUser("Hey there!");
//        UserService userServiceSMS =context.getBean(UserService.class);
//        userServiceSMS.notifyUser("Hey there!");

        System.out.println("Starting Spring Application Context");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.springappwithannotations.config");
        System.out.println("Retrieving Lifecycle Bean");
        LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
        lifeCycleBean.performTask();
        System.out.println("Closing Spring Context");

    }
}