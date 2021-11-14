package UserInputHelpers;


import Account.AccountManager;

/**
 * A parent class to store the general AccountManager instance for the child helper classes
 */
public class UserInputHelper {

    AccountManager accounts;


    public UserInputHelper(AccountManager accounts) {
        this.accounts = accounts;
    }
}
