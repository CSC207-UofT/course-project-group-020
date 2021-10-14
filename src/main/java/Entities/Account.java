package Entities;

// TODO: Update README.md

/**
 *  An entity class that represents a password manager account.
 *
 *  Each instance of Account contains a PrivateInfoManager which manages all the private information attached
 *  to this Account.
 */
public class Account{

    private String username;
    private String masterPassword;
    private PrivateInfoManager privateInfoManager;  // TODO: Maybe change the attribute name

    /**
     *
     * @param username          The username of this password manager account
     * @param masterPassword    The master password of this password manager account
     */
    public Account(String username, String masterPassword){
        this.username = username;
        this.masterPassword = masterPassword;
        this.privateInfoManager = new PrivateInfoManager();
    }

}

