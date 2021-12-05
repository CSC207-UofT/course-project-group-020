package Serializer;
import Account.Account;

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
        }
        catch(Exception e){e.printStackTrace();}
    }

    public static Account deserialize(String username){
        // We would call this method when we first open the program to check if there are any
        // previously created accounts
        try {
            String PATH = "src/main/java/Serializer/Data/";
            FileInputStream fin = new FileInputStream(PATH + username + ".bin");
            ObjectInputStream in = new ObjectInputStream(fin);
            Account account = (Account) in.readObject();

            in.close();

            return account;

        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        Account newAcc = new Account("Ryan", "Zhao");
//
//        Serializer.serialize(newAcc);
        Account dAcc = Serializer.deserialize("Ryan");

        System.out.println(dAcc.getUsername());
    }
}
