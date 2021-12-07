package PrivateInfoObjects;

/**
 * This is a class that represents a Note of the user
 */

public class Note extends PrivateInfo {
    private static final long serialVersionUID = 1L;

    /**
     * This is the constructor for this class. Two parameters are necessary to construct an instance of Note.
     *
     * @param title   The title of the Note, e.g. ("Dogs?")
     * @param content The string representation of the context of the note, e.g. ("Yes, I like dogs a lot!")
     */

    public Note(String title, String content) {
        super();
        this.buildInfo("title", title);
        this.buildInfo("content", content);
        this.setType("Note");
    }

}
