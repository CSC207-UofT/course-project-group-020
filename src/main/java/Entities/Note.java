package Entities;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that represents a Note of the user
 */
// Java warns that Class Note is never used, but we will not solve this style warning for the beta program.
public class Note extends PrivateInfo {

    /**
     * This is the constructor for this class. Two parameters are necessary to construct an instance of Note.
     * @param title The title of the Note, e.g. ("Dogs?")
     * @param content The string representation of the context of the note, e.g. ("Yes, I like dogs a lot!")
     */

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public Note(String title, String content) {
        super();
        info.put("title", title);
        info.put("content", content);
    }

    public Note decryptInfoType(String key){
        List<String> decrypted = new ArrayList<>();
        decrypted.add(decryptInfo(key, "title"));
        decrypted.add(decryptInfo(key, "content"));
        return new Note(decrypted.get(0), decrypted.get(1));
    }

}
