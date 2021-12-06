package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to update/edit an entry.
 */
public class UpdateEntryForm {
    public String username;
    public String password;
    public String id;
    public String type;
    public String[] data;
}
