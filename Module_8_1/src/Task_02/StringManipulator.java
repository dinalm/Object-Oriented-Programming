package Task_02;

public class StringManipulator {

    /**
     * Concatenates two strings together
     */
    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }

    /**
     * Returns the length of the input string
     */
    public int findLength(String str) {
        return str.length();
    }

    /**
     * Converts the input string to uppercase
     */
    public String convertToUpperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * Converts the input string to lowercase
     */
    public String convertToLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * Checks if the input string contains the given substring
     */
    public boolean containsSubstring(String str, String subStr) {
        return str.contains(subStr);
    }
}
