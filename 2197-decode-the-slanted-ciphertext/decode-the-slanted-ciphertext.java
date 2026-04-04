class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        if (rows == 0) return "";

        int columns = l / rows;
        StringBuilder originalText = new StringBuilder();

        for (int col = 0; col < columns; col++) {
            for (int j = col; j < l; j += (columns + 1)) {
                originalText.append(encodedText.charAt(j));
            }
        }

        // remove trailing spaces
        int end = originalText.length() - 1;
        while (end >= 0 && originalText.charAt(end) == ' ') {
            end--;
        }

        return originalText.substring(0, end + 1);
    }
}
    