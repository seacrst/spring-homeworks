package seacrest.bank_app.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seacrest.bank_app.services.AccountService;

@RestController
@RequestMapping("api/account")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService service) {
        accountService = service;
    }

    @PostMapping("send")
    public void send() {

    }

    @PostMapping("deposit")
    public void deposit() {

    }

    @PostMapping("withdraw")
    public void withdraw() {

    }

}
