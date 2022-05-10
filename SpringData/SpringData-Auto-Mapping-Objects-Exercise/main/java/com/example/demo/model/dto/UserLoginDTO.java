package com.example.demo.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserLoginDTO {
    @Email
    private String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}",message = "Enter valid password")
    private String password;

    public UserLoginDTO(){};

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
