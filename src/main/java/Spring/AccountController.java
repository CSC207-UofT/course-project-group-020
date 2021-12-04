package Spring;
import Account.Account;

import Encryption.MasterEncryption;
import PrivateInfoObjects.*;
import Serializer.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO : add docstrings

/*
/verify-identity
REQ: (u, poi)
RES: 401 (no access) || 200 (success)

/get-all-user-data
REQ: (u, poi) + (key)
RES: 401 || 200 (with arr of all user entries)

/create-entry
REQ: (u, poi) + (key) + (data)
RES: 401 || 200 || 500 (server fault)

/update-entry
REQ: (u, poi) + (key) + ({username?: newVal, password?: newVal}, indexInUserArr)
RES: 401 || 200 || 500

/delete-entry
REQ: (u, poi) + (key) + (indexInUserArr)
RES: 401 || 200 || 500

Not important rn
/change-key
REQ: (u, oldPoi) + (oldKey) + (newPoi, newKey)
RES: 401 || 200 || 500
 */

@RestController
public class AccountController { ;

    public AccountController(){
    }

    // TODO: Remove this method? for testing only
//    @GetMapping("/get-all-user-data")
//    List<Account> getAllUserData(){
//
//        File f = new File("/Users/ryanzhao/Projects/course-project-group-020/src/main/java/Serializer/Data");
//        String[] fileList = f.list();
//        List<Account> accList = new ArrayList<Account>();
//
//        for (String username: fileList) {
//
//            if (username.endsWith(".bin")){
//                Account acc = Serializer.deserialize(username.substring(0, username.length() - 4));
//                accList.add(acc);
//            }
//        }
//        return accList;
//    }

    /**
     * Returns all the decrypted data of a given user.
     * @param userInfoForm which contain the username of password of a user as strings
     * @return returns an HTTP 200 response with the user's data if the specified user exists and the password
     *         is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     *         response if the password is incorrect
     */
    @PostMapping("/get-user-data")
    ResponseEntity<?> getUserData(@RequestBody UserInfoForm userInfoForm){

        Account user = Serializer.deserialize(userInfoForm.username);

//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else {
//            String userAttempt = MasterEncryption.encryptMaster(userInfoForm.password);
//
//            if (userAttempt.equals(user.getMasterPassword())){
//                return new ResponseEntity<>(user, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.CONFLICT);
//            }
//        }

        // TODO: Possible error when deserializing user
        // TODO: decrypt user after Kelian finished decryption method
        return verifyUserHelper(userInfoForm.username, userInfoForm.password, user);
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
    ResponseEntity<?> verifyUser(@RequestBody UserInfoForm userInfoForm){

        return verifyUserHelper(userInfoForm.username, userInfoForm.password, null);
    }

    /**
     * Helper method for verifyUser.
     *
     * @param username String of user's username
     * @param password String of user's password
     * @param responseBody Object that is returned as part of response body if username and password is valid
     * @return returns an HTTP 200 response with the given responseBody if the specified user exists and the password
     *         is correct. Otherwise, returns an HTTP 404 response if the user does not exist or an HTTP 401
     *         response if the password is incorrect
     */
    public ResponseEntity<?> verifyUserHelper(String username, String password, Object responseBody){
        Account user = Serializer.deserialize(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else {
            String userAttempt = MasterEncryption.encryptMaster(password);

            if (userAttempt.equals(user.getMasterPassword())){
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
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
    ResponseEntity<?>  createUser(@RequestBody UserInfoForm userInfoForm){
        Account newUser = new Account(userInfoForm.username, MasterEncryption.encryptMaster(userInfoForm.password));
        if (Serializer.deserialize(userInfoForm.username) == null) {
            Serializer.serialize(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
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
    ResponseEntity<?> createEntry(@RequestBody EntryInfoForm createEntryForm) {
        try {
            ResponseEntity<?> verifyResult = verifyUserHelper(createEntryForm.username, createEntryForm.password, null);
            if (verifyResult.getStatusCodeValue() == 200) {
                Account user = Serializer.deserialize(createEntryForm.username);
                user.addInfo(PrivateInfoFactory.createEntryByType(createEntryForm.type, createEntryForm.data, createEntryForm.password));
                Serializer.serialize(user);
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
    ResponseEntity<?> deleteEntry(@RequestBody DeleteEntryForm deleteEntryForm) {
        try {
            ResponseEntity<?> verifyResult = verifyUserHelper(deleteEntryForm.username, deleteEntryForm.password, null);
            if (verifyResult.getStatusCodeValue() == 200) {
                Account user = Serializer.deserialize(deleteEntryForm.username);
                user.deleteInfo(deleteEntryForm.id);
                Serializer.serialize(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
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
    ResponseEntity<?> updateEntry(@RequestBody UpdateEntryForm updateEntryForm) {
        try {
            ResponseEntity<?> verifyResult = verifyUserHelper(updateEntryForm.username, updateEntryForm.password, null);
            if (verifyResult.getStatusCodeValue() == 200) {
                Account user = Serializer.deserialize(updateEntryForm.username);
                user.deleteInfo(updateEntryForm.id);
                user.addInfo(PrivateInfoFactory.createEntryByType(updateEntryForm.type, updateEntryForm.data, updateEntryForm.password));
                Serializer.serialize(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
