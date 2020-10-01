package com.example.grademy.repositories;

import com.example.grademy.entities.Department;
import com.example.grademy.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("select distinct t.department from Teacher t")
    List<Department> getDistinctDepartments();
}
