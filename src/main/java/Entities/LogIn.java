package Entities;

import Encryption.PrivateInfoEncryption;
import org.apache.juli.logging.Log;

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
        info.put("username", username);
        info.put("password", password);
        info.put("webpage", webpage);
        info.put("url", url);
    }

    @Override
    public LogIn decrypt(String key) {
        String dUsername = PrivateInfoEncryption.decryptInfo(key, info.get("username"));
        String dPass = PrivateInfoEncryption.decryptInfo(key, info.get("password"));
        String dWeb = PrivateInfoEncryption.decryptInfo(key, info.get("webpage"));
        String dUrl = PrivateInfoEncryption.decryptInfo(key, info.get("url"));
        LogIn decryptedLogin = new LogIn(dUsername, dPass, dWeb, dUrl);

        return decryptedLogin;
    }
}
