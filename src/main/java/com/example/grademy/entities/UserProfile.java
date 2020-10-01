package com.example.grademy.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "BIO_NAME", length = 100)
    private String bioName;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "AVATAR", length = 1001)
    private String avatar;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
