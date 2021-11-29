package Account;

import Entities.PrivateInfo;

import javax.swing.text.html.HTMLDocument;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * An entity class that represents a password manager account.
 * <p>
 * Each instance of Account has a username and master password that are attached to it. It also contains a vault that
 * is an array list of PrivateInfo objects. The vault holds all the user's PrivateInfo such as their Logins, Contacts,
 * IDs, and Notes.
 */
public class Account implements Serializable {

    private final String username;
    private final String masterPassword;
    public ArrayList<PrivateInfo> vault = new ArrayList<>();
    public Throwable IndexOutOfBoundsException;

    /**
     * @param username       The username of this password manager account.
     * @param masterPassword The master password of this password manager account.
     */
    public Account(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
    }

    /**
     * A getter method to get the username of this account.
     *
     * @return Returns the string value of the username of the account.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * A getter method to get the master password of this account.
     *
     * @return Returns the string value of the master password of this account.
     */
    public String getMasterPassword() {
        return this.masterPassword;
    }


    /**
     * A getter method to get this account's vault which is an ArrayList of PrivateInfo.
     *
     * @return Returns the vault which is an ArrayList of PrivateInfo.
     */
    public ArrayList<PrivateInfo> getVault() {
        return this.vault;
    }


    /**
     * This method is responsible for adding new instances of PrivateInfo into the vault of this account.
     *
     * @param newInfo The new PrivateInfo that is to be added to the vault.
     * @return Returns true if the PrivateInfo object was added to the vault
     */
    public boolean addInfo(PrivateInfo newInfo) {
        this.vault.add(newInfo);
        return true;
    }

    /**
     * This method is responsible for editing the wanted instance of PrivateInfo from the vault.
     *
     * @param infoId            The string representation of the unique UUID of the instance of PrivateInfo.
     * @param attributeToChange The string representation of the wanted attribute to change,
     *                          such as "username" or "password".
     * @param newValue          The new string value that is to be changed to.
     * @return Returns true if the PrivateInfo object was edited; false, otherwise.
     */
    public boolean editInfo(String infoId, String attributeToChange, String newValue) {

        for (PrivateInfo info : this.vault) {
            if (info.getId().equals(infoId)) {
                info.setInfo(attributeToChange, newValue);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is responsible for deleting the wanted instance of PrivateInfo from the vault.
     *
     * @param infoId The string representation of the unique UUID for the instance of
     *               PrivateInfo that is wanted to be deleted.
     * @return Returns true if the PrivateInfo object was deleted from the vault; false, otherwise.
     */
    public boolean deleteInfo(String infoId) {

        Iterator<PrivateInfo> iter = this.vault.iterator();

        while (iter.hasNext()) {
            PrivateInfo info = iter.next();
            if (info.getId().equals(infoId)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }


    /**
     * This is a getter method responsible for getting the wanted PrivateInfo from the vault.
     *
     * @param infoId The string representation of the unique UUID of the wanted PrivateInfo instance.
     * @return Returns the wanted PrivateInfo instance.
     * @throws Throwable Throws Exception.
     */
    public PrivateInfo getPrivateInfo(String infoId) throws Throwable {

        int i = 0;

        for (PrivateInfo info : this.vault) {
            if (info.getId().equals(infoId)) {
                return this.vault.get(i);
            }

            i += 1;
        }

        throw IndexOutOfBoundsException;

    }
}
