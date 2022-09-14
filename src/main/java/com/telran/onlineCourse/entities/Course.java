package com.telran.onlineCourse.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder

public class Course {
    private String id;
    private String name;
    private List<String> students;
    private boolean isClosed;
    private LocalDate updatedOn;
    private LocalDate createdOn;
}
