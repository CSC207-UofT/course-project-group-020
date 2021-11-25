package Entities;

public class PrivateInfoFactory {

    public static LogIn createLogin(String[] data){
        return new LogIn(data[0], data[1], data[2], data[3]);
    }

    public static Contact createContact(String[] data){
        return new Contact(data[0], data[1], data[2]);
    }

    public static ID createID(String[] data){
        return new ID(data[0], data[1], data[2]);
    }

    public static Note createNote(String[] data){
        return new Note(data[0], data[1]);
    }

    public static PrivateInfo createEntryByType(String type, String[] data) {
        switch (type) {
            case "Login":
                return createLogin(data);
            case "Contact":
                return createContact(data);
            case "ID":
                return createID(data);
            case "Note":
                return createNote(data);
            default:
                return createNote(data); //TODO fix this to raise error

        }
    }

}
