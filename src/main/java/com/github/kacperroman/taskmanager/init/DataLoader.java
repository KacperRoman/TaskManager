package com.github.kacperroman.taskmanager.init;

import com.github.kacperroman.taskmanager.model.Role;
import com.github.kacperroman.taskmanager.model.Task;
import com.github.kacperroman.taskmanager.model.User;
import com.github.kacperroman.taskmanager.repository.TaskRepository;
import com.github.kacperroman.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, TaskRepository taskRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("admin",passwordEncoder.encode("admin123"),"abc@gmail.com", Role.ADMIN);
        //userRepository.save(admin);

        User user = new User("user",passwordEncoder.encode("user123"),"user123@a.com",Role.USER);
        userRepository.saveAll(List.of(user,admin));

        Task adminTask1 = new Task("Admin Task 1", "This is the first task for admin", admin);
        Task adminTask2 = new Task("Admin Task 2", "This is the second task for admin", admin);
        Task adminTask3 = new Task("Admin Task 3", "This is the third task for admin", admin);
        Task adminTask4 = new Task("Admin Task 4", "This is the fourth task for admin", admin);

        Task userTask1 = new Task("User Task 1", "This is the first task for user", user);
        Task userTask2 = new Task("User Task 2", "This is the second task for user", user);
        Task userTask3 = new Task("User Task 3", "This is the third task for user", user);
        Task userTask4 = new Task("User Task 4", "This is the fourth task for user", user);

        taskRepository.saveAll(List.of(adminTask1,adminTask2,adminTask3,adminTask4,userTask1,userTask2,userTask3,userTask4));
    }
}
