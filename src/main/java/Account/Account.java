package Account;

import PrivateInfoObjects.PrivateInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private final List<PrivateInfo> vault;
    public Throwable IndexOutOfBoundsException;
    private static final long serialVersionUID = 1L;

    /**
     * @param username       The username of this password manager account.
     * @param masterPassword The master password of this password manager account.
     */
    public Account(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
        this.vault = new ArrayList<>();
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
    public List<PrivateInfo> getVault() {
        return this.vault;
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
