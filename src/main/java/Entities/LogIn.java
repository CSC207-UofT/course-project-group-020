package Entities;

/**
 * TODO: Write Javadoc
 */
public class LogIn extends PrivateInfo {


    /**
     * @param username The username associated with the LogIn, e.g. ("hayknaz")
     * @param password The password associated with the LogIn, e.g. ("IlikedogsALOT")
     * @param webpage  The webpage associated with the LogIn, e.g. ("ACORN")
     * @param url      The url of the webpage, e.g. ("acorn.utoronto.ca")
     */
    public LogIn(String username, String password, String webpage, String url) {
        super();
        this.type = "LogIn";
        info.put("username", username);
        info.put("password", password);
        info.put("webpage", webpage);
        info.put("url", url);
    }


}
