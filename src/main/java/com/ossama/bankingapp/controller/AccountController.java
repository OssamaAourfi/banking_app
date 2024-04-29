package com.ossama.bankingapp.controller;

import com.ossama.bankingapp.entity.Account;
import com.ossama.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id).orElseThrow(()-> new RuntimeException("Account not found"));
    }
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id ,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return accountService.deposit(id,amount);
    }
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id ,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return accountService.withdraw(id,amount);
    }

}
