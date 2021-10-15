package Entities;

import java.util.ArrayList;

/**
 * This class is responsible for storing and managing all child instances of PrivateInfo;
 * This includes:
 * - LogIn
 * - Note
 * - Contact
 * - ID
 * <p>
 * Throughout the duration of entire program, there will only be one instance of PrivateInfoManager that is tied
 * to an Account that keeps track of all the PrivateInfo objects the user of the Account creates.
 * <p>
 * <p>
 * Below is an example use case:
 * infoVault = new PrivateInfoManager();
 * LogIn yousufACORN = new LogIn("yousufhassan", "pass123", "ACORN", "acorn.utoronto.ca");
 * LogIn yousufGmail = new LogIn("yousufhassan@gmail.com, "pass789", "Gmail", "mail.google.com");
 * Note favouriteMovie = new Note("Favourite Movie", "Shutter Island");
 * <p>
 * Then, we add all of these PrivateInfo children objects to infoVault, and it keeps track of them.
 */

public class PrivateInfoManager {

    private final ArrayList<PrivateInfo> vault;

    public PrivateInfoManager() {
        vault = new ArrayList<>();
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

    // Java warns that this method is never used, we will not fix this style warning because we will be calling this
    // method in our final program (non-beta).
    public void deleteInfo(PrivateInfo toBeDeleted) {
        this.vault.remove(toBeDeleted);
    }


    /**
     * The purpose of this method is to provide with a copy of the vault to be used in outer classes.
     *
     * @return Returns a copy of the vault
     */
    public ArrayList<PrivateInfo> getCopy() {
        return new ArrayList<>(this.vault);
    }

    /**
     * The purpose of this method is to turn the Arraylist vault, stored in this.Vault, into a string
     * @return Returns the string representation of Arraylist stored in this.Vault
     */

    @Override
    public String toString() {
        return this.vault.toString();
    }
}
