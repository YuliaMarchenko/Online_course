package com.telran.onlineCourse.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder

public class Course {
    private String id;
    private String name;
    private List<String> students;
    private boolean isClosed;
    private LocalDateTime updatedOn;
    private LocalDateTime createdOn;
}
