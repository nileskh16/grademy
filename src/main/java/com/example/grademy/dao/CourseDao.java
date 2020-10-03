package com.example.grademy.dao;

import lombok.Data;

import java.util.List;

@Data
public class CourseDao {
    private String name;
    private String language;
    private String description;
    private String ratings;
    private List<String> teacherIds;
}
