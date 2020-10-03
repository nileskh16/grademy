package com.example.grademy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DEPT_NAME", length = 100)
    private String name;

    @Column(name = "ADDRESS", length = 1001)
    private String address;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Teacher> teachers;
}
