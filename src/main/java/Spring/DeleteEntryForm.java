package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to delete an entry from their vault.
 */
public class DeleteEntryForm {
    private String username;
    private String password;
    private String id;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }
}
