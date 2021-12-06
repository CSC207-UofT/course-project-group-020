import Account.Account;
import Account.AccountManager;
import PrivateInfoObjects.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionality of adding, deleting and editing PrivateInfo such as LogIns, Notes, IDs, and
 * Contacts from an individual account.
 */

public class AccountManagerTest {
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


        assert (this.accountManager.addInfo(newLogIn, "hayknazaryan"));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");


        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("username").equals("hayknazaryan"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("password").equals("Idontlikecats"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("webpage").equals("instagram"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).getInfo("url").equals("insta"));
    }

    @Test
    public void testAddInfoWithContact() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");

        assert (this.accountManager.addInfo(newContact, "hayknazaryan"));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");


        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("name").equals("Hayk"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("number").equals("123-4567890"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("address").equals("55 Joe Street"));
    }

    @Test
    public void testAddInfoWithID() throws Throwable {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");


        assert (this.accountManager.addInfo(newID, "hayknazaryan"));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");

        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDType").equals("Driver's License"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDNumber").equals("12345"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDExpirationDate").equals("Dec 31, 2030"));
    }

    @Test
    public void testAddInfoWithNote() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples");

        assert (this.accountManager.addInfo(newNote,"hayknazaryan"));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");

        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newNote.getId()).getInfo("title").equals("Shopping List"));
        assert (currentAccount.getPrivateInfo(newNote.getId()).getInfo("content").equals("Apples"));
    }

    /**
     * The four tests below test the editInfo method with different PrivateInfo objects.
     */
    @Test
    public void testEditInfoWithLogin() throws Throwable {
        LogIn newLogIn1 = new LogIn("hayknazaryan", "hejaBVB", "instagram", "insta");
        LogIn newLogIn2 = new LogIn("hayknazaryan", "hejaBVB", "snapchat", "snap");

        LogIn oldLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");


        // Add old LogIn for then after to test the changes.
        this.accountManager.addInfo(oldLogIn,"hayknazaryan");


        assert (this.accountManager.editInfo(newLogIn1, "hayknazaryan", oldLogIn.getId()));
        assert (this.accountManager.editInfo(newLogIn2, "hayknazaryan", newLogIn1.getId()));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");

        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newLogIn2.getId()).getInfo("username").equals("hayknazaryan"));
        assert (currentAccount.getPrivateInfo(newLogIn2.getId()).getInfo("password").equals("hejaBVB"));
        assert (currentAccount.getPrivateInfo(newLogIn2.getId()).getInfo("webpage").equals("snapchat"));
        assert (currentAccount.getPrivateInfo(newLogIn2.getId()).getInfo("url").equals("snap"));
    }

    @Test
    public void testEditInfoWithContact() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");
        Contact oldContact = new Contact("Hayk", "564-2345567", "280 Quebec Avenue");



        // Add old Contact for then after to test the changes.
        this.accountManager.addInfo(oldContact,"hayknazaryan");
        assert (this.accountManager.editInfo(newContact, "hayknazaryan", oldContact.getId()));

        Account currentAccount = this.accountManager.getAccount("hayknazaryan");

        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("name").equals("Hayk"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("number").equals("123-4567890"));
        assert (currentAccount.getPrivateInfo(newContact.getId()).getInfo("address").
                equals("55 Joe Street"));
    }

    @Test
    public void testEditInfoWithID() throws Throwable {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");
        ID oldID = new ID("Driver's License", "12345", "March 25, 2015");



        // Add old ID for then after to test changes.
        this.accountManager.addInfo(oldID,"hayknazaryan");

        assert (this.accountManager.editInfo(newID, "hayknazaryan", oldID.getId()));
        Account currentAccount = this.accountManager.getAccount("hayknazaryan");


        assert (currentAccount.getVault().size() == 1);
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDType").equals("Driver's License"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDNumber").equals("12345"));
        assert (currentAccount.getPrivateInfo(newID.getId()).getInfo("IDExpirationDate").
                equals("Dec 31, 2030"));
    }

    @Test
    public void testEditInfoWithNote() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples, Oranges");
        Note oldNote = new Note("Shopping List", "Apples");


        // Add old Note for then after to test changes.
        this.accountManager.addInfo(oldNote,"hayknazaryan");

        assert (this.accountManager.editInfo(newNote, "hayknazaryan", oldNote.getId()));

        Account currentAccount = accountManager.getAccount("hayknazaryan");
        assert (currentAccount.getPrivateInfo(newNote.getId())).getInfo("content").equals("Apples, Oranges");

    }

    /**
     * The four tests below test the deleteInfo method with different PrivateInfo objects.
     */
    @Test
    public void testDeleteInfoWithLogin()  {
        LogIn newLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");


        this.accountManager.addInfo(newLogIn, "hayknazaryan");
        Account currentAccount = accountManager.getAccount("hayknazaryan");
        int initialSize = currentAccount.getVault().size();

        assert (this.accountManager.deleteInfo(newLogIn.getId(), "hayknazaryan"));
        assert (this.accountManager.getAccount("hayknazaryan").getVault().size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithContact()  {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");

        this.accountManager.addInfo(newContact, "hayknazaryan");
        Account currentAccount = accountManager.getAccount("hayknazaryan");

        int initialSize = currentAccount.getVault().size();

        assert (this.accountManager.deleteInfo(newContact.getId(), "hayknazaryan"));
        assert (this.accountManager.getAccount("hayknazaryan").getVault().size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithID()  {
        ID newID = new ID("Driver's License", "12345", "Dec 31, 2030");


        this.accountManager.addInfo(newID, "hayknazaryan");
        Account currentAccount = accountManager.getAccount("hayknazaryan");
        int initialSize = currentAccount.getVault().size();

        assert (this.accountManager.deleteInfo(newID.getId(), "hayknazaryan"));
        assert (this.accountManager.getAccount("hayknazaryan").getVault().size() == initialSize - 1);
    }

    @Test
    public void testDeleteInfoWithNote() {
        Note newNote = new Note("Shopping List", "Apples");

        this.accountManager.addInfo(newNote, "hayknazaryan");
        Account currentAccount = accountManager.getAccount("hayknazaryan");
        int initialSize = currentAccount.getVault().size();

        assert (this.accountManager.deleteInfo(newNote.getId(), "hayknazaryan"));
        assert (this.accountManager.getAccount("hayknazaryan").getVault().size() == initialSize - 1);
    }
}
