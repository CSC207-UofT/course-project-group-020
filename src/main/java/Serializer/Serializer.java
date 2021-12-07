package Serializer;
import Account.Account;

import java.io.*;

/**
 * Class responsible for serializing the current state of the program.
 *
 */
public class Serializer implements ISerializer{
    private static final String DATA_PATH = "src/main/java/Serializer/Data/";
    /**
     * Constructor
     */
    public Serializer(){
        // We would iterate over each account in accounts and make a separate .txt file to save them
    }

    /**
     * Function to serialize and save Account object into a .bin file to DATA_PATH.
     * @param account Account to be serialized
     */
    public void serialize(Account account){
        // So when we close the program, before we close we input the current state of the
        // into this method, and it stores it.

        try{
            String filename = account.getUsername() + ".bin";
            FileOutputStream fout = new FileOutputStream(DATA_PATH + filename);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(account);

            out.flush();
            out.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    /**
     * Function to deserialize an account based on username. Searched for file in DATA_PATH with filname username.bin
     * to deserialize.
     * @param username String username of account to be deserializd
     * @return The deserialized Account if username is found. If username is not found, returns null.
     */
    public Account deserialize(String username){
        // We would call this method when we first open the program to check if there are any
        // previously created accounts
        try {
            FileInputStream fin = new FileInputStream(DATA_PATH + username + ".bin");
            ObjectInputStream in = new ObjectInputStream(fin);
            Account account = (Account) in.readObject();

            in.close();

            return account;

        } catch(IOException | ClassNotFoundException e){
            return null;
        }
    }
}
