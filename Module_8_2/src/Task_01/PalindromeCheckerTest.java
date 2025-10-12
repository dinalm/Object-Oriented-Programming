package Task_01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PalindromeCheckerTest {

    @Test
    public void testSimplePalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("radar"));
    }

    @Test
    public void testSingleCharacter() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("a"));
    }

    @Test
    public void testPalindromeWithSpaces() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"));
    }

    @Test
    public void testPalindromeWithMixedCase() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("RaceCar"));
    }

    @Test
    public void testPalindromeWithPunctuation() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw?"));
    }

    @Test
    public void testNonPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("hello"));
    }

    @Test
    public void testNonPalindromeOpenAI() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("openai"));
    }

    @Test
    public void testEmptyString() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(""));
    }

    @Test
    public void testNumericPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("12321"));
    }

    @Test
    public void testNumericNonPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("12345"));
    }
}

