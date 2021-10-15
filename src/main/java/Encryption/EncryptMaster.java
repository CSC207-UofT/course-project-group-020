package Encryption;

//obtained inspiration from: https://mkyong.com/java/java-sha-hashing-example/
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMaster {

    private static final Charset utf_8 = StandardCharsets.UTF_8;
    private static final String ALGO = "SHA3-256"; //"SHA-256"; --> for SHA-2 etc...

    public static String encryptMaster(String to_encrypt) {
        return bytesToHex(EncryptMaster.digest(to_encrypt.getBytes(utf_8), ALGO));
    }

    public static byte[] digest(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest(input);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        String name = "Haykoooo";
        System.out.println(encryptMaster(name));
    }
}
