package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;

import java.util.List;

public interface CourseService {
    boolean createCourse(Course course);
    List<Course> showNonClosedCourse();
    List<Course> showAllCourse();
    Course findCourseById(String id);
    boolean deleteCourse(String id);
    boolean addStudentsToCourse(String id, String [] students);
    List<String> showStudentsOfCourse();
    boolean deleteStudentFromCourse(String id, String name);
    boolean changeStatusClosedOfCourse(String id);
}
