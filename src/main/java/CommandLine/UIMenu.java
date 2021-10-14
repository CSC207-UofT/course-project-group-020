package CommandLine;
import java.util.Scanner;

public class UIMenu {

    Scanner userInput;
    Scanner userInputSub;

    String mainMenu = " (1.) Logins \n (2.) Notes \n (3.) Contacts \n (4.) Address \n (5.) I.D.s ";

    String loginsSubmenu = " (1.) Add LogIn \n (2.) Change LogIn \n (3.) Delete LogIn";


    public UIMenu(Scanner userInput, Scanner userInputSub){

        this.userInput = userInput;
        this.userInputSub = userInputSub;


    }

    public void run(){

        System.out.println("MAIN MENU");
        System.out.println(this.mainMenu);

    try{

        System.out.println("Please enter a number 1 - 5: ");
        String storedUserInput = this.userInput.nextLine();


        if (storedUserInput.equals("1")){

            System.out.println(this.loginsSubmenu);
            System.out.println("Please enter a number 1 - 3: ");

            String storedUserInputSub = this.userInputSub.nextLine();

            if (storedUserInputSub.equals("1")){
                //TODO: Add Method

                System.out.println("LogIn Added!");
            }
            else if (storedUserInputSub.equals("2")){
                //TODO: Add Method

                System.out.println("LogIn Changed!");
            }
            else if (storedUserInputSub.equals("3")){
                //TODO: Add Method
                System.out.println("LogIn Deleted!");
            }
            else{
                System.out.println("INVALID INPUT");
            }
        }
        else{
            System.out.println("Sorry! This is just a beta, more coming soon!");

        }




    } finally {
        if (this.userInput != null && this.userInputSub != null){
            this.userInput.close();
            this.userInputSub.close();

        }
    }
    }


}
