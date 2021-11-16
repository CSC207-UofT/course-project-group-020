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


}
