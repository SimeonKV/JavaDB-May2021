package com.example.springdemo;

import com.example.springdemo.services.AccountService;
import com.example.springdemo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

     this.accountService.transferBetweenAccounts(3l,4l,new BigDecimal("500"));



    }
}
