package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> showNonClosedCourse();
    List<Course> showAllCourse();
    Course findCourseById(String id);
    boolean deleteCourse(String id);
    boolean addStudentsToCourse(String id, String [] students);
    List<String> showStudentsOfCourse(String id);
    boolean deleteStudentFromCourse(String id, String [] students);
    boolean changeStatusClosedOfCourse(String id);
}
