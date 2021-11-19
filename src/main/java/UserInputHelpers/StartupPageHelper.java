package UserInputHelpers;
import Account.Account;
import Account.AccountManager;
import Entities.PrivateInfoManager;

import java.io.IOException;

public class StartupPageHelper {

    public StartupPageHelper(){
    }

    /**
     * A method that is a helper function to LogIn a user on the startup page. Returns the account private info
     * if logged in successfully
     * @param username
     * @param masterPassword
     * @return Returns true if Login is valid and false if otherwise
     */
    public Object LoginHelper(String username, String masterPassword, AccountManager accounts) {

        for (PrivateInfoManager account: accounts.getAccounts()){

            if (username.equals(account.getAccount().getUsername()) && masterPassword.equals(account.getAccount().getMasterPassword())){
                return account;
            }
        }

        return false;
    }

    public boolean CreateNewAccountHelper(String username, String password, AccountManager accounts){

        accounts.createAccount(username, password);
        return true;

    }

}
