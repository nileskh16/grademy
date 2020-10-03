package com.example.grademy.controllers;

import com.example.grademy.dao.CourseDao;
import com.example.grademy.entities.Course;
import com.example.grademy.entities.Teacher;
import com.example.grademy.repositories.CourseRepository;
import com.example.grademy.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Course> getListOfCourses() {
        return courseRepository.findAll();
    }

    @RequestMapping(name = "/get/id", method = RequestMethod.GET)
    public Course getCourseById(@PathVariable String id) {
        return courseRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Course addCourse(@RequestBody CourseDao courseData) {
        Course freshCourse = new Course();
        freshCourse.setDeleted(false);
        freshCourse.setDescription(courseData.getDescription());
        freshCourse.setLanguage(courseData.getLanguage());
        freshCourse.setRatings(courseData.getRatings());
        freshCourse.setTitle(courseData.getName());
        List<String> teacherIds = courseData.getTeacherIds();
        Set<Teacher> courseTeachers = new HashSet<>();
        for (String teacherId : teacherIds) {
            Teacher teacher = teacherRepository.findById(Integer.parseInt(teacherId)).orElse(null);
            if (teacher != null) {
                courseTeachers.add(teacher);
            }
        }
        freshCourse.setTeachers(courseTeachers);
        return courseRepository.save(freshCourse);
    }
}
