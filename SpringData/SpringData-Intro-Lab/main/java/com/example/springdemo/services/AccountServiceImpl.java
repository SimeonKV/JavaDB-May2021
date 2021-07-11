package com.example.springdemo.services;

import com.example.springdemo.exceptions.InsufficientFundsException;
import com.example.springdemo.models.Account;
import com.example.springdemo.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void transferBetweenAccounts(Long fromId, Long toId, BigDecimal amount) throws InsufficientFundsException {

        this.withdrawMoney(amount,fromId);
        this.transferMoney(amount,toId);



    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) throws InsufficientFundsException {
        Account account = this.accountRepository.findById(id)
                .orElseThrow();

        this.throwIfInsufficientFunds(account,amount);

        account.setBalance(account.getBalance().subtract(amount));
        this.accountRepository.save(account);

    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow();

        account.setBalance(account.getBalance().add(amount));
        this.accountRepository.save(account);


    }


    private void throwIfInsufficientFunds(Account account,BigDecimal amount) throws InsufficientFundsException{
        if(account.getBalance().compareTo(amount) < 0){
            throw new InsufficientFundsException();
        }

    }
}
