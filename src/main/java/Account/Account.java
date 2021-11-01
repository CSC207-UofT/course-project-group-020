package Account;

import Entities.PrivateInfoManager;

import java.io.Serializable;

/**
 * An entity class that represents a password manager account.
 * <p>
 * Each instance of Account contains a PrivateInfoManager which manages all the private information attached
 * to this Account.
 */
public class Account implements Serializable {

    private final String username;
    private final String masterPassword;
    private final PrivateInfoManager vault;

    /**
     * @param username       The username of this password manager account
     * @param masterPassword The master password of this password manager account
     */
    public Account(String username, String masterPassword) {
        this.username = username;
        this.masterPassword = masterPassword;
        this.vault = new PrivateInfoManager();
    }

    /**
     * A getter method to get the username of this account
     * @return Returns the string value of the username of the account
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * A getter method to get the master password of this account
     * @return Returns the string value of the master password of this account
     */
    public String getMasterPassword() {
        return this.masterPassword;
    }

    /**
     * A getter method to get a copy of this account's vault
     * @return Returns a copy of this account's vault, which is an instance of PrivateInfoManager
     */
    public PrivateInfoManager getVault() {
        return this.vault;
    }
}

