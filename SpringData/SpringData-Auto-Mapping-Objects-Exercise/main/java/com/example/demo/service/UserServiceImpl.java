package com.example.demo.service;

import com.example.demo.model.dto.UserLoginDTO;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private  User loggedIn;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Wrong password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = this.validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        User user = this.modelMapper.map(userRegisterDto,User.class);

        this.userRepository.save(user);

    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        Set<ConstraintViolation<UserLoginDTO>> violation = this.validationUtil.violation(userLoginDTO);

        if(!violation.isEmpty()){
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = this.userRepository
                .findByEmailAndPassword(userLoginDTO.getEmail(),userLoginDTO.getPassword())
                .orElse(null);

        if(user == null){
            System.out.println("Incorrect email or password");
            return;
        }

        this.loggedIn = user;
    }

    @Override
    public void logout() {
        if(this.loggedIn == null){
            System.out.println("User is not logged in");
            return;
        }

        this.loggedIn = null;
    }
}
