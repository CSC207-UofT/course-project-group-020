package Encryption;

import Account.Account;
import Exceptions.AttributeNotFoundException;
import PrivateInfoObjects.PrivateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface describes the necessary methods that are required for an encryption class used for encryption and
 * decryption of PrivateInfo Objects.
 */
public interface IPrivateInfoEncryptor {
    String encryptString(String key, String text_to_encrypt);
    String decryptString(String text, String key);
    String[] encryptList(String[] data, String key);
    List<PrivateInfo> decryptVault(Account account, String key) throws AttributeNotFoundException;
}
