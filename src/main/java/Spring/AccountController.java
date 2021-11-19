package Spring;
import Account.Account;
import Account.AccountManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Encryption.EncryptMaster;
import Entities.*;
import Serializer.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
public class AccountController {
    private AccountManager accounts;

    public AccountController(){
        accounts = new AccountManager();
    }

    public AccountController(AccountManager acc) {
        accounts = acc;
    }

    @GetMapping("/get-all-user-data")
    List<String> getAllUserData(){
        //TODO: FIRST MUST VERIFY IDENTITY

        File f = new File("/Users/ryanzhao/Projects/course-project-group-020/src/main/java/Serializer/Data");
        String[] fileList = f.list();
        List<String> accList = new ArrayList<String>();

        for (String username: fileList) {

            if (username.endsWith(".bin")){
                Account acc = Serializer.deserialize(username);
                accList.add(acc.getUsername());
            }
        }
        return accList;
    }

    @PostMapping("/get-user-data")
    ResponseEntity<?> getUserData(@RequestBody UserInfoForm userInfoForm){

        Account user = Serializer.deserialize(userInfoForm.username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            String userAttempt = EncryptMaster.encryptMaster(userInfoForm.password);

            if (userAttempt.equals(user.getMasterPassword())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
    }

    @PostMapping("/verify-user")
    ResponseEntity<?> verifyUser(@RequestBody UserInfoForm userInfoForm){

        String verifyResult = verifyUserHelper(userInfoForm.username, userInfoForm.password);

        if (verifyResult.equals("OK")){
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (verifyResult.equals("UNAUTHORIZED")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String verifyUserHelper(String username, String password){
        Account user = Serializer.deserialize(username);
        if (user == null) {
            return "NOT_FOUND";

        }else {
            String userAttempt = EncryptMaster.encryptMaster(password);

            if (userAttempt.equals(user.getMasterPassword())){
                return "OK";
            } else {
                return "UNAUTHORIZED";
            }
        }
    }

    @PostMapping("/create-user")
    ResponseEntity<?>  createUser(@RequestBody UserInfoForm userInfoForm){
        Account newUser = new Account(userInfoForm.username, EncryptMaster.encryptMaster(userInfoForm.password));

        if (Serializer.deserialize(userInfoForm.username) == null) {
            Serializer.serialize(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/create-entry")  //TODO: GETMAPPING works here but POSTMAPPING doesn't. Figure out why
    ResponseEntity<?> createEntry(@RequestBody EntryInfoForm createEntryForm) {
        String verifyResult = verifyUserHelper(createEntryForm.username, createEntryForm.password);
        if (verifyResult.equals("OK")){
            Account user = Serializer.deserialize(createEntryForm.username);
            user.getVault().addInfo(createEntryByType(createEntryForm.type, createEntryForm.data));
            Serializer.serialize(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (verifyResult.equals("UNAUTHORIZED")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete-entry")  //TODO: GETMAPPING works here but POSTMAPPING doesn't. Figure out why
    ResponseEntity<?> deleteEntry(@RequestBody EntryInfoForm createEntryForm) {
        String verifyResult = verifyUserHelper(createEntryForm.username, createEntryForm.password);
        if (verifyResult.equals("OK")){
            Account user = Serializer.deserialize(createEntryForm.username);
            user.getVault().addInfo(createEntryByType(createEntryForm.type, createEntryForm.data));
            Serializer.serialize(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (verifyResult.equals("UNAUTHORIZED")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update-entry")  //TODO: GETMAPPING works here but POSTMAPPING doesn't. Figure out why
    String updateEntry(@RequestBody EntryInfoForm createEntryForm) {

        //TODO: Verify identity with username and poi

        Account user = Serializer.deserialize(createEntryForm.username);

        user.getVault().addInfo(createEntryByType(createEntryForm.type, createEntryForm.data));

        Serializer.serialize(user);

        return "201";
    }

    PrivateInfo createEntryByType(String type, String[] data){
        if (type.equals("Login")){
            return PrivateInfoFactory.createLogin(data);
        } else if (type.equals("Contact")){
            return PrivateInfoFactory.createContact(data);
        } else if (type.equals("ID")){
            return PrivateInfoFactory.createID(data);
        } else if (type.equals("Note")){
            return PrivateInfoFactory.createNote(data);
        } else{
            return PrivateInfoFactory.createNote(data); //TODO fix this to raise error
        }
    }






}
