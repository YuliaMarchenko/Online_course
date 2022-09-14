package com.telran.onlineCourse.controller;

import com.telran.onlineCourse.entities.Course;
import com.telran.onlineCourse.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/courses")
    List<Course> showCourses(@RequestParam("show-closed") boolean showClosed){
        if (showClosed) return repository.showAllCourse();
        else return repository.showNonClosedCourse();
    }

    @GetMapping("/courses/{id}")
    Course findCourseById(@PathVariable("id") String id){
        return repository.findCourseById(id);
    }

    @DeleteMapping("/courses/{id}")
    Map<String,Boolean> deleteCourse(@PathVariable("id") String id){
        return result(repository.deleteCourse(id));
    }

    @PutMapping("/courses/{id}/students")
    Map<String, Boolean> addStudentsToCourse(@PathVariable("id") String id, @RequestParam("names") String names){
        return result(repository.addStudentsToCourse(id, names.split(",")));
    }

    public Map<String, Boolean> result(boolean answer){
        Map<String, Boolean> result = new HashMap<>();
        result.put("status", answer);
        return result;
    }
}
