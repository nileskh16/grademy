package com.example.grademy.repositories;

import com.example.grademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserNameContaining(String username);
    User findByUserName(String userName);
}
