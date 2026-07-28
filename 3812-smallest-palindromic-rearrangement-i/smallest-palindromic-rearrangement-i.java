import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        
        // Extract the first half of the string
        char[] half = s.substring(0, n / 2).toCharArray();
        
        // Sort to get the lexicographically smallest prefix
        Arrays.sort(half);
        
        String firstHalf = new String(half);
        // Reverse first half to build the matching second half
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        
        // Assemble the full palindrome
        if (n % 2 == 0) {
            return firstHalf + secondHalf;
        } else {
            char mid = s.charAt(n / 2);
            return firstHalf + mid + secondHalf;
        }
    }
}