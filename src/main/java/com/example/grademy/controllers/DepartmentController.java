package com.example.grademy.controllers;

import com.example.grademy.dao.DepartmentDao;
import com.example.grademy.entities.Department;
import com.example.grademy.entities.Teacher;
import com.example.grademy.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @RequestMapping(value = "/get/{id}")
    public Department getDepartmentByName(@PathVariable String id) {
        return departmentRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Department addDepartment(@RequestBody DepartmentDao deptData) {
        Department requestPayload = new Department();
        requestPayload.setName(deptData.getName());
        requestPayload.setAddress(deptData.getAddress());
        requestPayload.setTeachers(new HashSet<>());
        requestPayload = departmentRepository.save(requestPayload);
        return requestPayload;
    }

    @RequestMapping(value = "/get/teachers/{id}", method = RequestMethod.GET)
    public Set<Teacher> getAllTeachersFromDeptById(@PathVariable String id) {
        Department savedData = departmentRepository.findById(Integer.parseInt(id)).orElse(null);
        return savedData != null ? savedData.getTeachers() : null;
    }
}
