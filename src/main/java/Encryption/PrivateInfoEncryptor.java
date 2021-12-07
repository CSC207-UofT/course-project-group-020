package Encryption;

import Account.Account;
import Exceptions.AttributeNotFoundException;
import PrivateInfoObjects.PrivateInfo;

import java.util.ArrayList;

/**
 * This interface describes the necessary methods that are required for an encryption class used for encryption and
 * decryption of PrivateInfo Objects.
 */
public interface PrivateInfoEncryptor{
    String encryptInfo(String key, String text_to_encrypt);
    String decryptInfo(String text, String key);
    String[] encryptList(String[] data, String key);
    ArrayList<PrivateInfo> decryptVault(Account account, String key) throws AttributeNotFoundException;
}
