import Account.Account;
import Account.AccountManager;
import Entities.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of adding, deleting and editing PrivateInfo such as LogIns, Notes, IDs, and
 * Contacts from an individual account.
 */

public class PrivateInfoTest {
    String myMasterPassword = "Ilikedogsalot";
    AccountManager accountManager = new AccountManager();

    @Before
    public void setUp() {
        this.accountManager.createAccount("hayknazaryan", myMasterPassword);
    }

    /**
     * The four tests below test the addInfo method with different PrivateInfo objects.
     */
    @Test
    public void testAddInfoWithLogin() throws Throwable {
        LogIn newLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");

        assert (currentAccount.addInfo(newLogIn));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("username").equals("hayknazaryan"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("password").equals("Idontlikecats"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("webpage").equals("instagram"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("url").equals("insta"));
    }

    @Test
    public void testAddInfoWithContact() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");

        assert (currentAccount.addInfo(newContact));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("name").equals("Hayk"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("number").equals("123-4567890"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("address").equals("55 Joe Street"));
    }

    @Test
    public void testAddInfoWithID() throws Throwable {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");

        assert (currentAccount.addInfo(newID));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDType").equals("Driver's License"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDNumber").equals("12345"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDExpirationDate").equals("Dec 31, 2030"));
    }

    @Test
    public void testAddInfoWithNote() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");

        assert (currentAccount.addInfo(newNote));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newNote.getId()).getInfo("title").equals("Shopping List"));
        assert (currentAccount.getPrivateInfo(newNote.getId()).getInfo("content").equals("Apples"));
    }

    /**
     * The four tests below test the editInfo method with different PrivateInfo objects.
     */
    @Test
    public void testEditInfoWithLogin() throws Throwable {
        LogIn newLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newLogIn);

        assert (currentAccount.editInfo(newLogIn.getId(), "username", "hayknazaryan1"));
        assert (currentAccount.editInfo(newLogIn.getId(), "password", "ilovecats"));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("username").equals("hayknazaryan1"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("password").equals("ilovecats"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("webpage").equals("instagram"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("url").equals("insta"));
    }

    @Test
    public void testEditInfoWithContact() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newContact);

        assert (currentAccount.editInfo(newContact.getId(), "address", "123 John Lane"));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("name").equals("Hayk"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("number").equals("123-4567890"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("address").equals("123 John Lane"));
    }

    @Test
    public void testEditInfoWithID() throws Throwable {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newID);

        assert (currentAccount.editInfo(newID.getId(), "IDExpirationDate", "Dec 31, 2035"));
        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDType").equals("Driver's License"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDNumber").equals("12345"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDExpirationDate").equals("Dec 31, 2035"));
    }

    @Test
    public void testEditInfoWithNote() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newNote);

        assert (currentAccount.editInfo(newNote.getId(), "content", "carrots"));
        assert (currentAccount.getPrivateInfo(newNote.getId())).getInfo("content").equals("carrots");

    }

    /**
     * The four tests below test the deleteInfo method with different PrivateInfo objects.
     */
    @Test
    public void testDeleteInfoWithLogin() throws Throwable {
        LogIn newLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newLogIn);
        int initialSize = currentAccount.vault.size();

        assert (currentAccount.deleteInfo(newLogIn.getId()));
        assert (currentAccount.vault.size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithContact() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newContact);
        int initialSize = currentAccount.vault.size();

        assert (currentAccount.deleteInfo(newContact.getId()));
        assert (currentAccount.vault.size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithID() throws Throwable {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newID);
        int initialSize = currentAccount.vault.size();

        assert (currentAccount.deleteInfo(newID.getId()));
        assert (currentAccount.vault.size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithNote() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newNote);
        int initialSize = currentAccount.vault.size();

        assert (currentAccount.deleteInfo(newNote.getId()));
        assert (currentAccount.vault.size() == initialSize - 1);
    }
}
