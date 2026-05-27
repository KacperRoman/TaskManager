package com.github.kacperroman.taskmanager.service;

import com.github.kacperroman.taskmanager.model.User;
import com.github.kacperroman.taskmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<User> userList =  userRepository.findByUsername(username);
//            if(userList.isEmpty()){
//               throw new UsernameNotFoundException("User not found");
//            }
//            return userList.get(0);
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        return userOptional.orElseThrow(()->{
//            throw new UsernameNotFoundException("User not found");
//        });

        return userRepository.findByUsername(username).orElseThrow(()->{
            throw new UsernameNotFoundException("User not found");
        });


    }
}
