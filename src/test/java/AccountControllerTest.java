import Encryption.BlowfishEncryption;
import Encryption.PrivateInfoEncryptor;
import Encryption.SecureHashEncryption;
import Serializer.ISerializer;
import PrivateInfoObjects.PrivateInfo;
import Serializer.Serializer;
import Spring.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of the AccountController class. It tests that each endpoint returns the correct
 * HTTP response with the given request body. Since modifications to the database are made by use-case classes, we
 * do not test that these functions correctly modify the database.
 */

public class AccountControllerTest {
    AccountController accountController = new AccountController();
    PrivateInfoEncryptor encryptor = new BlowfishEncryption();
    ISerializer serializer = new Serializer();

    @Before
    public void setUp() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        accountController.createUser(user);
    }

    /**
     * Tests that createUser returns an HTTP 200(OK) response given username does not already exist in
     * database.
     */
    @Test
    public void testCreateUserSuccess() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Ryan";
        user.password = "CorrectPassword";
        ResponseEntity<?> result = accountController.createUser(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    /**
     * Tests that createUser returns an HTTP 409(Conflict) response if given username already exists in
     * the database.
     */
    @Test
    public void testCreateUserFailure() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "Password1";
        ResponseEntity<?> result = accountController.createUser(user);

        assertEquals(result.getStatusCodeValue(), 409);
    }

    /**
     * Tests that getUserData returns an HTTP 200(OK) response if given a valid username and password.
     */
    @Test
    public void testGetUserDataOK() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    /**
     * Tests that getUserData returns an HTTP 401(Unauthorized) response if given a valid username but invalid password.
     */
    @Test
    public void testGetUserDataUnauthorized() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    /**
     * Tests that getUserData returns an HTTP 404(Not Found) response if given an invalid username.
     */
    @Test
    public void testGetUserDataNotFound() {
        UserInfoForm user = new UserInfoForm();
        user.username = "ImaginaryPerson";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    /**
     * Tests that verifyUser returns an HTTP 200(OK) response if given a valid username and password.
     */
    @Test
    public void testVerifyUserOK() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "CorrectPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    /**
     * Tests that verifyUser returns an HTTP 401(Unauthorized) response if given a valid username but invalid password.
     */
    @Test
    public void testVerifyUserUnauthorized() {
        UserInfoForm user = new UserInfoForm();
        user.username = "Cliff";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    /**
     * Tests that verifyUser returns an HTTP 404(Not Found) response if given an invalid username.
     */
    @Test
    public void testVerifyUserNotFound() {
        UserInfoForm user = new UserInfoForm();
        user.username = "ImaginaryPerson";
        user.password = "WrongPassword";
        ResponseEntity<?> result = accountController.getUserData(user);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    /**
     * Tests that createEntry returns an HTTP 200(OK) response if given a valid username and password.
     */
    @Test
    public void testCreateEntrySuccess() {
        EntryInfoForm form = new EntryInfoForm();
        form.username = "Cliff";
        form.password = "CorrectPassword";
        form.type = "Login";
        form.data = new String[]{"username", "password", "web", "url"};

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    /**
     * Tests that createEntry returns an HTTP 401(Unauthorized) response if given a valid username but invalid password.
     */
    @Test
    public void testCreateEntryUnauthorized() {
        EntryInfoForm form = new EntryInfoForm();
        form.username = "Cliff";
        form.password = "WrongPassword";
        form.type = "Login";
        form.data = new String[]{"username", "password", "web", "url"};

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 401);
    }

    /**
     * Tests that createEntry returns an HTTP 404(Not Found) response if given an invalid username.
     */
    @Test
    public void testCreateEntryNotFound() {
        EntryInfoForm form = new EntryInfoForm();
        form.username = "ImaginaryPerson";
        form.password = "WrongPassword";
        form.type = "Login";
        form.data = new String[]{"username", "password", "web", "url"};

        ResponseEntity<?> result = accountController.createEntry(form);

        assertEquals(result.getStatusCodeValue(), 404);
    }

    /**
     * Tests that deleteEntry returns an HTTP 200(OK) response if given a valid username, password, and id.
     */
    @Test
    public void testDeleteEntryOK() {
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "Cliff";
        entry1.password = "CorrectPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};
        accountController.createEntry(entry1);

        ArrayList<PrivateInfo> acc = encryptor.decryptVault(
                Objects.requireNonNull(serializer.deserialize("Cliff")), "CorrectPassword");

        PrivateInfo note = acc.get(0);
        String id = note.id;

        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = id;

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 200);
    }

    /**
     * Tests that deleteEntry returns an HTTP 401(Unauthorized) response if given a valid username but invalid password.
     */
    @Test
    public void testDeleteEntryUnauthorized() {
        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "WrongPassword";
        deleteForm.id = "Some Random Identification";

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 401);
    }

    /**
     * Tests that deleteEntry returns an HTTP 404(Not Found) response if given an invalid username.
     */
    @Test
    public void testDeleteEntryUserNotFound() {
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "ImaginaryPerson";
        entry1.password = "WrongPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};

        ResponseEntity<?> createEntry1Result = accountController.createEntry(entry1);
        ArrayList<PrivateInfo> acc = encryptor.decryptVault(
                Objects.requireNonNull(serializer.deserialize("Cliff")), "CorrectPassword");
        String id = "randomId";
        accountController.createEntry(entry1);

        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = "RandomID";

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 404);
    }

    /**
     * Tests that deleteEntry returns an HTTP 404(Not Found) response if given a valid username and password but invalid
     * id.
     */
    @Test
    public void testDeleteEntryEntryNotFound() {
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "ImaginaryPerson";
        entry1.password = "WrongPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"DeleteTest", "Content"};
        accountController.createEntry(entry1);


        DeleteEntryForm deleteForm = new DeleteEntryForm();
        deleteForm.username = "Cliff";
        deleteForm.password = "CorrectPassword";
        deleteForm.id = "RandomWrongId";

        ResponseEntity<?> result = accountController.deleteEntry(deleteForm);
        assertEquals(result.getStatusCodeValue(), 404);
    }

    /**
     * Tests that updateEntry returns an HTTP 200(OK) response if given a valid username, password, and id.
     */
    @Test
    public void testUpdateEntryOK(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "Cliff";
        entry1.password = "CorrectPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"Title", "Content"};
        accountController.createEntry(entry1);

        PrivateInfoEncryptor encryptor = new BlowfishEncryption();
        ArrayList<PrivateInfo> vault = encryptor.decryptVault(serializer.deserialize("Cliff"), "CorrectPassword");

        PrivateInfo note = vault.get(0);
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

    /**
     * Tests that updateEntry returns an HTTP 401(Unauthorized) response if given a valid username but invalid password.
     */
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

    /**
     * Tests that updateEntry returns an HTTP 404(Not Found) response if given an invalid username.
     */
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

    /**
     * Tests that updateEntry returns an HTTP 404(Not Found) response if given a valid username and password but invalid
     * id.
     */
    @Test
    public void testUpdateEntryEntryNotFound(){
        EntryInfoForm entry1 = new EntryInfoForm();
        entry1.username = "Cliff";
        entry1.password = "CorrectPassword";
        entry1.type = "Note";
        entry1.data = new String[]{"Title", "Content"};
        accountController.createEntry(entry1);

        UpdateEntryForm updateEntryForm = new UpdateEntryForm();
        updateEntryForm.username = "Cliff";
        updateEntryForm.password = "CorrectPassword";
        updateEntryForm.id = "RANDOMWRONGID";
        updateEntryForm.type = "Note";
        updateEntryForm.data = new String[]{"UpdatedTitle", "updatedContent"};

        ResponseEntity<?> result = accountController.updateEntry(updateEntryForm);

        assertEquals(404, result.getStatusCodeValue());
    }

    /**
     * Tests that deleteUser returns an HTTP response with status code 200(OK) when given a valid username and
     * password.
     */
    @Test
    public void testDeleteUserOK(){
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.username = "deleteOkTest";
        userInfoForm.password = "CorrectPassword";

        accountController.createUser(userInfoForm);
        ResponseEntity<?> testResult = accountController.deleteUser(userInfoForm);
        assertEquals(200, testResult.getStatusCodeValue());
    }

    /**
     * Tests that deleteUser returns an HTTP response with status code 401(OK) when given a valid username but
     * invalid password.
     */
    @Test
    public void testDeleteUserUnauthorized(){
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.username = "deleteUnauthorizedTest";
        userInfoForm.password = "CorrectPassword";

        accountController.createUser(userInfoForm);

        UserInfoForm wrongUserForm = new UserInfoForm();
        wrongUserForm.username = "deleteUnauthorizedTest";
        wrongUserForm.password = "WrongPassword";
        ResponseEntity<?> testResult = accountController.deleteUser(wrongUserForm);
        assertEquals(401, testResult.getStatusCodeValue());
    }

    /**
     * Tests that deleteUser returns an HTTP response with status code 404(Not Found) when given an invalid username.
     */
    @Test
    public void testDeleteUserNotFound(){
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.username = "deleteNotFoundTest";
        userInfoForm.password = "CorrectPassword";

        accountController.createUser(userInfoForm);

        UserInfoForm wrongUserForm = new UserInfoForm();
        wrongUserForm.username = "WrongUser";
        wrongUserForm.password = "CorrectPassword";

        ResponseEntity<?> testResult = accountController.deleteUser(wrongUserForm);
        assertEquals(404, testResult.getStatusCodeValue());
    }

    /**
     * tests the generatePassword returns an HTTP response with a status code 200(OK)
     */
    @Test
    public void testGeneratePassword(){
        GeneratePassForm generatePassForm = new GeneratePassForm();
        generatePassForm.length = 13;
        ResponseEntity<?> testResult = accountController.generatePassword(generatePassForm);

        assertEquals(200, testResult.getStatusCodeValue());

    }

    @After
    public void cleanUp(){
        UserInfoForm deleteUserForm = new UserInfoForm();
        deleteUserForm.username = "Ryan";
        deleteUserForm.password = "CorrectPassword";

        accountController.deleteUser(deleteUserForm);

        deleteUserForm.username = "Cliff";
        accountController.deleteUser(deleteUserForm);

        deleteUserForm.username = "Cliff";
        accountController.deleteUser(deleteUserForm);

        deleteUserForm.username = "deleteNotFoundTest";
        accountController.deleteUser(deleteUserForm);

        deleteUserForm.username = "deleteUnauthorizedTest";
        accountController.deleteUser(deleteUserForm);
    }
}
