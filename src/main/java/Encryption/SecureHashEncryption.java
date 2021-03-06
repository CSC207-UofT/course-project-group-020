package Encryption;

//obtained inspiration from: https://mkyong.com/java/java-sha-hashing-example/

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class that is charged with encrypting the Master Passwords to log-in to the main program for users to access
 * their data (that is also encrypted).
 * <p>
 * It just has two instance attributes that are 'global variables' used only for the class as parameters to
 * choose the type of encryption and character use.
 */

public class SecureHashEncryption implements IMasterEncryptor {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String ALGO = "SHA-256"; //"SHA-256"; --> for SHA-2 etc...

    /**
     * This method is the main encryption method. It unites the other methods.
     *
     * @param to_encrypt This is the string that is to be encrypted
     */
    public String encryptMaster(String to_encrypt) {
        return bytesToHex(SecureHashEncryption.digest(to_encrypt.getBytes(UTF_8)));
    }

    /**
     * This method is in charge of the actual encryption.
     *
     * @param input An array of bytes
     */
    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(SecureHashEncryption.ALGO);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest(input);
    }

    /**
     * This method is to convert from bytes to hexadecimal and to be able to output it as a hex string.
     * Mainly for visual representation and later storing purposes.
     *
     * @param bytes An array of bytes
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
