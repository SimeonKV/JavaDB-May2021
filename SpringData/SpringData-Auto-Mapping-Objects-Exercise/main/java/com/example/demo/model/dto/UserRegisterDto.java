package com.example.demo.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    @Email(message = "Enter valid email")
    private String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}",message = "Enter valid password")
    private String password;
    private String confirmPassword;
    @Size(min = 2)
    private String fullName;

    public UserRegisterDto(){};

    public UserRegisterDto(@Email(message = "Enter valid email") String email,
                           @Pattern(regexp = "[A-Za-z\\d]{6,}", message = "Enter valid password")
                                   String password,
                           String confirmPassword, @Size(min = 2) String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
