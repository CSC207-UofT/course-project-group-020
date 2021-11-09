package Data;
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

    public void Serialize(AccountManager accounts){
        // So when we close the program, before we close we input the current state of the
        // into this method, and it stores it.

        for (Account account: accounts.getAccounts()){

            try{
                String filename = account.getUsername() + ".bin";
                FileOutputStream fout = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fout);
                out.writeObject(account);
                out.flush();

                out.close();
                System.out.println("Done serializing...");
            }
            catch(Exception e){e.printStackTrace();}
        }
    }

    public Account Deserialize(String Username){
        // We would call this method when we first open the program to check if there are any
        // previously created accounts
        try {
            FileInputStream fin = new FileInputStream(Username + ".bin");
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
