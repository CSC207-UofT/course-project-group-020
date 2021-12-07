package PrivateInfoObjects;


import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Write Javadoc
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
        info.put("username", username);
        info.put("password", password);
        info.put("webpage", webpage);
        info.put("url", url);
    }

}
