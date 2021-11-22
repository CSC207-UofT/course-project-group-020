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
     * A getter method to get a copy of this account's vault
     *
     * @return Returns a copy of this account's vault, which is an instance of PrivateInfoManager
     */
//    public PrivateInfoManager getVault() {
//        return this.vault;
//    }
    public ArrayList<PrivateInfo> getVault() {
        return this.vault;
    }


    public void addInfo(PrivateInfo newInfo) {

        this.vault.add(newInfo);

    }

    public void editInfo(UUID infoId, String attributeToChange, String newValue) throws Throwable {

        for (PrivateInfo info : this.vault) {

            if (info.getId().equals(infoId.toString())) {
                info.SetInfo(attributeToChange, newValue);

            } else {
                throw IndexOutOfBoundsException;
            }
        }


    }

    public void deleteInfo(UUID infoId) {

        int i = 0;

        for (PrivateInfo info : this.vault) {
            if (info.getId().equals(infoId.toString())) {
                break;

            }
            i += 1;
        }

        this.vault.remove(i);
    }


    public PrivateInfo getPrivateInfo(UUID infoId) throws Throwable{

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

