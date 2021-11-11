import Account.Account;
import Account.AccountManager;
import Entities.LogIn;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of adding, deleting and editing PrivateInfo such as LogIns, Notes, IDs, and
 * Contacts from an individual account.
 */

public class PrivateInfoTest {


        String myMasterPassword = "Ilikedogsalot";
        AccountManager accountManager = new AccountManager();

        @Before
        public void setUp(){this.accountManager.createAccount("hayknazaryan", myMasterPassword);}

    @Test
    public void TestAddInfo(){

            LogIn NewLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");

            Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");

            accountManager.addInfo(NewLogIn, currentAccount);


            assert (currentAccount.getVault().getVault().size() > 0);
            assert (currentAccount.getVault().getPrivateInfo(NewLogIn).GetInfo("username").equals("hayknazaryan"));
            assert (currentAccount.getVault().getPrivateInfo(NewLogIn).GetInfo("password").equals("Idontlikecats"));
            assert (currentAccount.getVault().getPrivateInfo(NewLogIn).GetInfo("webpage").equals("instagram"));
            assert (currentAccount.getVault().getPrivateInfo(NewLogIn).GetInfo("url").equals("insta"));




    }



}
