package Spring;


import Account.AccountManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AccountController myBean(){
        AccountManager acc = new AccountManager();
        acc.createAccount("Ryan", "Zhao");

        AccountController accController = new AccountController(acc);

        return accController;
    }
}
