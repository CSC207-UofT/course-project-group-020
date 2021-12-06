package Account;

import Encryption.MasterEncryption;
import PrivateInfoObjects.PrivateInfo;
import Serializer.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Iterator;

/**
 * This class keeps track of all the Password Manager Accounts. It does so in the ArrayList called
 * accounts.
 * <p>
 * It is also responsible for creating new Accounts, deleting Accounts, and logging in to the vault
 * associated with the Account.
 * <p>
 * It has an instance attribute called accounts that is an ArrayList that
 */
public class AccountManager {
    //    public ArrayList<Account> accounts;
    public Throwable NullPointerException;
    private Account currentAccount;

    public AccountManager() {
//        accounts = new ArrayList<>();
    }

    /**
     * Creates a new account into our system.
     *
     * @param username       The username associated with this new Account.
     * @param masterPassword The master password associated with this new Account.
     */
    public boolean createAccount(String username, String masterPassword) {
        String encryptedMasterPassword = MasterEncryption.encryptMaster(masterPassword);
        Account account = new Account(username, encryptedMasterPassword);

        // Stores this new account in our data folder with the other accounts.
        Serializer.serialize(account);
        return true;
    }

    public Account getAccount(String username) {

        return Serializer.deserialize(username);

    }

    /**
     * Helper method for verifyUser.
     *
     * @param username String of user's username
     * @param password String of user's password
     * @return returns an HTTP 200 response with the user as response body if the specified user exists and the password
     * is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     * response if the password is incorrect
     */
    public ResponseEntity<?> verifyUser(String username, String password) {
        Account user = Serializer.deserialize(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            String userAttempt = MasterEncryption.encryptMaster(password);
            if (userAttempt.equals(user.getMasterPassword())) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }

    /**
     * This method is responsible for adding new instances of PrivateInfo into the vault of a given account.
     *
     * @param newInfo         The new PrivateInfo that is to be added to the vault.
     * @param accountUsername The username of the account that the PrivateInfo instance newInfo is to be
     *                        added to.
     * @return Returns true if the PrivateInfo object was added to the vault.
     */
    public boolean addInfo(PrivateInfo newInfo, String accountUsername) {

        Account currentAccount = getAccount(accountUsername);

        currentAccount.getVault().add(newInfo);

        Serializer.serialize(currentAccount);

        return true;
    }

    /**
     * This method is responsible to edit an instance of PrivateInfo from the vault of a given account.
     * It uses the string representation of the UUID of an PrivateInfo already present in the account, deletes it,
     * and replaces it with the given new PrivateInfo instance, newInfo.
     *
     * @param newInfo         The new PrivateInfo that is to be added into the account.
     * @param accountUsername The username of the account that is need of editing.
     * @param oldInfoId       The string representation of the UUID of an old PrivateInfo already present
     *                        in the given account.
     * @return Returns true if the PrivateInfo was changed.
     */
    public boolean editInfo(PrivateInfo newInfo, String accountUsername, String oldInfoId) {

        Account currentAccount = getAccount(accountUsername);

        // Delete the info first
        boolean check = deleteInfo(oldInfoId, accountUsername);

        if (check) {
            return addInfo(newInfo, accountUsername);

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
    public boolean deleteInfo(String infoId, String accountUsername) {

        Account currentAccount = Serializer.deserialize(accountUsername);

        assert currentAccount != null;
        Iterator<PrivateInfo> iter = currentAccount.getVault().iterator();

        while (iter.hasNext()) {
            PrivateInfo info = iter.next();
            if (info.getId().equals(infoId)) {
                iter.remove();
                Serializer.serialize(currentAccount);
                return true;
            }
        }
        return false;
    }


    public boolean deleteAccount(String username) {

        try {
            String PATH = "src/main/java/Serializer/Data/";
            File fin = new File(PATH + username + ".bin");

            return fin.delete();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An account with this username does not exist.");
            return false;
        }
    }

}


//    public ArrayList<Account> getAccounts() {
//        return this.accounts;
//    }
//
//    public Account getAccount(Account wantedAccount) {
//
//        int i = 0;
//        for (Account account : this.accounts) {
//            if (account == wantedAccount) {
//                break;
//            }
//            i += 1;
//        }
//        return this.accounts.get(i);
//
//
//    }

//    public Account getAccountByUsername(String usernameOfWantedAccount) throws Throwable{
//        int i = 0;
//        for (Account account : this.accounts) {
//
//            if (account.getUsername().equals(usernameOfWantedAccount)) {
//                return this.accounts.get(i);
//            }
//            i += 1;
//        }
//        throw NullPointerException;
//    }
//}

