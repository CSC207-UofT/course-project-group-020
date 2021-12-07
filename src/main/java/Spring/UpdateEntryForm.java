package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to update/edit an entry.
 */
public class UpdateEntryForm {
    private String username;
    private String password;
    private String id;
    private String type;
    private String[] data;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String[] getData() {
        return data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
