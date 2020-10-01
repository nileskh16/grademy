package com.example.grademy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TEACHER")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPT_ID")
    @JsonIgnore
    private Department department;

    @Transient
    private String departmentName;

    public String getDepartmentName() {
        return getDepartment().getName();
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
