package Entities;

/**
 * This is a class that represents a Note
 */
// Java warns that Class Note is never used, but we will not solve this style warning for the beta program.
public class Note extends PrivateInfo {

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public Note(String title, String content) {
        super();
        info.put("title", title);
        info.put("content", content);


    }

}
