package PasswordManagerProgram;

import java.security.SecureRandom;
/**
 * Class responsible for creating strong passwords and rating the strength of passwords
 *
 */
public class PasswordGeneration {
    public String generatePassword(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return new String(bytes);
    }
}
