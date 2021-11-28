package Account;

import Entities.PrivateInfo;
import Entities.PrivateInfoManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * An entity class that represents a password manager account.
 * <p>
 * Each instance of Account contains a PrivateInfoManager which manages all the private information attached
 * to this Account.
 */
public class Account implements Serializable {

    private final String username;
    private final String masterPassword;
    // This defaults to an empty ArrayList.
    public ArrayList<PrivateInfo> vault;



    private Throwable IndexOutOfBoundsException;

    /**
     * @param username       The username of this password manager account
     * @param masterPassword The master password of this password manager account
     */
    public Account(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
    }

    /**
     * A getter method to get the username of this account
     *
     * @return Returns the string value of the username of the account
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * A getter method to get the master password of this account
     *
     * @return Returns the string value of the master password of this account
     */
    public String getMasterPassword() {
        return this.masterPassword;
    }


    /**
     * A getter method to get this account's vault which is an ArrayList of PrivateInfo.
     * @return Returns the vault which is an ArrayList of PrivateInfo.
     */
    public ArrayList<PrivateInfo> getVault() {
        return this.vault;
    }


    /**
     * This method is responsible for adding new instances of PrivateInfo into the vault of this account.
     * @param newInfo The new PrivateInfo that is to be added to the vault.
     */
    public void addInfo(PrivateInfo newInfo) {

        this.vault.add(newInfo);

    }

    /**
     * This method is responsible for editing the wanted instance of PrivateInfo from the vault.
     *
     * @param infoId The string representation of the unique UUID of the instance of PrivateInfo.
     * @param attributeToChange The string representation of the wanted attribute to change,
     *                          such as "username" or "password".
     * @param newValue The new string value that is to be changed to.
     * @throws Throwable Throws Exception.
     */
    public void editInfo(String infoId, String attributeToChange, String newValue) throws Throwable {

        for (PrivateInfo info : this.vault) {

            if (info.getId().equals(infoId.toString())) {
                info.SetInfo(attributeToChange, newValue);

            } else {
                throw IndexOutOfBoundsException;
            }
        }


    }

    /**
     * This method is responsible for deleting the wanted instance of PrivateInfo from the vault.
     *
     * @param infoId The string representation of the unique UUID for the instance of
     *               PrivateInfo that is wanted to be deleted.
     */
    public void deleteInfo(String infoId) {

        int i = 0;

        for (PrivateInfo info : this.vault) {
            if (info.getId().equals(infoId.toString())) {
                break;

            }
            i += 1;
        }

        this.vault.remove(i);
    }


    /**
     * This is a getter method responsible for getting the wanted PrivateInfo from the vault.
     *
     * @param infoId The string representation of the unique UUID of the wanted PrivateInfo instance.
     * @return Returns the wanted PrivateInfo instance.
     * @throws Throwable Throws Exception.
     */
    public PrivateInfo getPrivateInfo(String infoId) throws Throwable{

        int i = 0;

        for (PrivateInfo info: this.vault){
            if (info.getId().equals(infoId.toString())){
                return this.vault.get(i);
            }

            i += 1;
        }

        throw IndexOutOfBoundsException;

    }


}
