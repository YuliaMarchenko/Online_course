package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> showNonClosedCourse();
    List<Course> showAllCourse();
    Course findCourseById(String id);
    Course deleteCourse(String id);
    Course addStudentsToCourse(String id, String [] students);
    List<String> showStudentsOfCourse(String id);
    Course deleteStudentFromCourse(String id, String [] students);
    Course changeStatusClosedOfCourse(String id);
}
