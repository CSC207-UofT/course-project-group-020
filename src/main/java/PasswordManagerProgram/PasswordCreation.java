package PasswordManagerProgram;

import java.security.SecureRandom;

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
    private static String[] allowedChars = new String[0];


    public PasswordCreation(){
        final String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String numbers = "0123456789";
        final String symbols = "!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";

        allowedChars = new String[]{lowercaseLetters, uppercaseLetters, numbers, symbols};
    }
    /**
     * This method generates and returns a strong password using the SecureRandom class, which is a cryptographically
     * strong random number generator.
     *
     * Documentation: https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html
     *
     * @param length The length of the password that is generated. Must be >= 12.
     * @return string that is a strong password of the specified length
     */
    public static String generatePassword(int length) {
        if (length < 12){
            throw new IllegalArgumentException("Randomly generated passwords must have length of at least 12.");
        }

        StringBuilder password = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        // generates password randomly and then checks to see if it fulfills the strong password requirements
        do {
            for(int i=0; i<length; i++){
                // randomly choose a type of character
                int chooseType = random.nextInt(4);
                String charType = allowedChars[chooseType];

                // randomly choose a character from the chosen character type
                int random_index = random.nextInt(charType.length());
                password.append(charType.charAt(random_index));
            }
        } while(checkPasswordStrength(new String(password))[0].equals(PasswordStrength.STRONG.toString()));
        return new String(password);
    }

    /**
     * This method rates the strength of a password on a scale of Weak, Medium, and Strong. Passwords that are weak
     * contain 6 or fewer characters or contain only one type of character, while strong passwords satisfy all the
     * requirements listed in the class description. Medium passwords are passwords that are not Weak or Strong.
     *
     * @param password The password that is to be checked.
     * @return array of strings where the first element is the strength of the password and the second element is a
     *         recommendation on how to increase the rating of the password. If the password is considered strong, then
     *         the second element is an empty string.
     */
    public static String[] checkPasswordStrength(String password){
        String message = "Missing: lowercase letters, uppercase letters, symbols, numbers";
        int charTypeCounter = 0;

        for(int i = 0;i<password.length(); i++){
            char c = password.charAt(i);
            for (String key: allowedChars) {
                if (key.indexOf(c) != -1){
                    charTypeCounter++;
                    message = editMessage(message, key);
                }
            }
        }
        return determineRatingAndMessage(password, message, charTypeCounter);
    }

    /**
     * Helper method for checkPasswordStrength. Checks the criteria laid out in the class description and customizes
     * the message if needed.
     */
    private static String[] determineRatingAndMessage(String password, String message, int charTypeCounter) {
        if(password.length() >= 12 && charTypeCounter == 4){
            return new String[]{PasswordStrength.STRONG.toString(), ""};
        }
        else if(password.length() >= 12 && charTypeCounter == 3){
            return new String[]{PasswordStrength.STRONG.toString(), message};
        }
        else if (password.length() < 6){
            return new String[]{PasswordStrength.WEAK.toString(), "Needs more characters"};
        }
        else if(charTypeCounter == 1){
            return new String[]{PasswordStrength.WEAK.toString(), message};
        }
        else{
            return new String[]{PasswordStrength.MEDIUM.toString(), message};
        }
    }

    /**
     * Helper method for checkPasswordStrength. Edits the message based on what needs to be changed in the password to
     * make it stronger.
     */
    private static String editMessage(String message, String key) {
        if (key.equals(allowedChars[0])) {
            message = message.replace("lowercase letters, ", "");
        }
        else if (key.equals(allowedChars[1])) {
            message = message.replace("uppercase letters, ", "");
        }
        else if (key.equals(allowedChars[2])) {
            message = message.replace("symbols, ", "");
        }
        else{
            message = message.replace(", numbers", "");
        }
        return message;
    }
}
