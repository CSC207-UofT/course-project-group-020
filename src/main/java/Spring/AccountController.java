package Spring;
import Account.Account;
import Account.AccountManager;

import Encryption.MasterEncryption;
import Encryption.PrivateInfoEncryption;
import PasswordManagerProgram.PasswordCreation;
import PrivateInfoObjects.*;
import Serializer.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller class that takes input from a web-based frontend and returns HTTP response objects back.
 */
@RestController
public class AccountController { ;
    AccountManager accountManager = new AccountManager();

    public AccountController(){
    }

    /**
     * Returns all the decrypted data of a given user.
     * @param userInfoForm which contain the username of password of a user as strings
     * @return returns an HTTP 200 response with the user's data if the specified user exists and the password
     *         is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     *         response if the password is incorrect
     */
    @PostMapping("/get-user-data")
    public ResponseEntity<?> getUserData(@RequestBody UserInfoForm userInfoForm){
        ResponseEntity<?> verifyResult = accountManager.verifyUser(userInfoForm.username, userInfoForm.password);
        if(verifyResult.getStatusCodeValue() == 200){
            Account userAcc = accountManager.getAccount(userInfoForm.username);
            Account decryptedAcc = PrivateInfoEncryption.decryptAccount(userAcc, userInfoForm.password);
            return new ResponseEntity<>(decryptedAcc, verifyResult.getStatusCode());
        } else {
            return verifyResult;
        }
    }

    /**
     * Verifies that a user exists and matches the password that is given.
     *
     * @param userInfoForm which contain the username of password of a user as strings
     * @return returns an HTTP 200 response if the specified user exists and the password
     *         is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     *         response if the password is incorrect
     */
    @PostMapping("/verify-user")
    public ResponseEntity<?> verifyUser(@RequestBody UserInfoForm userInfoForm){
        return accountManager.verifyUser(userInfoForm.username, userInfoForm.password);
    }



    /**
     * Creates a user with the given username and password in the database. Returns an HTTP response
     * depending on if username already exists in database or not.
     *
     * @param userInfoForm which contain the username of password of a user as strings
     * @return returns an HTTP 200 response if the user does not already exist.
     * Otherwise, returns an HTTP 409 response if the user already exists.
     */
    @PostMapping("/create-user")
    public ResponseEntity<?>  createUser(@RequestBody UserInfoForm userInfoForm){
        ResponseEntity<?> verifyResults = accountManager.verifyUser(userInfoForm.username, userInfoForm.password);

        if(verifyResults.getStatusCodeValue()==200 || verifyResults.getStatusCodeValue()==401){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else{
            accountManager.createAccount(userInfoForm.username, userInfoForm.password);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Adds a PrivateInfo using the given type and data parameters in the specified user's vault  in the database
     * if username and password are valid. Returns an HTTP response depending on if the process was successful or not
     *
     * @param createEntryForm which contains Strings for username, password, and type. Also contains string array of data
     *                        that will be stored in the PrivateInfo object.
     * @return returns an HTTP 200 response with the given responseBody if the specified user exists and the password
     *         is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     *         response if the password is incorrect
     */
    @PostMapping("/create-entry")
    public ResponseEntity<?> createEntry(@RequestBody EntryInfoForm createEntryForm) {
        try {
            ResponseEntity<?> verifyResult = accountManager.verifyUser(createEntryForm.username, createEntryForm.password);
            if (verifyResult.getStatusCodeValue() == 200) {
                PrivateInfo newEntry = PrivateInfoFactory.createEntryByType(createEntryForm.type, createEntryForm.data, createEntryForm.password);
                accountManager.addInfo(newEntry, createEntryForm.username);
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes the entry with the given id from the specified user if username and password are valid in the
     * database. Returns an HTTP response depending on if process is successful
     *
     * @param deleteEntryForm which contains strings for user's username, password, and id of private info.
     * @return returns an HTTP 200 response with the given responseBody if the specified user exists, the password
     *         is correct, and the PrivateInfo with the specified id exists. Returns an HTTP 404 response if the
     *         user does not exist or if user does not have a PrivateInfo with the given id. Returns an HTTP 401
     *         response if the password is incorrect
     */
    @PostMapping("/delete-entry")
    public ResponseEntity<?> deleteEntry(@RequestBody DeleteEntryForm deleteEntryForm) {
        try {
            ResponseEntity<?> verifyResult = accountManager.verifyUser(deleteEntryForm.username, deleteEntryForm.password);
            if (verifyResult.getStatusCodeValue() == 200) {
                boolean result = accountManager.deleteInfo(deleteEntryForm.id, deleteEntryForm.username);

                if(result){
                    return verifyResult;
                } else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Changes the PrivateInfo with the given id in the specified user's vault to be the new one specified by the
     * type and data in the updateEntryForm. Returns an HTTP response based on if process was successful.
     * @param updateEntryForm which contains Strings for the username, password, id of PrivateInfo, and type. Also
     *                        contains a String array of data for the new PrivateIfno
     * @return returns an HTTP 200 response with the given responseBody if the specified user exists, the password
     *         is correct, and the PrivateInfo with the specified id exists. Otherwise, returns an HTTP 404 response
     *         if the user does not exist or an HTTP 401 response if the password is incorrect.
     */
    @PostMapping("/update-entry")
    public ResponseEntity<?> updateEntry(@RequestBody UpdateEntryForm updateEntryForm) {
        try {
            ResponseEntity<?> verifyResult = accountManager.verifyUser(updateEntryForm.username, updateEntryForm.password);
            if (verifyResult.getStatusCodeValue() == 200) {
                PrivateInfo newInfo = PrivateInfoFactory.createEntryByType(updateEntryForm.type, updateEntryForm.data, updateEntryForm.password);
                accountManager.editInfo(newInfo, updateEntryForm.username, updateEntryForm.id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * A function that deletes a user from the database
     *
     * @param userInfoForm which contains the username and password of the user you want to delete.
     * @return Returns a ResponseEntity with HTTP status code 200(OK) if the operation was successful,
     *         404(Not Found) if user does not exist, or 401(Unauthorized) if password does not match username.
     */
    @PostMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestBody UserInfoForm userInfoForm) {
        ResponseEntity<?> verifyResult = accountManager.verifyUser(userInfoForm.username, userInfoForm.password);
        if (verifyResult.getStatusCodeValue() == 200) {
            accountManager.deleteAccount(userInfoForm.username);
        }
        return verifyResult;
    }


    /**
     * A function to generate a random password of a certain length
     *
     * @param generatePassForm which contains an int value for length of password desired
     * @return A HTTP response entity with status code 200(OK). Body of the response contains generated password as
     *         String
     */
    @PostMapping("/generate-password")
    public ResponseEntity<?> generatePassword(@RequestBody GeneratePassForm generatePassForm) {
        String newPass = PasswordCreation.generatePassword(generatePassForm.length);

        return new ResponseEntity<>(newPass, HttpStatus.OK);
    }

}
