import Account.Account;
import Account.AccountManager;
import Entities.Contact;
import Entities.LogIn;
import Entities.Note;
import Entities.PrivateInfoManager;
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

    @Test
    public void testAddInfo() throws Throwable {
        LogIn newLogIn = new LogIn("hayknazaryan", "Idontlikecats", "instagram", "insta");
        Account currentAccount = this.accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newLogIn);

        assert (currentAccount.getVault().size() > 0);
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).GetInfo("username").equals("hayknazaryan"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).GetInfo("password").equals("Idontlikecats"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).GetInfo("webpage").equals("instagram"));
        assert (currentAccount.getPrivateInfo(newLogIn.getId()).GetInfo("url").equals("insta"));
    }

    @Test
    public void testEditInfo() throws Throwable {
        Note newNote = new Note("Shopping List", "Apples");

        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newNote);
        currentAccount.editInfo(newNote.getId(), "content", "carrots");

        assert (currentAccount.getPrivateInfo(newNote.getId())).GetInfo("content").equals("carrots");

    }

    @Test
    public void testDeleteInfo() throws Throwable {
        Contact newContact = new Contact("Hayk", "123-4567890", "55 Joe Street");
        Account currentAccount = accountManager.getAccountByUsername("hayknazaryan");
        currentAccount.addInfo(newContact);
        int initialSize = currentAccount.vault.size();

        assert (currentAccount.deleteInfo(newContact.getId()));
        assert (currentAccount.vault.size() == initialSize - 1);

    }


}
