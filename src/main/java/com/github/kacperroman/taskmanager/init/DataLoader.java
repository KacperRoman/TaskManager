package com.github.kacperroman.taskmanager.init;

import com.github.kacperroman.taskmanager.model.Role;
import com.github.kacperroman.taskmanager.model.User;
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

    @Autowired
    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("admin",passwordEncoder.encode("admin123"),"abc@gmail.com", Role.ADMIN);
        //userRepository.save(admin);

        User user = new User("user",passwordEncoder.encode("user123"),"user123@a.com",Role.USER);
        userRepository.saveAll(List.of(user,admin));
    }
}
