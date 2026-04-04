class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        
        if (rows == 1) return encodedText;

        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder result = new StringBuilder();

        // Start from each column
        for (int col = 0; col < cols; col++) {
            int i = 0, j = col;

            // Traverse diagonally
            while (i < rows && j < cols) {
                result.append(encodedText.charAt(i * cols + j));
                i++;
                j++;
            }
        }

        // Remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }

        return result.substring(0, end + 1);
    }
}
    