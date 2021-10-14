package CommandLine;

import java.util.Scanner;

public class UICreateAccount{

    // Scanner objects
    Scanner username;
    Scanner password;

    // Storage
    String storedUser = "";
    String storedPass = "";

    public UICreateAccount(Scanner username, Scanner password){

        this.username = username;
        this.password = password;

    }

    // Runs the Create Account Section
    public void runCreateAccount(){
        System.out.println("Welcome to our Password Manager BETA!");
        System.out.println("Please create an account");

        try{

            System.out.println("Enter your new username: ");
            String collectedUsername = this.username.nextLine();

            System.out.println("Enter your new password: ");
            String collectedPassword = this.password.nextLine();

            this.storedUser = collectedUsername;
            this.storedPass = collectedPassword;

            System.out.println("Success!");
        }

        finally {
            if (this.username != null && this.password != null){
                this.username.close();
                this.password.close();
            }
        }

    }

}
