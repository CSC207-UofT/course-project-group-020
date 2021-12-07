/*
 * This file contains JUnit test cases for EncryptMaster.java/
 */

import Password.PasswordCreation;
import Password.PasswordStrength;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a class responsible for testing the class PasswordCreation
 */
public class PasswordCreationTest {
    /**
     * This is a test to ensure that generatePassword generates a password of the correct length
     */
    @Test
    public void testIfPasswordIsCorrectLength() {
        String password = PasswordCreation.generatePassword(12);
        assert (password.length() == 12);
    }

    /**
     * This is a test to ensure that generatePassword generates a strong password.
     */
    @Test
    public void testIfPasswordIsStrong() {
        String password = PasswordCreation.generatePassword(12);
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.STRONG.toString());
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a weak password with a length that is
     * too small.
     */
    @Test
    public void testWeakPasswordRatingTooShort() {
        String password = "ffd/6";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.WEAK.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Needs more characters");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a weak password with not enough types
     * of characters.
     */
    @Test
    public void testWeakPasswordRatingLacksTypes() {
        String password = "ffffffffffffffffd";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.WEAK.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Missing: uppercase letters, symbols, numbers");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a completely strong password.
     */
    @Test
    public void testStrongPasswordAllCharTypes() {
        String password = "ffdFFF4l=7dkFjfd8d";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.STRONG.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a strong password that does not have
     * all character types.
     */
    @Test
    public void testStrongPassword3CharTypes() {
        String password = "ffdFFFl=dkF----jfd";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.STRONG.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Missing: numbers");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a medium strength password.
     */
    @Test
    public void testMediumPassword1() {
        String password = "ffdFFFl";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.MEDIUM.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Missing: symbols, numbers");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a medium strength password.
     */
    @Test
    public void testMediumPassword2() {
        String password = "ffdFFFl6";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.MEDIUM.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Missing: symbols");
    }

    /**
     * This is a test to ensure that checkPasswordStrength correctly identifies a medium strength password.
     */
    @Test
    public void testMediumPassword3() {
        String password = "ffdFFFFDSGFHBFDGCF";
        assertEquals(PasswordCreation.checkPasswordStrength(password)[0], PasswordStrength.MEDIUM.toString());
        assertEquals(PasswordCreation.checkPasswordStrength(password)[1], "Missing: symbols, numbers");
    }
}

