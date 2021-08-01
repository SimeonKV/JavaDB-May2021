package com.json.lab.service.impl;

import com.google.gson.Gson;
import com.json.lab.constants.GlobalConstants;
import com.json.lab.model.dto.UserSeedDto;
import com.json.lab.model.dto.UserSoldDto;
import com.json.lab.model.entity.User;
import com.json.lab.repository.UserRepository;
import com.json.lab.service.UserService;
import com.json.lab.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {
 private static final String USERS_FILE = "users.json";
 private final Gson gson;
 private final ValidationUtil validationUtil;
 private final ModelMapper modelMapper;
 private final UserRepository userRepository;

    public UserServiceImpl(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsers() throws IOException {
      if(this.userRepository.count() > 0){
          return;
      }

        String usersJson
                = Files.readString(Path.of(GlobalConstants.RESOURCES_FILE_PATH +  USERS_FILE));

        UserSeedDto[] userSeedDtos = gson.
                fromJson(usersJson,UserSeedDto[].class);


        Arrays.stream(userSeedDtos)
                .filter(userEntityToBe -> validationUtil.isValid(userEntityToBe))
                .map(userEntityToBe -> modelMapper.map(userEntityToBe, User.class))
                .forEach(this.userRepository::save);


    }

    @Override
    public User findRandomUser() {
        long randomId = ThreadLocalRandom
                .current()
                .nextLong(1,this.userRepository.count() + 1);

        return this.userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<UserSoldDto> findAllUsersWithMoreThanOneSoldProduct() {
        return this.userRepository.findAllUsersWithMoreThanOneSoldProducts()
                .stream()
                .map(entity ->
                        modelMapper.map(entity,UserSoldDto.class))
                .collect(Collectors.toList());
    }
}
