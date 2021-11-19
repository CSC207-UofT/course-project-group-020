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
    public ArrayList<PrivateInfoManager> accounts;
    private Throwable NullPointerException;

    public AccountManager() {
        accounts = new ArrayList<PrivateInfoManager>();
    }

    /**
     * @param username       The username associated with this Account
     * @param masterPassword The master password associated with this Account
     */

    public void createAccount(String username, String masterPassword) {
        String encryptedMasterPassword = EncryptMaster.encryptMaster(masterPassword);
        Account account = new Account(username, encryptedMasterPassword);
        PrivateInfoManager privInfoAccount = new PrivateInfoManager(account);
        this.accounts.add(privInfoAccount);

    }


    /**
     * Removes the account from the system.
     * @param accountToBeDeleted The account that is to be deleted from the system.
     */
    public void deleteAccount(PrivateInfoManager accountToBeDeleted) {
        this.accounts.remove(accountToBeDeleted);

    }


//    public Account attemptLogIn(String username, String masterPassword) {
//        String encryptedMasterPassword = EncryptMaster.encryptMaster(masterPassword);
//        // Search for an Account with these credentials within accounts.
//        for (Account account : accounts) {
//            if (account.getUsername().equals(username) && account.getMasterPassword().equals(encryptedMasterPassword)) {
//                return account;
//            }
//        }
//        // If no account was found
//        System.out.println("Sorry, we could not find an account with those credentials.");
//        return null;
//    }

    public ArrayList<PrivateInfoManager> getAccounts() {
        return this.accounts;
    }

    // Change this because we cannot have getters and setters
    // within use cases.

    public Account getAccount(PrivateInfoManager wantedAccount){

        int i = 0;
        for (PrivateInfoManager account: this.accounts){
            if (account == wantedAccount){
                break;
            }
            i += 1;
        }
        return this.accounts.get(i).getAccount();


    }

    public Account getAccountByUsername(String usernameOfWantedAccount) throws Throwable{
        int i = 0;
        for (PrivateInfoManager account: this.accounts){

            if (account.getAccount().getUsername().equals(usernameOfWantedAccount)){
                return this.accounts.get(i).getAccount();
            }
            i += 1;
        }
        throw NullPointerException; // TODO: Replace with an exception (also do this everywhere else)
    }

    public PrivateInfoManager getPrivateInfoManagerByUsername (String usernameOfWantedAccount) throws Throwable
    {
        int i = 0;
        for (PrivateInfoManager account: this.accounts){

            if (account.getAccount().getUsername().equals(usernameOfWantedAccount)){
                return this.accounts.get(i);
            }
            i += 1;
        }
        throw NullPointerException; // TODO: Replace with an exception (also do this everywhere else)
    }


    public PrivateInfoManager getPrivateInfoManager(PrivateInfoManager wantedPrivateInfoManager){

        int i = 0;
        for (PrivateInfoManager account: this.accounts){
            if (account == wantedPrivateInfoManager){
                break;
            }
            i += 1;
        }
        return this.accounts.get(i);

    }

    public void addInfo(PrivateInfo newInfo, Account accountToBeAddedTo){
        int i = 0;

        for (PrivateInfoManager account: this.accounts){
            if (account.getAccount() == accountToBeAddedTo){
                this.accounts.get(i).addInfo(newInfo);
            }
            i += 1;

        }


    }

    public void editInfo(PrivateInfo infoToBeChanged, Account accountToBeChangedIn,
                         String attributeToChange, String newValue){

        int i = 0;
        for (PrivateInfoManager account: this.accounts){
            if (account.getAccount() == accountToBeChangedIn){
                this.accounts.get(i).editInfo(infoToBeChanged, attributeToChange, newValue);
            }
            i += 1;
        }
    }

    public static void main(String[] args) {
        AccountManager acc = new AccountManager();
        acc.createAccount("Ryan", "Zhao");

    }
}
