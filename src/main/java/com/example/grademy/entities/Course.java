package com.example.grademy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "COURSES")
@Getter
@Setter
@NoArgsConstructor
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
    @JsonIgnore
    private Set<Teacher> teachers;

    @ManyToMany
    Set<User> users;

    @Transient
    private String strOfTeachers;

    public String getStrOfUsers() {
        return getUsers().stream()
                .map((User user) -> {
                    return String.format("%s %s", user.getFirstName(), user.getLastName());
                })
                .collect(Collectors.joining(", "));
    }

    public void setStrOfUsers(String strOfUsers) {
        this.strOfUsers = strOfUsers;
    }

    @Transient
    private String strOfUsers;

    public String getStrOfTeachers() {
        return getTeachers().stream()
                .map((Teacher teacher) -> {
                    return String.format("%s %s", teacher.getFirstName(), teacher.getLastName());
                })
                .collect(Collectors.joining(", "));
    }

    public void setStrOfTeachers(String setOfTeachers) {
        strOfTeachers = setOfTeachers;
    }
}
