package com.example.springdemo.services;

import com.example.springdemo.exceptions.UserNotFoundException;
import com.example.springdemo.exceptions.UsernameAlreadyExists;

import java.math.BigDecimal;

public interface UserService {

    void register(String username,
                  int age ,
                  BigDecimal initialAmount)
            throws UsernameAlreadyExists;


    void addAccount(BigDecimal amount,Long id) throws UserNotFoundException;

}
