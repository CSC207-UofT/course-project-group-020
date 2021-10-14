package Entities;

/**
 * This is a class that represents a Note
 *
 *
 */

public class Note extends PrivateInfo{

    public Note(String title, String content){
        super();
        info.put("title", title);
        info.put("content", content);


    }

}
