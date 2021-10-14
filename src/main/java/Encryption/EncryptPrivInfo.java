package Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptPrivInfo {

    public String encryptInfo(String key, String text_to_encrypt) {  //56 char max length key
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

    public String decryptInfo(String key, String encrypted_text){
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