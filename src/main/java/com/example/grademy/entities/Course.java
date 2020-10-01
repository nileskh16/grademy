package com.example.grademy.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @Column(name = "RATINGS")
    private String ratings;

    @Column(name = "LANGUAGE")
    private String language;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "COURSE_TEACHER",
            joinColumns = @JoinColumn(name = "FK_COURSE"),
            inverseJoinColumns = @JoinColumn(name = "FK_TEACHER")
    )
    private Set<Teacher> teachers;
}
