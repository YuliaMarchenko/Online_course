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
    Course deleteCourse(@PathVariable("id") String id) {
        Course course = repository.deleteCourse(id);
        if (course == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return course;
    }

    @PutMapping("/courses/{id}/students")
    Course addStudentsToCourse(@PathVariable("id") String id, @RequestParam("names") String names) {
        Course course = repository.addStudentsToCourse(id, names.split(","));
        if (course == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return course;
    }

    @GetMapping("/courses/{id}/students")
    List<String> showStudentsOfCourse(@PathVariable("id") String id) {
        List<String> students = repository.showStudentsOfCourse(id);
        if (students == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return students;
    }

    @DeleteMapping("/courses/{id}/students")
    Course deleteStudentFromCourse(@PathVariable("id") String id, @RequestParam("names") String names) {
        Course course = repository.deleteStudentFromCourse(id, names.split(","));
        if (course == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return course;
    }

    @PutMapping("/courses/{id}/toggle-course")
    Course changeStatusClosedOfCourse(@PathVariable("id") String id) {
        Course course = repository.changeStatusClosedOfCourse(id);
        if (course == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } return course;
    }
}
