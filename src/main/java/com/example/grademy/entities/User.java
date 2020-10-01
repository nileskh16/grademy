package com.example.grademy.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "USERNAME", unique = true)
    private String userName;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "PROFILE_ID")
    private UserProfile userProfile;
}
