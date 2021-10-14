package CommandLine;
import java.util.Scanner;

public class UIMain {

    public static void main(String[] args) {

        Scanner username = null;
        Scanner password = null;

        username = new Scanner(System.in);
        password = new Scanner(System.in);

        UICreateAccount createUI = new UICreateAccount(username, password);
        createUI.runCreateAccount();
//
        Scanner userInput = null;
        Scanner userInputSub = null;
        Scanner userExit = null;

        userInput = new Scanner(System.in);
        userInputSub = new Scanner(System.in);
        userExit = new Scanner(System.in);


        UIMenu menu = new UIMenu(userInput, userInputSub);
        menu.run();

        System.out.println("To Exit, type 'EXIT' now, or press enter to go back to main menu");
        String userExitString = userExit.nextLine();
        if (userExitString.equals("STOP")){
            System.out.println("True");
        }

        userInput.close();
        userInputSub.close();
        userExit.close();


        }

        }

