import java.util.*;

class Solution {
    public boolean checkValidString(String s) {

        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                open.push(i);
            }

            else if (ch == '*') {
                star.push(i);
            }

            else { // ')'

                if (!open.isEmpty()) {
                    open.pop();
                }
                else if (!star.isEmpty()) {
                    star.pop();
                }
                else {
                    return false;
                }
            }
        }

        // Match remaining '(' with '*' 
        while (!open.isEmpty() && !star.isEmpty()) {

            int openIndex = open.pop();
            int starIndex = star.pop();

            // '*' must come AFTER '('
            if (starIndex < openIndex) {
                return false;
            }
        }

        // If '(' are still left, they cannot be matched
        return open.isEmpty();
    }
}