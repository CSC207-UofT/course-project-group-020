package PasswordManagerProgram;

import Account.Account;
import Account.AccountManager;
import CommandLine.UIMain;
import Entities.LogIn;
import Entities.PrivateInfoManager;

import java.io.IOException;

/**
 * This class is responsible for running the program.
 *
 * It creates and holds a single AccountManager instance ... TODO: finish this javadoc.
 */
public class PasswordManagerProgram {
//    private static AccountManager accountManager = new AccountManager();
    private Account activeAccount;
    
    public PasswordManagerProgram(){

    }

    public static void main(String[] args) throws IOException {
        AccountManager accountManager = new AccountManager();
        UIMain uiMain = new UIMain();

        uiMain.welcome();  // welcome the user to the program

        // Create new account or log in to existing prompt
        String userSelection =  uiMain.accountPrompt();
        // This is the account the user has logged in to
        Account activeAccount = getActiveAccount(accountManager, uiMain, userSelection);

        PrivateInfoManager vault =  activeAccount.getVault();
        userSelection = uiMain.mainMenuPrompt();

        // TODO: user selects option from main menu
            // only implement 1. tell user 2-4 is in beta. 5 is logout.

        // TODO: user selects option from submenu
            // 1 = addLogin, 2 = editLogin, 3 = deleteLogin

        // TODO: execute the appropriate method according to the user's submenu selection
            // at this point, vault should be updated with the new PrivateInfo object
            // ensure that the console outputs confirmation of completed action.

        // TODO: return to the main menu
            // that means the first 3 TODOs are in a while loop

        // TODO: finally, merge code with encryption changes

        // TODO: Create tests

    }

    private static Account getActiveAccount(AccountManager accountManager, UIMain uiMain, String userSelection)
            throws IOException {
        // Creating a new account
        if (userSelection.equals("1")){
            String[] accountCredentials = uiMain.getAccountCredentials();
            return accountManager.createAccount(accountCredentials[0], accountCredentials[1]);
        }
        // Logging in to an existing account
        else{
            assert userSelection.equals("2");
            String[] accountCredentials = uiMain.getAccountCredentials();
            return accountManager.attemptLogIn(accountCredentials[0], accountCredentials[1]);
        }
    }

}
