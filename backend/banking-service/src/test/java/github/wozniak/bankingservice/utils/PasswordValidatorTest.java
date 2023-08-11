package github.wozniak.bankingservice.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Jackson Wozniak
 * @created : 8/6/2023, Sunday
 **/
class PasswordValidatorTest {

    @Test
    void validPasswords(){
        assertTrue(PasswordValidator.isValid("ab12!@BB"));
        assertTrue(PasswordValidator.isValid("A1q!{}90()"));
        assertTrue(PasswordValidator.isValid("This1s4Passw0rd!"));
    }

    @Test
    void invalidPasswords(){
        assertFalse(PasswordValidator.isValid(""));
        assertFalse(PasswordValidator.isValid("1234567"));
        assertFalse(PasswordValidator.isValid("aB1!()"));
        assertFalse(PasswordValidator.isValid("GGHH[]{}"));
    }
}