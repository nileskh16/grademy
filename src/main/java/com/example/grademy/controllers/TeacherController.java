package com.example.grademy.controllers;

import com.example.grademy.dao.TeacherDao;
import com.example.grademy.entities.Department;
import com.example.grademy.entities.Teacher;
import com.example.grademy.repositories.DepartmentRepository;
import com.example.grademy.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Teacher addTeacher(@RequestBody TeacherDao teacherData) {
        Teacher tempTeacher = new Teacher();
        tempTeacher.setActive(true);
        tempTeacher.setFirstName(teacherData.getFirstName());
        tempTeacher.setLastName(teacherData.getLastName());
        Department department = departmentRepository.findById(Integer.parseInt(teacherData.getDepartmentId())).orElse(null);
        if (department != null) {
            tempTeacher.setDepartment(department);
        }
        return teacherRepository.save(tempTeacher);
    }
}
