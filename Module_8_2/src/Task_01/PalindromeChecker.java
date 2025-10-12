package Task_01;

public class PalindromeChecker {

    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        // Remove non-alphanumeric characters and convert to lowercase
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Check if the cleaned string reads the same forwards and backwards
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }
}

