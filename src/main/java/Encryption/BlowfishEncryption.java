package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Account.Account;
import PrivateInfoObjects.*;

import java.util.ArrayList;
/**
 * The class that is in charge of the encryption of the information the user is storing. Works using a key that
 * is used for the encryption and decryption. Without it, the data would not be able to be converted back.
 */

public class BlowfishEncryption implements PrivateInfoEncryptor{

    public String[] encryptList(String[] data, String key){
        String[] eData = new String[data.length];
        for(int i = 0; i < eData.length; i++){
            eData[i] = encryptInfo(key, data[i]);
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
     * @param text_to_encrypt The string which is to be encrypted.
     *
     */
    public String encryptInfo(String key, String text_to_encrypt) {  //56 char max length key
        byte[] KeyData = key.getBytes();
        try {
            SecretKeySpec keyspec = new SecretKeySpec(KeyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            //the actual encryption happens here
            byte[] encrypted = cipher.doFinal(text_to_encrypt.getBytes());

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
     *
     * @param account The Account to have its info decrypted.
     * @param key The string representation of the key corresponding to the Account.
     */
    public ArrayList<PrivateInfo> decryptVault(Account account, String key){

        ArrayList<PrivateInfo> decryptedVault = new ArrayList<>();

        for(PrivateInfo private_info: account.getVault()){
            PrivateInfo decryptedInfo = decryptPrivateInfo(private_info, private_info.type, key);
            decryptedInfo.setId(private_info.id);
            decryptedVault.add(decryptedInfo);
        }
        return decryptedVault;
    }

    public PrivateInfo decryptPrivateInfo(PrivateInfo privateInfo, String type, String key){
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

    private PrivateInfo decryptLogin(PrivateInfo privateLogin, String key){
        return new LogIn(decryptInfo(privateLogin.getInfo("username"), key),
                decryptInfo(privateLogin.getInfo("password"), key),
                decryptInfo(privateLogin.getInfo("webpage"), key),
                decryptInfo(privateLogin.getInfo("url"), key));
    }

    private PrivateInfo decryptContact(PrivateInfo privateContact, String key){
        return new Contact(decryptInfo(privateContact.getInfo("name"), key),
                decryptInfo(privateContact.getInfo("number"), key),
                decryptInfo(privateContact.getInfo("address"), key));
    }


    private PrivateInfo decryptId(PrivateInfo privateId, String key){
        return new Identification(decryptInfo(privateId.getInfo("IDType"), key),
                decryptInfo(privateId.getInfo("IDNumber"), key),
                decryptInfo(privateId.getInfo("IDExpirationDate"), key));
    }

    private PrivateInfo decryptNote(PrivateInfo privateNote, String key){
        return new Note(decryptInfo(privateNote.getInfo("title"), key),
                decryptInfo(privateNote.getInfo("content"), key));
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
    public String decryptInfo(String text, String key){
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