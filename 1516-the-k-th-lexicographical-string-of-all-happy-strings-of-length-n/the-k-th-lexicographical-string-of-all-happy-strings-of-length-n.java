class Solution {
    static int count = 0;
    static String result = "";
    public String getHappyString(int n, int k) {
        

    
        count = 0;
        result = "";
        backtrack(n, k, "");
        return result;
    }

    private static void backtrack(int n, int k, String current) {

        if (current.length() == n) {
            count++;

            if (count == k) {
                result = current;
            }
            return;
        }

        for (char ch : new char[]{'a', 'b', 'c'}) {

            if (current.length() > 0 && current.charAt(current.length() - 1) ==             ch) {
                continue;
            }

            backtrack(n, k, current + ch);

            if (!result.equals("")) return;
        }
    }
    
}