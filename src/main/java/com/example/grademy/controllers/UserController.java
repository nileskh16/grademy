package com.example.grademy.controllers;

import com.example.grademy.dao.UserDao;
import com.example.grademy.entities.Course;
import com.example.grademy.entities.User;
import com.example.grademy.entities.UserProfile;
import com.example.grademy.repositories.CourseRepository;
import com.example.grademy.repositories.UserRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody UserDao userData) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userData.getEmailId());
        userProfile.setBioName(userData.getBioName());
        userProfile.setAvatar(userData.getAvatar());

        User freshUser = new User();
        freshUser.setFirstName(userData.getFirstName());
        freshUser.setLastName(userData.getLastName());
        freshUser.setUserName(userData.getUserName());
        freshUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        freshUser.setRole(userData.getRole().toUpperCase());
        freshUser.setActive(true);
        freshUser.setUserProfile(userProfile);
        userProfile.setUser(freshUser);
        return userRepository.save(freshUser);
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public User updateCourse(@RequestBody Object bodyPayload) {
        JsonObject payload = (JsonObject) bodyPayload;
        Integer userId = payload.get("userId").getAsInt();
        Integer courseId = payload.get("courseId").getAsInt();
        Course savedCourse = courseRepository.getOne(courseId);
        User savedUser = userRepository.getOne(userId);
        savedUser.getCourses().add(savedCourse);
        return userRepository.save(savedUser);
    }
}
