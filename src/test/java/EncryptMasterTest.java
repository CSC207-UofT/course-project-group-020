/*
 * This file contains JUnit test cases for EncryptMaster.java/
 */

import Account.Account;
import Account.AccountManager;
import Encryption.EncryptMaster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptMasterTest {
    String myMasterPassword = "Ilikedogsalot";
    AccountManager accountManager = new AccountManager();


    @Before
    public void setUp() {
        accountManager.createAccount("hayknazaryan", myMasterPassword);
    }


    @Test
    public void testIfEncryptedAndStoredMasterPasswordsMatch() {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(myMasterPassword);
        String storedMasterPassword = accountManager.getAccounts().get(0).getMasterPassword();

        assertEquals(encryptedMasterPassword, storedMasterPassword);
    }

    @Test
    public void testEncryption() {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(myMasterPassword);

        assert (!myMasterPassword.equals(encryptedMasterPassword));

    }

}
