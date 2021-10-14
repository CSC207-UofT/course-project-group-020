package CommandLine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIMain {

    public void welcome(){
        System.out.println("Welcome to the Password Manager Beta!");
    }

    public String accountPrompt() throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Would you like to create a new Password Manager account or log in to an existing one?");
        System.out.println("1) Create new Password Manager account \n " +
                           "2) Log in to an existing account");

        // Read and return the user's selected option
        return reader.readLine();
    }

    public String mainMenuPrompt() throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("What would you like to manage in your vault?");
        System.out.println("Please select a number from 1 to 5");
        System.out.println("1) LogIns \n 2) Notes \n 3) Contacts \n 4) IDs \n 5) I would like to Logout");

        // Read and return the user's selected option
        return reader.readLine();
    }

    /**
     * This method prompts the user to enter their account details.
     * It is used both when creating a new account or logging in to an existing account.
     *
     * @return An array containing the account username and master password.
     * @throws IOException
     */
    public String[] getAccountCredentials() throws  IOException {
        String[] accountCredentials = new String[2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the username for your account:");
        String username = reader.readLine();
        accountCredentials[0] = username;

        System.out.println("Enter the master password for your account:");
        String masterPassword = reader.readLine();
        accountCredentials[1] = masterPassword;

        return accountCredentials;
    }


}

