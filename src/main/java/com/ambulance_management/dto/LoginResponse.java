package com.ambulance_management.dto;

import com.ambulance_management.entity.Role;

public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;

    public LoginResponse(Long id, String name, String email,
                          String phone, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }
}
