package Spring;
import Account.Account;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Encryption.MasterEncryption;
import Entities.*;
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
    @GetMapping("/get-all-user-data")
    List<Account> getAllUserData(){

        File f = new File("/Users/ryanzhao/Projects/course-project-group-020/src/main/java/Serializer/Data");
        String[] fileList = f.list();
        List<Account> accList = new ArrayList<Account>();

        for (String username: fileList) {

            if (username.endsWith(".bin")){
                Account acc = Serializer.deserialize(username.substring(0, username.length() - 4));
                accList.add(acc);
            }
        }
        return accList;
    }

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
        return verifyUserHelper(userInfoForm.username, userInfoForm.password, user);
    }

    @PostMapping("/verify-user")
    ResponseEntity<?> verifyUser(@RequestBody UserInfoForm userInfoForm){

        return verifyUserHelper(userInfoForm.username, userInfoForm.password, null);


//        if (verifyResult.equals("OK")){
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else if (verifyResult.equals("UNAUTHORIZED")){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

//    public String verifyUserHelper(String username, String password){
//        Account user = Serializer.deserialize(username);
//        if (user == null) {
//            return "NOT_FOUND";
//
//        }else {
//            String userAttempt = MasterEncryption.encryptMaster(password);
//
//            if (userAttempt.equals(user.getMasterPassword())){
//                return "OK";
//            } else {
//                return "UNAUTHORIZED";
//            }
//        }
//    }

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


    // TODO: Figure out a way to pass method as parameter? lots of reused code here for create, delete, and update entry

    @PostMapping("/create-entry")
    ResponseEntity<?> createEntry(@RequestBody EntryInfoForm createEntryForm) {
        try {
            ResponseEntity<?> verifyResult = verifyUserHelper(createEntryForm.username, createEntryForm.password, null);
            if (verifyResult.getStatusCodeValue() == 200) {
                Account user = Serializer.deserialize(createEntryForm.username);
                user.addInfo(PrivateInfoFactory.createEntryByType(createEntryForm.type, createEntryForm.data));
                Serializer.serialize(user);
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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


    //update-entry deletes entry with ID and creates a new entry with new id with the edits
    @PostMapping("/update-entry")
    ResponseEntity<?> updateEntry(@RequestBody UpdateEntryForm updateEntryForm) {
        try {
            ResponseEntity<?> verifyResult = verifyUserHelper(updateEntryForm.username, updateEntryForm.password, null);
            if (verifyResult.getStatusCodeValue() == 200) {
                Account user = Serializer.deserialize(updateEntryForm.username);
                user.deleteInfo(updateEntryForm.id);
                user.addInfo(PrivateInfoFactory.createEntryByType(updateEntryForm.type, updateEntryForm.data));
                Serializer.serialize(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return verifyResult;
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
