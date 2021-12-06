package Encryption;

/**
 * This interface describes the necessary methods that are required for an encryption class used for encryption and
 * decryption of the master password.
 */
public interface MasterEncryptor{
    String encryptMaster(String to_encrypt);
}
