package com.telran.onlineCourse.controller;

import com.telran.onlineCourse.entities.Course;
import com.telran.onlineCourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService repository;

    @PostMapping("/courses")
    Course createCourse(@RequestBody Course course) {
        Course newCourse = repository.createCourse(course);
        return courseOrError(newCourse, HttpStatus.CONFLICT);
    }

    @GetMapping("/courses")
    List<Course> showCourses(@RequestParam("show-closed") boolean showClosed) {
        if (showClosed) return repository.showAllCourse();
        else return repository.showNonClosedCourse();
    }

    @GetMapping("/courses/{id}")
    Course findCourseById(@PathVariable("id") String id) {
        Course newCourse =  repository.findCourseById(id);
        return courseOrError(newCourse, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/courses/{id}")
    Course deleteCourse(@PathVariable("id") String id) {
        Course course = repository.deleteCourse(id);
        return courseOrError(course, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/courses/{id}/students")
    Course addStudentsToCourse(@PathVariable("id") String id, @RequestParam("names") String names) {
        Course course = repository.addStudentsToCourse(id, names.split(","));
        return courseOrError(course, HttpStatus.NOT_FOUND);
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
        return courseOrError(course, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/courses/{id}/toggle-course")
    Course changeStatusClosedOfCourse(@PathVariable("id") String id) {
        Course course = repository.changeStatusClosedOfCourse(id);
        return courseOrError(course, HttpStatus.NOT_FOUND);
    }

    private Course courseOrError(Course course, HttpStatus notFound) {
        if (course == null){
            throw new ResponseStatusException(notFound);
        }
        return course;
    }
}
