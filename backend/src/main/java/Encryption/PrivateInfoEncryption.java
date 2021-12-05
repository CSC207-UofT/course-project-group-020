package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Account.Account;
import PrivateInfoObjects.PrivateInfo;

/**
 * The class that is in charge of the encryption of the information the user is storing. Works using a key that
 * is used for the encryption and decryption. Without it, the data would not be able to be converted back.
 */

public class PrivateInfoEncryption {

    /**
     * This is the encryption method that takes in the key and string to be encrypted.
     *
     * It uses the blowfish API to encrypt the string text. The text needs to be converted to a byte arraylist
     * first.
     *
     * Then the encryption is converted back using StringBuilder to display it and store it.
     *
     * @param key The string representation of the key that will be used to encrypt
     * @param text_to_encrypt The string which is to be encrypted
     *
     */
    public static String encryptInfo(String key, String text_to_encrypt) {  //56 char max length key
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
     * This is the Account decryption method that again takes in an account with encrypted vault and the key.
     *
     * @param account The Account to have its info decrypted
     * @param key The string representation of the key corresponding to the Account
     */
    public static Account decryptAccount(Account account, String key){

        Account decrypted_account = new Account(account.getUsername(), account.getMasterPassword());

        for(PrivateInfo private_info: account.getVault()){
            PrivateInfo decryptedInfo = private_info.decryptInfoType(key);
            decryptedInfo.setId(private_info.id);
            decrypted_account.getVault().add(decryptedInfo);
        }
        return decrypted_account;
    }

    public static String[] encryptList(String[] data, String key){
        String[] eData = new String[data.length];
        for(int i = 0; i < eData.length; i++){
            eData[i] = PrivateInfoEncryption.encryptInfo(key, data[i]);
        }
        return eData;
    }

}