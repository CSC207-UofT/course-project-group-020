package PasswordManagerProgram;

import Account.Account;
import Account.AccountManager;
import CommandLine.UIMain;
import Encryption.EncryptMaster;
import Encryption.EncryptPrivInfo;
import Entities.LogIn;
import Entities.PrivateInfo;
import Entities.PrivateInfoManager;

import java.io.IOException;
import java.util.ArrayList;

// TODO: Update README.md


/**
 * This class is responsible for running the program.
 */
public class PasswordManagerProgram {

    public PasswordManagerProgram() {

    }

    public static void main(String[] args) throws IOException {
        AccountManager accountManager = new AccountManager();
        UIMain uiMain = new UIMain();

        uiMain.welcome();  // welcome the user to the program

        // This is the account the user has logged in to
        Account activeAccount = getActiveAccount(accountManager, uiMain);
        // This is the vault associated with the active account.
        PrivateInfoManager vault = activeAccount.getVault();

        while (true) {
            String requestedAction = getRequestedAction(uiMain);
            executeAction(uiMain, requestedAction, vault);
            if (requestedAction.equals("LOGOUT")) {
                break;
            }
            System.out.println();  // print a blank line
        }

        System.out.println("Goodbye!");

        // TODO: finally, merge code with encryption changes

        // TODO: Create tests

    }

    private static Account getActiveAccount(AccountManager accountManager, UIMain uiMain)
            throws IOException {
        // Determines whether to create new account or log in to existing prompt
        String userSelection = uiMain.accountPrompt();

        // Creating a new account
        if (userSelection.equals("1")) {
            String[] accountCredentials = uiMain.getAccountCredentials();
            return accountManager.createAccount(accountCredentials[0], accountCredentials[1]);
        }
        // Logging in to an existing account
        else {
            assert userSelection.equals("2");
            String[] accountCredentials = uiMain.getAccountCredentials();
            return accountManager.attemptLogIn(accountCredentials[0], accountCredentials[1]);
        }
    }

    private static String getRequestedAction(UIMain uiMain) throws IOException {
        String userMainMenuSelection = uiMain.mainMenuPrompt();
        switch (userMainMenuSelection) {
            case "1":
                return uiMain.subMenuPrompt(userMainMenuSelection);
            case "5":
                return "printVault";
            case "6":
                return "LOGOUT";


            // User chooses an 2, 3, 4, or 5
            default:
                return "betaVersion";
        }
    }

    private static void executeAction(UIMain uiMain, String requestedAction, PrivateInfoManager vault)
            throws IOException {
        switch (requestedAction) {
            case "printVault":

                ArrayList<PrivateInfo> vaultCopy = vault.getCopy();

                for (PrivateInfo privateInfo : vaultCopy) {

                    if (privateInfo instanceof LogIn) {
                        privateInfo.ChangeInfo("password", EncryptPrivInfo.decryptInfo(uiMain.getKey(),
                                privateInfo.GetInfo("password")));
                    }

                }

                System.out.println(vaultCopy);
                break;
            case "betaVersion":
                // Java warns that this is a duplicate of the default branch, but we will be providing a different
                // implementation of this for our final program (non-beta).
                System.out.println("Sorry, that feature has not been implemented in the beta.");
                break;
            case "1":
                // prompt user for login info
                LogIn logIn = uiMain.createLogIn();
                // create login
                vault.addInfo(logIn);
                break;
            case "2":
                // TODO: implement edit login
                // Java warns that this is a duplicate of the default branch, but we will be providing a different
                // implementation of this for our final program (non-beta).
                System.out.println("Sorry, that feature has not been implemented in the beta.");
                break;
            default:
                // TODO: implement delete login
                System.out.println("Sorry, that feature has not been implemented in the beta.");
                break;
        }
    }

}
