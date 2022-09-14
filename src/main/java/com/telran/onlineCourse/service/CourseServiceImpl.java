package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;
import org.springframework.beans.factory.annotation.Value;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    private Map<String, Course> courses = new HashMap<>();

    @Override
    public boolean createCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        courses.put(course.getId(), course);
        return true;
    }

    @Override
    public List<Course> showNonClosedCourse() {
        return null;
    }

    @Override
    public List<Course> showAllCourse() {
        return null;
    }

    @Override
    public Course findCourseById() {
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }

    @Override
    public boolean addStudentsToCourse(String[] students) {
        return false;
    }

    @Override
    public List<String> showStudentsOfCourse() {
        return null;
    }

    @Override
    public boolean deleteStudentFromCourse(String name, int id) {
        return false;
    }

    @Override
    public boolean changeStatusClosedOfCourse(int id) {
        return false;
    }
}
