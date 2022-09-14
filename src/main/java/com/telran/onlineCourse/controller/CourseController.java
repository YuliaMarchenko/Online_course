package com.telran.onlineCourse.controller;

import com.telran.onlineCourse.entities.Course;
import com.telran.onlineCourse.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    Course createTask(@RequestBody Course course) {
        Course newCourse = repository.createCourse(course);
        if (newCourse == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } return newCourse;
    }

    @GetMapping("/courses")
    List<Course> showCourses(@RequestParam("show-closed") boolean showClosed) {
        if (showClosed) return repository.showAllCourse();
        else return repository.showNonClosedCourse();
    }

    @GetMapping("/courses/{id}")
    Course findCourseById(@PathVariable("id") String id) {
        Course newCourse =  repository.findCourseById(id);
        if (newCourse == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return newCourse;
    }

    @DeleteMapping("/courses/{id}")
    Map<String, Boolean> deleteCourse(@PathVariable("id") String id) {
        if (!repository.deleteCourse(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return result(true);
    }

    @PutMapping("/courses/{id}/students")
    Map<String, Boolean> addStudentsToCourse(@PathVariable("id") String id, @RequestParam("names") String names) {
        return result(repository.addStudentsToCourse(id, names.split(",")));
    }

    @GetMapping("/courses/{id}/students")
    List<String> showStudentsOfCourse(@PathVariable("id") String id) {
        return repository.showStudentsOfCourse(id);
    }

    @DeleteMapping("/courses/{id}/students")
    Map<String, Boolean> deleteStudentFromCourse(@PathVariable("id") String id, @RequestParam("names") String names) {
        return result(repository.deleteStudentFromCourse(id, names.split(",")));
    }

    @PutMapping("/courses/{id}/toggle-course")
    Map<String, Boolean> changeStatusClosedOfCourse(@PathVariable("id") String id) {
        return result(repository.changeStatusClosedOfCourse(id));
    }

    public Map<String, Boolean> result(boolean answer) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("status", answer);
        return result;
    }
}
