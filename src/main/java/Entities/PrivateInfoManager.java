package Entities;

import Encryption.EncryptPrivInfo;

import java.io.Serializable;
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

public class PrivateInfoManager implements Serializable {
// TODO: refactor this

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
     * This method, for whatever subclass, is responsible for changing the information of
     * the necessary attribute. Works with any subclass. It inputs the attribute to be changed,
     * and the new value to be changed to. If the subclass does not have the given attribute,
     * an error message is returned.
     *
     * @param attribute A string input to indicate which attribute to change
     * @param newValue  A string input that is the new value that is to be changed to
     */
    public void FindChangeInfo(String attribute, String newValue) {

        for (PrivateInfo info : this.vault) {
            if (info instanceof LogIn) {
                this.ChangeInfo("password", EncryptPrivInfo.encryptInfo(uiMain.getKey(),
                        info.GetInfo("password")));
            }

            if (info.containsKey(attribute)) {
                info.put(attribute, newValue);

            } else {
                System.out.println("This datatype does not have the " + attribute + " attribute");
            }
        }
    }

    public void changeInfo(String attributeToChange, String newValue, PrivateInfo inputInfo){

        for (PrivateInfo myInfo: this.vault){

            if (myInfo == inputInfo){
                myInfo.SetInfo(attributeToChange, newValue);
            }

        }

    }


    /**
     * The purpose of this method is to provide with a copy of the vault to be used in outer classes.
     *
     * @return Returns a copy of the vault
     */
    public ArrayList<PrivateInfo> getCopy() {

        return new ArrayList<PrivateInfo>(this.vault);
    }

    /**
     * The purpose of this method is to turn the Arraylist vault, stored in this.Vault, into a string
     *
     * @return Returns the string representation of Arraylist stored in this.Vault
     */

    @Override
    public String toString() {
        return this.vault.toString();
    }
}
