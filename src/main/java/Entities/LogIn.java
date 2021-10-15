package Entities;

/**
 * TODO: Write Javadoc
 */
public class LogIn extends PrivateInfo {

    /**
     * @param username The username associated with the LogIn
     * @param password The password associated with the LogIn
     * @param webpage  The webpage associated with the LogIn
     * @param url      The url of the webpage
     */
    public LogIn(String username, String password, String webpage, String url) {
        super();
        info.put("username", username);
        info.put("password", password);
        info.put("webpage", webpage);
        info.put("url", url);
    }
}
