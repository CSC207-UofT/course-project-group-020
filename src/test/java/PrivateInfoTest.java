import Account.Account;
import Account.AccountManager;
import Entities.LogIn;
import Entities.PrivateInfoManager;
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
        public void TestAddInfo() throws Throwable {
                LogIn NewLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");
                PrivateInfoManager currentPrivateInfoManager = accountManager.getPrivateInfoManagerByUsername("hayknazaryan");

                Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");

                accountManager.addInfo(NewLogIn, currentAccount);


                assert (currentPrivateInfoManager.getVault().size() > 0);
                assert (currentPrivateInfoManager.getPrivateInfo(NewLogIn).GetInfo("username").equals("hayknazaryan"));
                assert (currentPrivateInfoManager.getPrivateInfo(NewLogIn).GetInfo("password").equals("Idontlikecats"));
                assert (currentPrivateInfoManager.getPrivateInfo(NewLogIn).GetInfo("webpage").equals("instagram"));
                assert (currentPrivateInfoManager.getPrivateInfo(NewLogIn).GetInfo("url").equals("insta"));




    }



}
