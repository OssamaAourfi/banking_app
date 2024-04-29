package com.ossama.bankingapp.service;

import com.ossama.bankingapp.entity.Account;
import com.ossama.bankingapp.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }
    public Account deposit(Long id, Double amount) {
        Account account = getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()+amount);
        return accountRepository.save(account);
    }
    public Account withdraw(Long id, Double amount) {
        Account account = getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance()-amount);
        return accountRepository.save(account);
    }

}
