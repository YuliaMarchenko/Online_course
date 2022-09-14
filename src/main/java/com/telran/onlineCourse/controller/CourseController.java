package com.telran.onlineCourse.controller;

import com.telran.onlineCourse.entities.Course;
import com.telran.onlineCourse.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CourseController {
    private final CourseService repository;

    public CourseController(CourseService repository) {
        this.repository = repository;
    }

    @PostMapping("/courses")
    Map<String, Boolean> createTask(@RequestBody Course course) {
        return result(repository.createCourse(course));
    }

    public Map<String, Boolean> result(boolean answer){
        Map<String, Boolean> result = new HashMap<>();
        result.put("status", answer);
        return result;
    }
}