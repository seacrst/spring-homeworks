package seacrest.app.account;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("all")
    public List<Account> getAll() {

        return accountService.getAll();

    }

}
