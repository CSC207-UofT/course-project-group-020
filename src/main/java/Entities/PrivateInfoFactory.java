package Entities;

import Encryption.PrivateInfoEncryption;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.util.List;

public class PrivateInfoFactory {

    public static LogIn createLogin(String[] data, String key){
        String[] eData = PrivateInfoEncryption.encryptList(data, key);
        return new LogIn(eData[0], eData[1], eData[2], eData[3]);
    }

    public static Contact createContact(String[] data, String key){
        String[] eData = PrivateInfoEncryption.encryptList(data, key);
        return new Contact(eData[0], eData[1], eData[2]);
    }

    public static ID createID(String[] data, String key){
        String[] eData = PrivateInfoEncryption.encryptList(data, key);
        return new ID(eData[0], eData[1], eData[2]);
    }

    public static Note createNote(String[] data, String key){
        String[] eData = PrivateInfoEncryption.encryptList(data, key);
        return new Note(eData[0], eData[1]);
    }


    public static PrivateInfo createEntryByType(String type, String[] data, String key) {
        switch (type) {
            case "Login":
                return createLogin(data, key);
            case "Contact":
                return createContact(data, key);
            case "ID":
                return createID(data, key);
            case "Note":
                return createNote(data, key);
            default:
                return createNote(data, key); //TODO fix this to raise error

        }
    }

}
