package com.github.kacperroman.taskmanager.repository;

import com.github.kacperroman.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
