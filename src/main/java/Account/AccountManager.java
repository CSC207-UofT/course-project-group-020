package Account;

import Encryption.MasterEncryption;
import Entities.PrivateInfo;
import Entities.PrivateInfoManager;

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
    public ArrayList<Account> accounts;
    public Throwable NullPointerException;

    public AccountManager() {
        accounts = new ArrayList<>();
    }

    /**
     * @param username       The username associated with this Account
     * @param masterPassword The master password associated with this Account
     */

    public void createAccount(String username, String masterPassword) {
        String encryptedMasterPassword = MasterEncryption.encryptMaster(masterPassword);
        Account account = new Account(username, encryptedMasterPassword);

        this.accounts.add(account);
    }


    /**
     * Removes the account from the system.
     *
     * @param accountToBeDeleted The account that is to be deleted from the system.
     */
    public void deleteAccount(PrivateInfoManager accountToBeDeleted) {
        this.accounts.remove(accountToBeDeleted);

    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    public Account getAccount(Account wantedAccount) {

        int i = 0;
        for (Account account : this.accounts) {
            if (account == wantedAccount) {
                break;
            }
            i += 1;
        }
        return this.accounts.get(i);


    }

    public Account getAccountByUsername(String usernameOfWantedAccount) throws Throwable{
        int i = 0;
        for (Account account : this.accounts) {

            if (account.getUsername().equals(usernameOfWantedAccount)) {
                return this.accounts.get(i);
            }
            i += 1;
        }
        throw NullPointerException;
    }
}

