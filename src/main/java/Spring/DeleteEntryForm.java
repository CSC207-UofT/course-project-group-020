package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to delete an entry from their vault.
 */
public class DeleteEntryForm {
    public String username;
    public String password;
    public String id;
}
