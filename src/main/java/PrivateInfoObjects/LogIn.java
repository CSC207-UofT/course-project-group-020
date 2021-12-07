package PrivateInfoObjects;

/**
 * This class represents a Login of a user.
 */
public class LogIn extends PrivateInfo {
    private static final long serialVersionUID = 1L;


    /**
     * @param username The username associated with the LogIn, e.g. ("hayknaz")
     * @param password The password associated with the LogIn, e.g. ("IlikedogsALOT")
     * @param webpage  The webpage associated with the LogIn, e.g. ("ACORN")
     * @param url      The url of the webpage, e.g. ("acorn.utoronto.ca")
     */
    public LogIn(String username, String password, String webpage, String url) {
        super();
        this.setType("Login");
        this.buildInfo("username", username);
        this.buildInfo("password", password);
        this.buildInfo("webpage", webpage);
        this.buildInfo("url", url);
    }

}
