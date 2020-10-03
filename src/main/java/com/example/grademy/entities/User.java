package com.example.grademy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", length = 1001, nullable = false)
    private String password;

    @Column(name = "USER_ROLE")
    private String role;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PROFILE_ID")
    @JsonIgnore
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_COURSES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    @JsonIgnore
    private Set<Course> courses;

    @Transient
    private String userEmail;

    @Transient
    private String userAvatar;

    public String getUserEmail() {
        if (getUserProfile() != null) {
            return getUserProfile().getEmail();
        } else return "NA";
    }

    public String getUserAvatar() {
        return getUserProfile() != null ? getUserProfile().getAvatar() : "NA";
    }
}
