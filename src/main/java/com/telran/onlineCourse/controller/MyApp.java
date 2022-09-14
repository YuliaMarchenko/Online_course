package com.telran.onlineCourse.controller;

import com.telran.onlineCourse.service.CourseService;
import com.telran.onlineCourse.service.CourseServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyApp {

    @Bean
    public CourseService tasksManager() {
        return new CourseServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
