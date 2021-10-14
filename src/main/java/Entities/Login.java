package Entities;

import java.util.Optional;

/**
 * TODO: Write Javadoc
 */
public class Login extends PrivateInfo{

    // Instance attributes for the class Login
    private String username;
    private String password;
    private String webpage;
    private String url;


    /**
     *
     * @param username  The username associated with the Login
     * @param password  The password associated with the Login
     * @param webpage   The webpage associated with the Login
     * @param url       The url of the webpage
     */
    public Login(String username, String password, String webpage, String url){
        this.username = username;
        this.password = password;
        this.webpage = webpage;
        this.url = url;

    }
// TODO: DO WE NEED TO IMPLEMENT EXCEPTIONS AND TRY/CATCHES?

    /**
     *
     * @param attribute The specific instance attribute that is wanted from the Login class
     * @return Returns the attribute if the input is valid, else, returns ERROR message
     */
    // TODO: should this be a private or public method

    public String GetInfo(String attribute) {
        return switch (attribute) {
            case "username" -> this.username;
            case "password" -> this.password;
            case "webpage" -> this.webpage;
            case "url" -> this.url;
            default -> "WRONG ATTRIBUTE";
        };
    }

    /**
     * @param attribute The instance attribute that will be changed
     * @param newValue  The new value to overwrite the instance attribute with
     */
    public void ChangeInfo(String attribute, String newValue) {
        if (attribute.equals("username")){
            this.username = newValue;
        }
        else if (attribute.equals("password")){
            this.password = newValue;
        }
    }
}
