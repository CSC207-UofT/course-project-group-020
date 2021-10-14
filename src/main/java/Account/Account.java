package Account;

// TODO: Update README.md

import Entities.PrivateInfoManager;

/**
 *  An entity class that represents a password manager account.
 *
 *  Each instance of Account contains a PrivateInfoManager which manages all the private information attached
 *  to this Account.
 */
public class Account{

    private final String username;
    private final String masterPassword;
    private final PrivateInfoManager vault;  // TODO: Maybe change the attribute name

    /**
     *
     * @param username          The username of this password manager account
     * @param masterPassword    The master password of this password manager account
     */
    public Account(String username, String masterPassword){
        this.username = username;
        this.masterPassword = masterPassword;
        this.vault = new PrivateInfoManager();
    }

    public String getUsername() {
        return username;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public PrivateInfoManager getVault() {
        return vault;
    }
}

