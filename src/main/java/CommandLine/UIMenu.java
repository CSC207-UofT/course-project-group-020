package CommandLine;
import java.util.Scanner;

public class UIMenu {

    Scanner userInput;
    Scanner userInputSub;

    String mainMenu = " (1.) Logins \n (2.) Notes \n (3.) Contacts \n (4.) Address \n (5.) I.D.s ";

    String loginsSubmenu = " (1.) Add Login \n (2.) Change Login \n (3.) Delete Login";


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

            int storedUserInputSub = this.userInputSub.nextInt();


            if (storedUserInputSub == 1){
                //TODO: Add Method

                System.out.println("Login Added!");
            }
            else if (storedUserInputSub == 2){
                //TODO: Add Method

                System.out.println("Login Changed!");
            }
            else if (storedUserInputSub == 3){
                //TODO: Add Method
                System.out.println("Login Deleted!");
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
