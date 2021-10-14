package Entities;

import java.util.Optional;

/**
 * TODO: Write Javadoc
 */
public class LogIn extends PrivateInfo{

    // Instance attributes for the class LogIn
//    private String username;
//    private String password;
//    private String webpage;
//    private String url;


    /**
     *
     * @param username  The username associated with the LogIn
     * @param password  The password associated with the LogIn
     * @param webpage   The webpage associated with the LogIn
     * @param url       The url of the webpage
     */
    public LogIn(String username, String password, String webpage, String url){
        super();
        info.put("username", username);
        info.put("password", password);
        info.put("webpage", webpage);
        info.put("url", url);

    }}

// TODO: DO WE NEED TO IMPLEMENT EXCEPTIONS AND TRY/CATCHES?

    /**
     *
     * @param attribute The specific instance attribute that is wanted from the LogIn class
     * @return Returns the attribute if the input is valid, else, returns ERROR message
     */
    // TODO: should this be a private or public method

//    @Override
//    public String GetInfo(String attribute) {
//        return switch (attribute) {
//            case "username" -> this.username;
//            case "password" -> this.password;
//            case "webpage" -> this.webpage;
//            case "url" -> this.url;
//            default -> "WRONG ATTRIBUTE";
//        };
//    }

    /**
     * @param attribute The instance attribute that will be changed
     * @param newValue  The new value to overwrite the instance attribute with
     */
//    @Override
//    public void ChangeInfo(String attribute, String newValue) {
//        if (attribute.equals("username")){
//            this.username = newValue;
//        }
//        else if (attribute.equals("password")){
//            this.password = newValue;
//        }
//    }
//}
