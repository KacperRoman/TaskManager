package com.github.kacperroman.taskmanager.model;

import jakarta.annotation.Nullable;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Column(unique = true)
    private String username;
    @Setter
    private String password;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User( int id, String username, String password,String email){
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
