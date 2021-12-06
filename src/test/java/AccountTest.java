import Account.Account;
import Account.AccountManager;
import PrivateInfoObjects.*;
import java.util.ArrayList;

import org.apache.juli.logging.Log;
import org.junit.Before;
import org.junit.Test;


/**
 * This class tests the functionality of the methods contained in the Account.java class.
 */

public class AccountTest {
    Account loneAccount = new Account("username123", "masterPassword123");
    AccountManager accountManager = new AccountManager();
    PrivateInfo logIn = createLogin();

    @Before
    public void setUp(){
        createAccount();
        populateVault(logIn);
    }

    public void createAccount(){
        accountManager.createAccount("username", "masterPassword");
    }

    public PrivateInfo createLogin(){
        return new LogIn("yousuf", "password123", "google", "google.com");
    }


    public void populateVault(PrivateInfo logIn) {
        accountManager.addInfo(logIn, "username");
    }


    @Test
    public void testGetUsername(){
        Account account = accountManager.getAccount("username");
        assert (account.getUsername().equals("username"));
    }

    @Test
    public void testGetMasterPassword(){
        assert (loneAccount.getMasterPassword().equals("masterPassword123"));
    }

    @Test
    public void testGetVaultEmptyVault(){
        ArrayList<PrivateInfo> expectedVault = new ArrayList<>();

        assert (expectedVault.equals(loneAccount.getVault()));
    }

    @Test
    public void testGetVaultWithItem(){
        Account account = accountManager.getAccount("username");
        ArrayList<PrivateInfo> expectedVault = new ArrayList<>();
        expectedVault.add(logIn);

        assert (expectedVault.toString().equals(account.getVault().toString()));
    }

    @Test
    public void testGetPrivateInfo() throws Throwable{
        Account account = accountManager.getAccount("username");
        PrivateInfo expectedLogIn = logIn;
        String logInID = account.getVault().get(0).id;

        assert (expectedLogIn.toString().equals(account.getPrivateInfo(logInID).toString()));
    }

}
