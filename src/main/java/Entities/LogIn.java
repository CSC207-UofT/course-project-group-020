package Entities;

import java.util.ArrayList;
import java.util.List;

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

    public LogIn decryptInfoType(String key){
        List<String> decrypted = new ArrayList<>();
        decrypted.add(decryptInfo(key, "username"));
        decrypted.add(decryptInfo(key, "password"));
        decrypted.add(decryptInfo(key, "webpage"));
        decrypted.add(decryptInfo(key, "url"));
        return new LogIn(decrypted.get(0), decrypted.get(1), decrypted.get(2), decrypted.get(3));
    }
}
