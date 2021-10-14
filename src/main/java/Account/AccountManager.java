package Account;

import java.util.ArrayList;

/**
 * This class keeps track of all the Password Manager Accounts. It does so in the ArrayList called
 * accounts.
 *
 * It is also responsible for creating new Accounts, deleting Accounts, and logging in to the vault
 * associated with the Account.
 *
 * It has an instance attribute called accounts that is an ArrayList that
 */
public class AccountManager {
    private ArrayList<Account> accounts;

    public AccountManager(){
        accounts = new ArrayList<Account>();
    }

    /**
     *
     * @param username          The username associated with this Account
     * @param masterPassword    The master password associated with this Account
     */

    // TODO: pass in the encryptedMasterPassword instead.
    public Account createAccount(String username, String masterPassword){
        // encryptedMasterPassword = ...
        Account account = new Account(username, masterPassword);
        this.accounts.add(account);
        return account;
    }

    public void deleteAccount(){
        // TODO: delete Account from accounts ArrayList

    }

    // TODO: pass in the encryptedMasterPassword instead.
    public Account attemptLogIn(String username, String masterPassword){
        // encryptedMasterPassword = ...

        // Search for an Account with these credentials within accounts.
        for (Account account : accounts){
            if (account.getUsername().equals(username) && account.getMasterPassword().equals(masterPassword)){
                return account;
            }
        }
        // If no account was found
        System.out.println("Sorry, we could not find an account with those credentials.");
        return null;
    }


}