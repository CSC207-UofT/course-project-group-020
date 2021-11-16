package Spring;
import Account.Account;
import Account.AccountManager;
import java.util.ArrayList;
import java.util.List;

import Entities.*;
import Serializer.Serializer;
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

    @GetMapping("/accounts")
    List<Account> all() {
        accounts.createAccount("Ryan", "Zhao");
        accounts.createAccount("Cliff", "Zhang");

        return accounts.getAccounts();
    }

    @GetMapping("/get-all-user-data")
    List<PrivateInfo> getAllUserData(@RequestParam(value="username") String username,
                                     @RequestParam(value="poi") String poi,
                                     @RequestParam(value="key") String key){
        //TODO: FIRST MUST VERIFY IDENTITY

        Account acc = Serializer.deserialize(username);
        System.out.println(acc.getUsername());
        System.out.println(acc.getMasterPassword());
        System.out.println(acc.getVault());
        PrivateInfoManager vault = acc.getVault();

        return vault.getVault();
    }

    @PostMapping("/create-user")
    void createUser(@RequestBody CreateUserForm createUserForm){
        Account newUser = new Account(createUserForm.username, createUserForm.password);
        Serializer.serialize(newUser);
    }

    @PostMapping("/create-entry")  //TODO: GETMAPPING works here but POSTMAPPING doesn't. Figure out why
    String createEntry(@RequestBody CreateEntryForm createEntryForm) {


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
