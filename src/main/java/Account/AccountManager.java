package Account;

import Encryption.EncryptMaster;
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


    /**
     * Removes the account from the system.
     * @param accountToBeDeleted The account that is to be deleted from the system.
     */
    public void deleteAccount(Account accountToBeDeleted) {
        this.accounts.remove(accountToBeDeleted);

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
        System.out.println("The username or password is incorrect.");
        return null;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    } // Change this because we cannot have getters and setters
    // within use cases.

    public Account getAccount(Account wantedAccount){

        int i = 0;
        for (Account account: this.accounts){
            if (account == wantedAccount){
                break;
            }
            i += 1;
        }
        return this.accounts.get(i);


    }

    public Account getAccountByUsername(String usernameOfWantedAccount){
        int i = 0;
        for (Account account: this.accounts){

            if (account.getUsername().equals(usernameOfWantedAccount)){
                break;
            }
            i += 1;
        }

        return this.accounts.get(i);

    }


    public PrivateInfoManager getPrivateInfoManager(Account wantedAccount){

        Account currentAccount = getAccount(wantedAccount);
        return currentAccount.getVault();

    }

    public void addInfo(PrivateInfo newInfo, Account accountToBeAddedTo){

        Account currentAccount = getAccount(accountToBeAddedTo);

        currentAccount.getVault().addInfo(newInfo);


    }

    public void editInfo(PrivateInfo infoToBeChanged, Account accountToBeChangedIn,
                         String attributeToChange, String newValue){

        PrivateInfoManager currentPrivateInfoManager = getPrivateInfoManager(accountToBeChangedIn);

        currentPrivateInfoManager.editInfo(infoToBeChanged, attributeToChange, newValue);

    }
}
