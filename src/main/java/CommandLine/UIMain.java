package CommandLine;
import Entities.LogIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class UIMain {

    public void welcome(){
        System.out.println("Welcome to the Password Manager Beta!");
    }

    /**
     * This method prompts the user to either create a new Account or log in to an existing Account.
     *
     * @return              A String representing the user's selection
     * @throws IOException
     */
    public String accountPrompt() throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Would you like to create a new Password Manager account or log in to an existing one?");
        System.out.println("1) Create new Password Manager account \n " +
                           "2) Log in to an existing account");

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

    /**
     * This method prompts the user to select what component of their vault they want to manage.
     * Or, the user can choose to log out if they do not want to manage their vault items.
     *
     * @return              A String representing the user's selection
     * @throws IOException
     */
    public String mainMenuPrompt() throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("What would you like to manage in your vault?");
        System.out.println("Please select a number from 1 to 6");
        System.out.println("1) LogIns \n 2) Notes \n 3) Contacts \n 4) IDs \n 5) Display vault \n 6) No thanks, I would like to logout");

        // Read and return the user's selected option
        return reader.readLine();
    }

    /**
     * This method prompts the user to select what action they would like to perform with the chosen vault component.
     *
     * @return              A String representing the user's selection
     * @throws IOException
     */
    public String subMenuPrompt(String mainMenuSelection) throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // This HashMap maps each possible mainMenuSelection to its vault component
        HashMap<String, String> vaultComponents = new HashMap<String, String>();
        vaultComponents.put("1", "LogIn");
        vaultComponents.put("2", "Note");
        vaultComponents.put("3", "Contact");
        vaultComponents.put("4", "ID");


        System.out.println("What action would you like to perform?");
        System.out.println("Please select a number from 1 to 3.");
        System.out.println("1) Add " + vaultComponents.get(mainMenuSelection) +
                "\n 2) Edit " + vaultComponents.get(mainMenuSelection) +
                "\n 3) Delete " + vaultComponents.get(mainMenuSelection));

        // Read and return the user's selected option
        return reader.readLine();
    }

    /**
     * This method prompts the user to enter the details of the LogIn the want to add.
     *
     * @return              A LogIn object with the values given by the user
     * @throws IOException
     */
    public LogIn createLogIn() throws IOException {
        // Create a BufferedReader object to read the user's inputs
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] logInInfo = new String[4];

        System.out.println("Please enter the username for this LogIn");
        logInInfo[0] = reader.readLine();

        // TODO: encrypt this password
        System.out.println("Please enter the password for this LogIn");
        logInInfo[1] = reader.readLine();
        System.out.println("Please enter the webpage associated with this LogIn");
        logInInfo[2] = reader.readLine();
        System.out.println("Please enter the url of the webpage associated with this LogIn");
        logInInfo[3] = reader.readLine();

        return new LogIn(logInInfo[0], logInInfo[1], logInInfo[2], logInInfo[3]);
    }
}
