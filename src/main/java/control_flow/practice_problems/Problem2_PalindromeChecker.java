class PalindromeChecker {

    boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    boolean isPalindromeRecursive(String text) {
        return checkRecursive(text, 0, text.length() - 1);
    }

    private boolean checkRecursive(String text, int left, int right) {

        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return checkRecursive(text, left + 1, right - 1);
    }

    boolean isPalindromeArrayReversal(String text) {

        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }
}

public class Problem2_PalindromeChecker {

    public static void main(String[] args) {

        String text = "madam";

        PalindromeChecker checker = new PalindromeChecker();

        String iterative = checker.isPalindromeIterative(text)
                ? "Palindrome" : "Not Palindrome";

        String recursive = checker.isPalindromeRecursive(text)
                ? "Palindrome" : "Not Palindrome";

        String arrayReversal = checker.isPalindromeArrayReversal(text)
                ? "Palindrome" : "Not Palindrome";

        System.out.println(
                "Iterative: " + iterative +
                " | Recursive: " + recursive +
                " | Array Reversal: " + arrayReversal
        );
    }
}