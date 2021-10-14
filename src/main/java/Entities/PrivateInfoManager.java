package Entities;

import java.util.ArrayList;

/**
 * This class is responsible for storing and managing all child instances of PrivateInfo;
 *      This includes:
 *          - LogIn
 *          - Note
 *          - Contact
 *          - ID
 *
 * Throughout the duration of entire program, there will only be one instance of PrivateInfoManager that is tied
 * to an Account that keeps track of all the PrivateInfo objects the user of the Account creates.
 *
 * Below is an example use case:
 *     infoVault = new PrivateInfoManager();
 *     LogIn yousufACORN = new LogIn("yousufhassan", "pass123", "ACORN", "acorn.utoronto.ca");
 *     LogIn yousufGmail = new LogIn("yousufhassan@gmail.com, "pass789", "Gmail", "mail.google.com");
 *     Note favouriteMovie = new Note("Favourite Movie", "Shutter Island");
 *
 *     Then, we add all of these PrivateInfo children objects to infoVault, and it keeps track of them.
 */

public class PrivateInfoManager {

    private final ArrayList<PrivateInfo> vault;

    public PrivateInfoManager(){
        vault = new ArrayList<PrivateInfo>();
    }

    /**
     * This is a method responsible for adding new info to our system, such as a new LogIn, ID, Contact or Note.
     *
     * @param newInfo The new info that is to be added to the ArrayList info
     */
    public void addInfo(PrivateInfo newInfo) {

        this.vault.add(newInfo);

    }

    /**
     * This is a method responsible for deleting a specific info from our system, such as a LogIn, ID, Contact or Note.
     *
     * @param toBeDeleted This is the parameter that is to be deleted from the Arraylist and thus from our system.
     */

    public void deleteInfo(PrivateInfo toBeDeleted) {

        this.vault.remove(toBeDeleted);

    }

    @Override
    public String toString(){
        return this.vault.toString();
    }
}
