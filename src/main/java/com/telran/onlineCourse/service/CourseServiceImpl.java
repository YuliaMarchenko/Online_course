package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;

import java.util.*;

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
        return true;
    }

    @Override
    public List<String> showStudentsOfCourse() {
        return null;
    }

    @Override
    public boolean deleteStudentFromCourse(String name, String id) {
        return false;
    }

    @Override
    public boolean changeStatusClosedOfCourse(String id) {
        return false;
    }
}
