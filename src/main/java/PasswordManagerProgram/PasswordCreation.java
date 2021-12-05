package PasswordManagerProgram;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for facilitating the creation of strong passwords. A strong password consists of
 *
 * - At least 12 characters
 * - A character from at least 3 of the following character types:
 *      - lowercaseLetters
 *      - uppercaseLetters
 *      - numbers
 *      - symbols
 *
 * Source: https://wmich.edu/arts-sciences/technology-password-tips
 */

public class PasswordCreation {
    private final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String numbers = "0123456789";
    private final String symbols = "!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";

    /**
     * This method generates and returns a strong password
     *
     * @param length The length of the password that is generated. Must be >= 12.
     */
    public String generatePassword(int length) {
        if (length < 12){
            throw new IllegalArgumentException("Randomly generated passwords must have length of at least 12.");
        }

        List<String> allowedChars = new ArrayList<>();
        allowedChars.add(lowercaseLetters);
        allowedChars.add(uppercaseLetters);
        allowedChars.add(numbers);
        allowedChars.add(symbols);

        StringBuilder password = new StringBuilder(capacity = length);
        SecureRandom random = new SecureRandom();
        do {
            for(int i=0; i<length; i++){
                int chooseType = random.nextInt(4);
                String charType = allowedChars.get(chooseType);
                int random_index = random.nextInt(charType.length());
                password.append(charType.charAt(random_index));
            }
        } while(checkPasswordStrength(new String(password))[0].equals(PasswordStrength.STRONG.toString()));
        return new String(password);
    }

    /**
     * This method rates the strength of a password on a scale of Weak, Medium, and Strong. Passwords that are weak
     * contain 6 or less characters or contain only one type of character, while strong passwords satisfy all the
     * requirements listed in the class description. Medium passwords are passwords that are not Weak or Strong.
     * requirements.
     *
     * @param password The password that is to be checked.
     * @return
     */
    public String[] checkPasswordStrength(String password){

        Map<String, Boolean> charTypeCount = new HashMap<>();
        charTypeCount.put(lowercaseLetters, false);
        charTypeCount.put(uppercaseLetters, false);
        charTypeCount.put(numbers, false);
        charTypeCount.put(symbols, false);

        // count how many of each symbol is in the password
        for(int i = 0;i<password.length(); i++){
            char c = password.charAt(i);

            for (String key: charTypeCount.keySet()) {
                if (key.indexOf(c) != -1){
                    charTypeCount.replace(key, true);
                }
            }
        }

        int frequency = 0;


        String message = "Missing: lowercase letters, uppercase letters, symbols, numbers";
        if (!charTypeCount.get(lowercaseLetters)){
            message = message.replace("lowercase letters, ", "");
            frequency++;
        }
        if (!charTypeCount.get(uppercaseLetters)){
            message = message.replace("uppercase letters, ", "");
            frequency++;
        }
        if (!charTypeCount.get(symbols)){
            message = message.replace("symbols, ", "");
            frequency++;
        }
        if (!charTypeCount.get(numbers)){
            message = message.replace(", numbers", "");
            frequency++;
        }
        // determine if the password is considered strong
        if(password.length() >= 12 && frequency == 4){
            return new String[]{PasswordStrength.STRONG.toString(), ""};
        }
        else if(password.length() >= 12 && frequency == 3){
            return new String[]{PasswordStrength.STRONG.toString(), message};
        }
        else if (password.length() < 6){
            return new String[]{PasswordStrength.WEAK.toString(), "Needs more characters"};
        }
        else if(frequency == 1){
            return new String[]{PasswordStrength.WEAK.toString(), message};
        }
        else{
            return new String[]{PasswordStrength.MEDIUM.toString(), message};
        }
    }
}
