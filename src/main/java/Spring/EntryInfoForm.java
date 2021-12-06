package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to create an entry from their vault.
 */
public class EntryInfoForm {
    public String username;
    public String password;
    public String type;
    public String[] data;
}
