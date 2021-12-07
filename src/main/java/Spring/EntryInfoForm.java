package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to create an entry from their vault.
 */
public class EntryInfoForm {
    private String username;
    private String password;
    private String type;
    private String[] data;

    public String[] getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
