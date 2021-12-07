package PrivateInfoObjects;

import Encryption.BlowfishEncryption;
import Encryption.PrivateInfoEncryptor;

public class PrivateInfoFactory {

    /**
     * Creates a Login object with the given data and encrypted with the given key. The order of
     * the values in data correspond to the values in the created Login object.
     *
     *
     * @param data Data that is stored in the created Login object. Indexes correspond to attribute in object.
     *             Index 0 is the username.
     *             Index 1 is the password.
     *             Index 2 is the webpage.
     *             Index 3 is the url.
     * @param key  Key used to encrypted newly created Login
     * @return Login object
     */
    public static LogIn createLogin(String[] data, String key){
        PrivateInfoEncryptor encryptor = new BlowfishEncryption();
        String[] eData = encryptor.encryptList(data, key);
        return new LogIn(eData[0], eData[1], eData[2], eData[3]);
    }

    /**
     * Creates a Contact object with the given data and encrypted with the given key. The order of
     * the values in data correspond to the values in the created object.
     *
     *
     * @param data Data that is stored in the created Login object. Indexes correspond to attribute in object.
     *             Index 0 is the name.
     *             Index 1 is the number.
     *             Index 2 is the address.
     * @param key  Key used to encrypted newly created Contact
     * @return Contact object
     */
    public static Contact createContact(String[] data, String key){
        PrivateInfoEncryptor encryptor = new BlowfishEncryption();
        String[] eData = encryptor.encryptList(data, key);
        return new Contact(eData[0], eData[1], eData[2]);
    }

    /**
     * Creates an Identification object with the given data and encrypted with the given key. The order of
     * the values in data correspond to the values in the created object.
     *
     *
     * @param data Data that is stored in the created Identification object. Indexes correspond to attribute in object.
     *             Index 0 is the IdType.
     *             Index 1 is the IdNumber.
     *             Index 2 is the IdExpirationDay.
     * @param key  Key used to encrypted newly created Identification
     * @return Identification object
     */
    public static Identification createIdentification(String[] data, String key){
        PrivateInfoEncryptor encryptor = new BlowfishEncryption();
        String[] eData = encryptor.encryptList(data, key);
        return new Identification(eData[0], eData[1], eData[2]);
    }

    /**
     * Creates a Note object with the given data and encrypted with the given key. The order of
     * the values in data correspond to the values in the created object.
     *
     *
     * @param data Data that is stored in the created Note object. Indexes correspond to attribute in object.
     *             Index 0 is the Title.
     *             Index 1 is the Content.
     * @param key  Key used to encrypted newly created Note
     * @return Identification object
     */
    public static Note createNote(String[] data, String key){
        PrivateInfoEncryptor encryptor = new BlowfishEncryption();
        String[] eData = encryptor.encryptList(data, key);
        return new Note(eData[0], eData[1]);
    }

    /**
     * Creates an object of the given type with the given data and encrypted with the given key.
     *
     * @param type String value representing types of accepted types. Value can be one of
     *             ["Login", "Contact", "Identification", "Note"]
     * @param data Data that is stored in the created Identification object. Indexes correspond to attribute in object.
     * @param key  Key used to encrypted newly created Identification
     * @return Instance of PrivateInfo subclass
     */
    public static PrivateInfo createEntryByType(String type, String[] data, String key) throws ClassNotFoundException{
        switch (type) {
            case "Login":
                return createLogin(data, key);
            case "Contact":
                return createContact(data, key);
            case "ID":
                return createIdentification(data, key);
            case "Note":
                return createNote(data, key);
            default:
                throw new ClassNotFoundException("Cannot create entry of this class.");
        }
    }

}
