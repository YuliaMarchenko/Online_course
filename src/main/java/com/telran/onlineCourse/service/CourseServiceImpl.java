package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CourseServiceImpl implements CourseService {
    private Map<String, Course> courses = new HashMap<>();

    @Override
    public boolean createCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        course.setCreatedOn(LocalDateTime.now());
        course.setUpdatedOn(LocalDateTime.now());
        courses.put(course.getId(), course);
        return true;
    }

    @Override
    public List<Course> showNonClosedCourse() {
        return courses.values().stream()
                .filter(course -> course.isClosed())
                .toList();
    }

    @Override
    public List<Course> showAllCourse() {
        return courses.values().stream().toList();
    }

    @Override
    public Course findCourseById(String id) {
        return courses.get(id);

    }

    @Override
    public boolean deleteCourse(String id) {
        courses.remove(id);
        return true;
    }

    @Override
    public boolean addStudentsToCourse(String id, String[] students) {
        for(String student: students) {
            courses.get(id).getStudents().add(student);
        }
        courses.get(id).setUpdatedOn(LocalDateTime.now());
        return true;
    }

    @Override
    public List<String> showStudentsOfCourse(String id) {
        return courses.get(id).getStudents();
    }

    @Override
    public boolean deleteStudentFromCourse(String id, String [] students) {
        for(String student: students) {
            courses.get(id).getStudents().remove(student);
        }
        courses.get(id).setUpdatedOn(LocalDateTime.now());
        return true;
    }

    @Override
    public boolean changeStatusClosedOfCourse(String id) {
        if (courses.get(id).isClosed()) {
            courses.get(id).setClosed(false);
        } else courses.get(id).setClosed(true);
        courses.get(id).setUpdatedOn(LocalDateTime.now());
        return true;
    }
}
