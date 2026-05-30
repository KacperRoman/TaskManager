package com.github.kacperroman.taskmanager.repository;

import com.github.kacperroman.taskmanager.model.Task;
import com.github.kacperroman.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user);
}
