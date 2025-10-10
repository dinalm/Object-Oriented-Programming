package Task_02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorTest {

    private StringManipulator manipulator = new StringManipulator();

    // Tests for concatenate method
    @Test
    void testConcatenateTwoStrings() {
        String result = manipulator.concatenate("Hello", "World");
        assertEquals("HelloWorld", result);
    }

    @Test
    void testConcatenateWithSpaces() {
        String result = manipulator.concatenate("Hello ", "World");
        assertEquals("Hello World", result);
    }

    @Test
    void testConcatenateEmptyStrings() {
        String result = manipulator.concatenate("", "");
        assertEquals("", result);
    }

    @Test
    void testConcatenateWithOneEmpty() {
        String result = manipulator.concatenate("Test", "");
        assertEquals("Test", result);
    }

    // Tests for findLength method
    @Test
    void testFindLengthOfNormalString() {
        int length = manipulator.findLength("Hello");
        assertEquals(5, length);
    }

    @Test
    void testFindLengthOfEmptyString() {
        int length = manipulator.findLength("");
        assertEquals(0, length);
    }

    @Test
    void testFindLengthWithSpaces() {
        int length = manipulator.findLength("Hello World");
        assertEquals(11, length);
    }

    @Test
    void testFindLengthOfSingleCharacter() {
        int length = manipulator.findLength("A");
        assertEquals(1, length);
    }

    // Tests for convertToUpperCase method
    @Test
    void testConvertToUpperCase() {
        String result = manipulator.convertToUpperCase("hello");
        assertEquals("HELLO", result);
    }

    @Test
    void testConvertToUpperCaseAlreadyUpperCase() {
        String result = manipulator.convertToUpperCase("HELLO");
        assertEquals("HELLO", result);
    }

    @Test
    void testConvertToUpperCaseMixed() {
        String result = manipulator.convertToUpperCase("HeLLo WoRLd");
        assertEquals("HELLO WORLD", result);
    }

    @Test
    void testConvertToUpperCaseWithNumbers() {
        String result = manipulator.convertToUpperCase("hello123");
        assertEquals("HELLO123", result);
    }

    // Tests for convertToLowerCase method
    @Test
    void testConvertToLowerCase() {
        String result = manipulator.convertToLowerCase("HELLO");
        assertEquals("hello", result);
    }

    @Test
    void testConvertToLowerCaseAlreadyLowerCase() {
        String result = manipulator.convertToLowerCase("hello");
        assertEquals("hello", result);
    }

    @Test
    void testConvertToLowerCaseMixed() {
        String result = manipulator.convertToLowerCase("HeLLo WoRLd");
        assertEquals("hello world", result);
    }

    @Test
    void testConvertToLowerCaseWithNumbers() {
        String result = manipulator.convertToLowerCase("HELLO123");
        assertEquals("hello123", result);
    }

    // Tests for containsSubstring method
    @Test
    void testContainsSubstringFound() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    void testContainsSubstringNotFound() {
        boolean result = manipulator.containsSubstring("Hello World", "Java");
        assertFalse(result);
    }

    @Test
    void testContainsSubstringAtBeginning() {
        boolean result = manipulator.containsSubstring("Hello World", "Hello");
        assertTrue(result);
    }

    @Test
    void testContainsSubstringAtEnd() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    void testContainsSubstringEmptySubstring() {
        boolean result = manipulator.containsSubstring("Hello World", "");
        assertTrue(result);
    }

    @Test
    void testContainsSubstringCaseSensitive() {
        boolean result = manipulator.containsSubstring("Hello World", "hello");
        assertFalse(result);
    }

    @Test
    void testContainsSubstringSingleCharacter() {
        boolean result = manipulator.containsSubstring("Hello", "l");
        assertTrue(result);
    }
}
