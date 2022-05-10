package com.example.demo.service;

import com.example.demo.model.dto.UserLoginDTO;
import com.example.demo.model.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDTO userLoginDTO);

    void logout();
}
