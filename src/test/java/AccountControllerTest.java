import Account.Account;
import Account.AccountManager;
import Encryption.MasterEncryption;
import Encryption.PrivateInfoEncryption;
import PrivateInfoObjects.Note;
import PrivateInfoObjects.PrivateInfo;
import Serializer.Serializer;
import Spring.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;

public class AccountControllerTest {
    AccountController accountController = new AccountController();

    @Before
    public void setUp() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        ResponseEntity<?> createUserResult = accountController.createUser(user);
    }

    @Test
    public void testCreateUserSuccess(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Ryan";
        user.password = "Password";
        ResponseEntity<?> result = accountController.createUser(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testCreateUserFailure(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "Password1";
        ResponseEntity<?> result = accountController.createUser(user);

        assertEquals(result.getStatusCodeValue(), 409);
    }

    @Test
    public void testGetUserDataOK(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetUserDataUnauthorized(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    @Test
    public void testGetUserDataNotFound(){
        UserInfoForm user = new UserInfoForm();
        user.username = "ImaginaryPerson";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    @Test
    public void testVerifyUserOK(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testVerifyUserUnauthorized(){
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    @Test
    public void testVerifyUserNotFound(){
        UserInfoForm user = new UserInfoForm();
        user.username = "ImaginaryPerson";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    @Test
    public void testCreateEntrySuccess(){
        EntryInfoForm form = new EntryInfoForm();
        form.username = "Cliff";
        form.password = "CorrectPassword";
        form.type = "Login";
        String[] formdata = {"username", "password", "web", "url"};
        form.data = formdata;

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testCreateEntryUnauthorized(){
        EntryInfoForm form = new EntryInfoForm();
        form.username = "Cliff";
        form.password = "WrongPassword";
        form.type = "Login";
        String[] formdata = {"username", "password", "web", "url"};
        form.data = formdata;

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    @Test
    public void testCreateEntryNotFound(){
        EntryInfoForm form = new EntryInfoForm();
        form.username = "ImaginaryPerson";
        form.password = "WrongPassword";
        form.type = "Login";
        String[] formdata = {"username", "password", "web", "url"};
        form.data = formdata;

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    @Test
    public void testDeleteEntryOK(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "Cliff";
        entry1.password = "CorrectPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};
        ResponseEntity<?> createEntry1Result = accountController.createEntry(entry1);

        Account acc = PrivateInfoEncryption.decryptAccount(Serializer.deserialize("Cliff"), "CorrectPassword");

        PrivateInfo note = acc.getVault().get(0);
        String id = note.id;

        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = id;

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testDeleteEntryUnauthorized(){
        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "WrongPassword";
        deleteForm.id = "Some Random ID";

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 401);
    }

    @Test
    public void testDeleteEntryUserNotFound(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "ImaginaryPerson";
        entry1.password = "WrongPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};
        ResponseEntity<?> createEntry1Result = accountController.createEntry(entry1);

        Account acc = PrivateInfoEncryption.decryptAccount(Serializer.deserialize("Cliff"), "CorrectPassword");

        String id = "randomId";

        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = id;

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 404);
    }

    @Test
    public void testDeleteEntryEntryNotFound(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "ImaginaryPerson";
        entry1.password = "WrongPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};
        ResponseEntity<?> createEntry1Result = accountController.createEntry(entry1);

        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = "RandomWrongId";

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 404);
    }

    @Test
    public void testUpdateEntryOK(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "Cliff";
        entry1.password = "CorrectPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"Title", "Content"};
        ResponseEntity<?> createEntry1Result = accountController.createEntry(entry1);

        Account acc = PrivateInfoEncryption.decryptAccount(Serializer.deserialize("Cliff"), "CorrectPassword");

        PrivateInfo note = acc.getVault().get(0);
        String id = note.id;

        UpdateEntryForm updateEntryForm = new UpdateEntryForm();
        updateEntryForm.username = "Cliff";
        updateEntryForm.password = "CorrectPassword";
        updateEntryForm.id = id;
        updateEntryForm.type = "Note";
        updateEntryForm.data = new String[]{"UpdatedTitle", "updatedContent"};

        ResponseEntity<?> result = accountController.updateEntry(updateEntryForm);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateEntryUnauthorized(){
        UpdateEntryForm updateEntryForm = new UpdateEntryForm();
        updateEntryForm.username = "Cliff";
        updateEntryForm.password = "WrongPassword";
        updateEntryForm.id = "SomeRandomId";
        updateEntryForm.type = "Note";
        updateEntryForm.data = new String[]{"UpdatedTitle", "updatedContent"};

        ResponseEntity<?> result = accountController.updateEntry(updateEntryForm);

        assertEquals(401, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateEntryUserNotFound(){
        UpdateEntryForm updateEntryForm = new UpdateEntryForm();
        updateEntryForm.username = "ImaginaryPerson";
        updateEntryForm.password = "WrongPassword";
        updateEntryForm.id = "SomeRandomId";
        updateEntryForm.type = "Note";
        updateEntryForm.data = new String[]{"UpdatedTitle", "updatedContent"};

        ResponseEntity<?> result = accountController.updateEntry(updateEntryForm);

        assertEquals(404, result.getStatusCodeValue());
    }

    @After
    public void cleanUp(){

    }
}
