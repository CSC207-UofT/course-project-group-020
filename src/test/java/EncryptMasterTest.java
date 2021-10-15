/*
 * This file contains JUnit test cases for EncryptMaster.java/
 */

import Account.Account;
import Account.AccountManager;
import Encryption.EncryptMaster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a class responsible for testing the encryption of our Password Manager program.
 */
public class EncryptMasterTest {
    String myMasterPassword = "Ilikedogsalot";
    AccountManager accountManager = new AccountManager();


    @Before
    public void setUp() {
        accountManager.createAccount("hayknazaryan", myMasterPassword);
    }

    /**
     * This is a test for ensuring that the master password stored in our program is encrypted, for the sake
     * of the user's privacy!
     */
    @Test
    public void testIfEncryptedAndStoredMasterPasswordsMatch() {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(myMasterPassword);
        String storedMasterPassword = accountManager.getAccounts().get(0).getMasterPassword();

        assertEquals(encryptedMasterPassword, storedMasterPassword);
    }

    /**
     * This is a test to ensure that our encryption is functional and encrypts a given master password accordingly.
     */
    @Test
    public void testEncryption() {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(myMasterPassword);

        assert (!myMasterPassword.equals(encryptedMasterPassword));

    }

}
