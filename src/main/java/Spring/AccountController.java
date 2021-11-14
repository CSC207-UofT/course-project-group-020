package Spring;
import Account.Account;
import Account.AccountManager;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private AccountManager accounts;

    public AccountController(){
        accounts = new AccountManager();
    }

    public AccountController(AccountManager acc) {
        accounts = acc;
    }

    @GetMapping("/accounts")
    ArrayList<Account> all() {
        return accounts.getAccounts();
    }

    @GetMapping("/accounts/{username}")
    Account findUsername(@PathVariable String username) {
        return accounts.getAccountByUsername(username);
    }





}
