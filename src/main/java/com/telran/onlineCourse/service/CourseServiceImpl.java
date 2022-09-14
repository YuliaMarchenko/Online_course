package com.telran.onlineCourse.service;

import com.telran.onlineCourse.entities.Course;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {
    private Map<String, Course> courses = new HashMap<>();

    @Override
    public Course createCourse(Course course) {
        if (isNameUnique(course)) {
            course.setId(UUID.randomUUID().toString());
            course.setCreatedOn(LocalDateTime.now());
            course.setUpdatedOn(LocalDateTime.now());
            courses.put(course.getId(), course);
            return course;
        }
        return null;

    }

    private boolean isNameUnique(Course course) {
        return !courses.values().stream()
                .filter(c -> c.getName().equals(course.getName()))
                .findAny()
                .isPresent();
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
    public Course deleteCourse(String id) {
        return courses.remove(id);
    }

    @Override
    public Course addStudentsToCourse(String id, String[] students) {
        Course course = courses.get(id);
        if (course == null) return null;
        Set<String> existingStudents = new HashSet<>(course.getStudents());
        for (String student : students) {
            if (!existingStudents.contains(student))
                course.getStudents().add(student);
        }
        course.setUpdatedOn(LocalDateTime.now());
        return course;
    }

    @Override
    public List<String> showStudentsOfCourse(String id) {
        Course course = courses.get(id);
        if (course == null) return null;
        return course.getStudents();
    }

    @Override
    public Course deleteStudentFromCourse(String id, String[] students) {
        Course course = courses.get(id);
        if (course == null) return null;
        for (String student : students) {
            course.getStudents().remove(student);
        }
        course.setUpdatedOn(LocalDateTime.now());
        return course;
    }

    @Override
    public Course changeStatusClosedOfCourse(String id) {
        Course course = courses.get(id);
        if (course == null) return null;
        if (course.isClosed()) {
            course.setClosed(false);
        } else course.setClosed(true);
        course.setUpdatedOn(LocalDateTime.now());
        return course;
    }
}
