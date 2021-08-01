package com.json.lab.service;

import com.json.lab.model.dto.UserSoldDto;
import com.json.lab.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserSoldDto> findAllUsersWithMoreThanOneSoldProduct();
}
