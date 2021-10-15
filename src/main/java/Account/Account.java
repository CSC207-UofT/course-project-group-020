package Account;

import Entities.PrivateInfoManager;

/**
 * An entity class that represents a password manager account.
 * <p>
 * Each instance of Account contains a PrivateInfoManager which manages all the private information attached
 * to this Account.
 */
public class Account {

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

    public String getUsername() {
        return this.username;
    }

    public String getMasterPassword() {
        return this.masterPassword;
    }

    public PrivateInfoManager getVault() {
        return this.vault;
    }
}

