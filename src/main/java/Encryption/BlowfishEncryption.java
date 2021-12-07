package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Account.Account;
import Exceptions.AttributeNotFoundException;
import PrivateInfoObjects.*;

import java.util.ArrayList;

/**
 * The class that is in charge of the encryption of the information the user is storing. Works using a key that
 * is used for the encryption and decryption. Without it, the data would not be able to be converted back.
 */

public class BlowfishEncryption implements IPrivateInfoEncryptor {

    public String[] encryptList(String[] data, String key){
        String[] eData = new String[data.length];
        for(int i = 0; i < eData.length; i++){
            eData[i] = encryptString(key, data[i]);
        }
        return eData;
    }

    /**
     * This is the encryption method that takes in the key and string to be encrypted.
     *
     * It uses the blowfish API to encrypt the string text. The text needs to be converted to a
     * byte arraylist first.
     *
     * Then the encryption is converted back using StringBuilder to display it and store it.
     *
     * @param key The string representation of the key that will be used to encrypt.
     * @param textToEncrypt The string which is to be encrypted.
     *
     */
    public String encryptString(String key, String textToEncrypt) {  //56 char max length key
        byte[] KeyData = key.getBytes();
        try {
            SecretKeySpec keyspec = new SecretKeySpec(KeyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            //the actual encryption happens here
            byte[] encrypted = cipher.doFinal(textToEncrypt.getBytes());

            //construct the encrypted char sentence
            StringBuilder string_bytes = new StringBuilder();
            for (byte b: encrypted) {
                string_bytes.append((char)b);
            }

            //the encrypted String returned
            return string_bytes.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This is the Account decryption method that again takes in an account with encrypted vault
     * and the key.
     *  @param account The Account to have its info decrypted.
     * @param key The string representation of the key corresponding to the Account.
     * @return
     */
    public ArrayList<PrivateInfo> decryptVault(Account account, String key) throws AttributeNotFoundException {

        ArrayList<PrivateInfo> decryptedVault = new ArrayList<PrivateInfo>();

        for(PrivateInfo private_info: account.getVault()){
            PrivateInfo decryptedInfo = decryptPrivateInfo(private_info, private_info.getType(), key);
            decryptedInfo.setId(private_info.getId());
            decryptedVault.add(decryptedInfo);
        }
        return decryptedVault;
    }

    /**
     * Function to decrypt PrivateInfo object based on which subclass it belongs to
     * @param privateInfo to be decrypted
     * @param type String type can be Login, ID, Contact, Note
     * @param key String key used to decrypt
     * @return PrivateInfo but with decrypted info
     */
    public PrivateInfo decryptPrivateInfo(PrivateInfo privateInfo, String type, String key) throws AttributeNotFoundException{
        switch (type){
            case "Login":
                return decryptLogin(privateInfo, key);
            case "Contact":
                return decryptContact(privateInfo, key);
            case "ID":
                return decryptId(privateInfo, key);
            case "Note":
                return decryptNote(privateInfo, key);
            default:
                throw new IllegalArgumentException("Type doesn't exist!");
        }
    }

    /**
     * Function to decrypt a Login object
     *
     * @param privateLogin to be decrypted
     * @param key String key used to decrypt
     * @return Login object upcasted to PrivateInfo
     */
    private PrivateInfo decryptLogin(PrivateInfo privateLogin, String key) throws AttributeNotFoundException{
        return new LogIn(decryptString(privateLogin.getInfo("username"), key),
                decryptString(privateLogin.getInfo("password"), key),
                decryptString(privateLogin.getInfo("webpage"), key),
                decryptString(privateLogin.getInfo("url"), key));
    }

    /**
     * Function to decrypt Contact object
     *
     * @param privateContact to be decrypted
     * @param key String key used to decrypt
     * @return Contact object upcasted to PrivateInfo
     */
    private PrivateInfo decryptContact(PrivateInfo privateContact, String key) throws AttributeNotFoundException{
        return new Contact(decryptString(privateContact.getInfo("name"), key),
                decryptString(privateContact.getInfo("number"), key),
                decryptString(privateContact.getInfo("address"), key));
    }

    /**
     * Function to decrypt ID object
     *
     * @param privateId to be decrypted
     * @param key String key used to decrypt
     * @return ID object upcasted to PrivateInfo
     */
    private PrivateInfo decryptId(PrivateInfo privateId, String key) throws AttributeNotFoundException{
        return new Identification(decryptString(privateId.getInfo("IDType"), key),
                decryptString(privateId.getInfo("IDNumber"), key),
                decryptString(privateId.getInfo("IDExpirationDate"), key));
    }

    /**
     * Function to decrypt Note object
     *
     * @param privateNote to be decrypted
     * @param key String key used to decrypt
     * @return Note object upcasted to PrivateInfo
     */
    private PrivateInfo decryptNote(PrivateInfo privateNote, String key) throws AttributeNotFoundException{
        return new Note(decryptString(privateNote.getInfo("title"), key),
                decryptString(privateNote.getInfo("content"), key));
    }

    /**
     * This is the decryption method that again takes in a key and the encrypted text to be decrypted.
     * <p>
     * The text is converted to an arraylist of bytes to be decrypted using the blowfish api again. Both the key
     * and the text have to match when they were encrypted for this to work.
     * <p>
     * Then the bytes arraylist is converted back to be displayed.
     *
     * @param key The string representation of the key that will be used to decrypt the ciphertext back
     *            to plaintext.
     * @param text The label is the attribute of the PrivateInfo that we are trying to decrypt.
     */
    public String decryptString(String text, String key){
        try{
            byte[] bb = new byte[text.length()];

            for (int i=0; i<text.length(); i++) {
                bb[i] = (byte) text.charAt(i);
            }

            byte[] KeyData = key.getBytes();
            SecretKeySpec keyspec = new SecretKeySpec(KeyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, keyspec);
            //actual decryption happens here and we return it straight away
            return new String(cipher.doFinal(bb));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}