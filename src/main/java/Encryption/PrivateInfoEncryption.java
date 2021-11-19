package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * The class that is in charge of the encryption of the information the user is storing. Works using a key that
 * is used for the encryption and decryption. Without it, the data would not be able to be converted back.
 */

public class PrivateInfoEncryption {

    /**
     * This is the encryption method that takes in the key and string to be encrypted.
     *
     * It uses the blowfish api to encrypt the string text. The text needs to be converted to a byte arraylist
     * first.
     *
     * Then the encryption is converted back using StringBuilder to display it and store it.
     *
     * @param key The string representation of the key that will be used to encrypt
     * @param text_to_encrypt The string which is to be encrypted
     *
     */
    public static String encryptInfo(String key, String text_to_encrypt) {  //56 char max length key
        try {
            byte[] KeyData = key.getBytes();
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
     * This is the decryption method that again takes in a key and the encrypted text to be decrypted.
     *
     * The text is converted to an arraylist of bytes to be decrypted using the blowfish api again. Both the key
     * and the text have to match when they were encrypted for this to work.
     *
     * Then the bytes arraylist is converted back to be displayed.
     *
     * @param key The string representation of the key that will be used to decrypt the ciphertext back to plaintext
     * @param encrypted_text The string that represents the ciphertext
     */
    public static String decryptInfo(String key, String encrypted_text){
        try{
            byte[] bb = new byte[encrypted_text.length()];

            for (int i=0; i<encrypted_text.length(); i++) {
                bb[i] = (byte) encrypted_text.charAt(i);
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