package Account;

import Encryption.EncryptMaster;

import java.util.ArrayList;

/**
 * This class keeps track of all the Password Manager Accounts. It does so in the ArrayList called
 * accounts.
 * <p>
 * It is also responsible for creating new Accounts, deleting Accounts, and logging in to the vault
 * associated with the Account.
 * <p>
 * It has an instance attribute called accounts that is an ArrayList that
 */
public class AccountManager {
    private ArrayList<Account> accounts;

    public AccountManager() {
        accounts = new ArrayList<Account>();
    }

    /**
     * @param username       The username associated with this Account
     * @param masterPassword The master password associated with this Account
     */

    public Account createAccount(String username, String masterPassword) {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(masterPassword);
        Account account = new Account(username, encryptedMasterPassword);
        this.accounts.add(account);
        return account;
    }

    public void deleteAccount() {
        // TODO: delete Account from accounts ArrayList

    }

    public Account attemptLogIn(String username, String masterPassword) {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(masterPassword);
        // Search for an Account with these credentials within accounts.
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getMasterPassword().equals(encryptedMasterPassword)) {
                return account;
            }
        }
        // If no account was found
        System.out.println("Sorry, we could not find an account with those credentials.");
        return null;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    } // Change this because we cannot have getters and setters
    // within use cases.
}
