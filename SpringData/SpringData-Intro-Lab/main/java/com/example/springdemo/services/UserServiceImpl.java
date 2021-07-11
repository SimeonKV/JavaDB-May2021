package com.example.springdemo.services;

import com.example.springdemo.exceptions.UserNotFoundException;
import com.example.springdemo.exceptions.UsernameAlreadyExists;
import com.example.springdemo.models.Account;
import com.example.springdemo.models.User;
import com.example.springdemo.repositories.AccountRepository;
import com.example.springdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String username,int age ,BigDecimal initialAmount) throws UsernameAlreadyExists {
        if(this.userRepository.existsByUsername(username)){
            throw new UsernameAlreadyExists();
        }

        User user = new User();
        user.setUsername(username);
        user.setAge(age);

        this.userRepository.save(user);

        Account firstAccount = new Account();
        firstAccount.setBalance(initialAmount);

        firstAccount.setUser(user);

        this.accountRepository.save(firstAccount);
    }

    @Override
    public void addAccount(BigDecimal amount, Long id) throws UserNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException());

        Account account = new Account();
        account.setBalance(amount);
        account.setUser(user);

        this.accountRepository.save(account);

    }
}
