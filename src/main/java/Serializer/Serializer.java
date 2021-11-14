package Serializer;
import Account.Account;
import Account.AccountManager;

import java.io.*;

/**
 * Class responsible for serializing the current state of the program.
 *
 */
public class Serializer {

    public Serializer(){
        // We would iterate over each account in accounts and make a separate .txt file to save them
    }

    public static void serialize(Account account){
        // So when we close the program, before we close we input the current state of the
        // into this method, and it stores it.

        try{
            String PATH = "src/main/java/Serializer/Data/";
            String filename = account.getUsername() + ".bin";
            FileOutputStream fout = new FileOutputStream(PATH + filename);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(account);
            out.flush();

            out.close();
            System.out.println("Done serializing...");
        }
        catch(Exception e){e.printStackTrace();}
    }

    public static Account deserialize(String Username){
        // We would call this method when we first open the program to check if there are any
        // previously created accounts
        try {
            String PATH = "src/main/java/Serializer/Data/";
            FileInputStream fin = new FileInputStream(PATH + Username + ".bin");
            ObjectInputStream in = new ObjectInputStream(fin);
            Account account = (Account)in.readObject();
            in.close();

            System.out.println("Done Deserializing...");
            return account;

        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
