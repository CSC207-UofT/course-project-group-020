/*
 * This file contains JUnit test cases for EncryptMaster.java/
 */

import Account.AccountManager;
import Encryption.MasterEncryption;
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
    public void testIfEncryptedAndStoredMasterPasswordsMatch() throws Throwable {
        String encryptedMasterPassword = MasterEncryption.encryptMaster(myMasterPassword);
        String storedMasterPassword = this.accountManager.getAccount("hayknazaryan").getMasterPassword();
        assertEquals(encryptedMasterPassword, storedMasterPassword);
    }

    /**
     * This is a test to ensure that our encryption is functional and encrypts a given master password accordingly.
     */
    @Test
    public void testEncryption() {
        String encryptedMasterPassword = MasterEncryption.encryptMaster(myMasterPassword);

        assert (!myMasterPassword.equals(encryptedMasterPassword));

    }

}
